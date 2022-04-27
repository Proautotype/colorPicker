/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorPick;

import com.jfoenix.controls.JFXColorPicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author G
 */
public class MyColorPickerController implements Initializable {

    @FXML
    private TextField txt_color;
    @FXML
    private Button btn_copy;
    @FXML
    private JFXColorPicker colPick_one;
    @FXML
    private JFXColorPicker colPick_two;
    myColorClass mcc = new myColorClass();
    /**
     * Initializes the controller class.
     */
    Color color = null;
    @FXML
    private Label lbl_copied;
    String styler = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void style_txt_1(ActionEvent event) {
        color = colPick_one.getValue();
        System.out.println(mcc);
        styler = colorStyle(color);
        txt_color.setText(styler.substring(0, 7));
        txt_color.setStyle(String.format("-fx-background-color: %s;", styler));
    }

    @FXML
    private void style_txt_2(ActionEvent event) {
        color = colPick_two.getValue();
        System.out.println(mcc);
        styler = colorStyle(color);       
        txt_color.setText(styler.substring(0, 7));
        txt_color.setStyle(String.format("-fx-background-color: %s;", styler));
    }

    public String colorStyle(Color tcolor) {
        mcc = new myColorClass();
//        String style = String.format("-fx-background-color: %s;", mcc.toHexString(tcolor));
        String style = mcc.toHexString(tcolor);
        return style;
    }

    @FXML
    private void copyColor(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.clear();
        content.putString(styler.substring(0, 7));
        clipboard.setContent(content);
        if (clipboard.hasString()) {
            lbl_copied.setText("Copied");
        } else {
            lbl_copied.setText("Not copied");
        }
    }

    class myColorClass {

        private double r, g, b;

        public myColorClass() {
        }

        public myColorClass(double r, double g, double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public double getR() {
            return r;
        }

        public void setR(double r) {
            this.r = r;
        }

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        public double getB() {
            return b;
        }

        public void setB(double b) {
            this.b = b;
        }

        public String getColor(Color color) {
            String colorCom = this.r + "," + this.g + "," + this.b;
            return colorCom;
        }

        private String toHexString(Color color) {
            int codeRed = ((int) Math.round(color.getRed() * 255) << 24);
            int codeGreen = ((int) Math.round(color.getGreen() * 255) << 16);
            int codeBlue = ((int) Math.round(color.getBlue() * 255) << 8);
            int opacity = ((int) Math.round(color.getOpacity() * 255));
            String colorValue = String.format("#%08X", (codeRed + codeGreen + codeBlue + opacity));
            return colorValue;
        }
    }

}
