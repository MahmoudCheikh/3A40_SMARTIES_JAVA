/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.smarties.entities.Produit;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class QrCodeController implements Initializable {

    static Produit prod;
    @FXML
    private ImageView QrCode;
    private Image genQRCodeImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String AllInfo = " Libelle: " + prod.getLibelle() + "\n Prix: " + prod.getPrix() + "\n Type: " + prod.getType() + "\n Description:  " + prod.getDescription() + "";
        ////////////////////////:
        System.out.println(AllInfo);
        System.out.println("" + AllInfo);
        if (!AllInfo.isEmpty()) {
            String foregroundColor = "#2E3437";
            String backgroundColor = "#FFFFFF";
            genQRCodeImg = encode(AllInfo, Integer.parseInt("300"), Integer.parseInt("300"), foregroundColor, backgroundColor);
            if (genQRCodeImg != null) {
                QrCode.setImage(genQRCodeImg);

            }
            QrCode.setVisible(true);
            //showDialogCodeQR();
        }
    }

    public static Image encode(String data, int width, int height, String foregroundColor, String backgroundColor) {
        try {
            BitMatrix byteMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(java.awt.Color.decode(backgroundColor));
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.decode(foregroundColor));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (WriterException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
