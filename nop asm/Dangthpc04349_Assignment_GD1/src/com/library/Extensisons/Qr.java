package com.library.Extensisons;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Random;

/**
 *
 * @author dell
 */
public class Qr {

    /**
     * @param args the command line arguments
     */
    Random rand = new Random();

    public String path(String voucher) {
        String s = "";
        int min = 33;
        int max = 126;
        // 9D, 12H, 20T, 22-25: so phan tram giam gia
        while (s.length() < 25) {
            //int randPath = rand.nextInt(225);
            int randPath = (int) (Math.random() * (max - min + 1) + min);
            s = s + (char) randPath;
            if (s.length() == 9) {
                s = s + "D";
            }
            if (s.length() == 12) {
                s = s + "H";
            }
            if (s.length() == 17) {
                s = s + "T";
            }
            if (s.length() == 22) {
                s = s + voucher;
            }
            System.out.println(s);
        }
        return s;
    }

    public void createQR(String voucher) {
        try {
            String qrCodeData = voucher;
            String filePath = "qr.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully! and stored at location" + filePath);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Qr qr = new Qr();
        qr.createQR(qr.path("030"));

        // TODO code application logic here
    }

}
