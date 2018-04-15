/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdtextdisplay;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author gerard
 */
public class GdTextDisplay {

//    static public String overlayText = "Brandend Zand en een verloren land \n"
//            + "En een leven vol gevaar\n"
//            + "12345678911234567892123456789312345678941234567895\n"
//            + "Brandend Zand berooft je bijna van 't verstand\n"
//            + "En dat alles komt door haar.";
//
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        overlayText = args[0];
        String text = null;

        File file = new File(args[0], args[1]);
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
//        reader.mark(1);
        int aantalLiedRegels = 0;
        while ((text = reader.readLine()) != null) {
            aantalLiedRegels++;
        }
        reader.close();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
//        int maxAantalCharactersOpScherm = 50;
//        int aantalPixelsPerCharacters = (int) width / maxAantalCharactersOpScherm;

//        System.out.println(aantalPixelsPerCharacters);
//        double height = screenSize.getHeight();
//        int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
//        System.out.println(screenRes);
//        int fontSize = (int) Math.round(maxAantalCharactersOpScherm * screenRes / 72.0);
//        System.out.println(fontSize);
//        String path = "/home/gerard/Afbeeldingen";
        String path = args[0];

// load source images
        BufferedImage image = ImageIO.read(new File(path, "image.png"));
        BufferedImage overlay = ImageIO.read(new File(path, "overlay.png"));

// create the new image, canvas size is the max. of both image sizes
        int w = image.getWidth();
        int h = image.getHeight();
//        int w = Math.max(image.getWidth(), overlay.getWidth());
//        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);

//            g.setFont(new Font("Arial", Font.PLAIN, fontSize));
//            g.setFont(new Font("Arial", Font.PLAIN, 45));
        int fontsize = Integer.parseInt(args[2]);
        g.setFont(new Font("Courier", Font.PLAIN, fontsize));
//            g.setFont(g.getFont().deriveFont(30f));
//            String[] lines = overlayText.split("\n"); //breaking the lines into an array
//            String[] lines = overlayText.split("|"); //breaking the lines into an array
//            int langstRegel = 0;
//            for (int lineCount = 0; lineCount < lines.length; lineCount++) { //lines from above
//                langstRegel = Math.max(langstRegel, lines[lineCount].length());
//
//            }
//            g.getFont().deriveFont(30f)

//            g.setFont(g.getFont().deriveFont(30f));
        int lineHeight = g.getFontMetrics().getHeight();
//            int lineWidth = g.getFontMetrics().charWidth(0)

//            int xPos = (int) width - ((fontSize) * langstRegel);
//        int xPos = 600;
//            System.out.println(width);
//            System.out.println(fontSize);
//            System.out.println(langstRegel);
//            System.out.println(xPos);
//            System.out.println(xPos);
        int xPos = Integer.parseInt(args[3]);
        int lineCount = 0;
        int yPos;
        yPos = h - (lineHeight * (aantalLiedRegels));
        int yPosStart = yPos;
        reader = new BufferedReader(new FileReader(file));
        while ((text = reader.readLine()) != null) {
//            for (int lineCount = 0; lineCount < lines.length; lineCount++) { //lines from above
//            yPos = (int) h / 2 + lineCount * lineHeight;
            yPos = yPosStart  + lineCount * lineHeight;
//                String line = lines[lineCount];
            g.drawString(text, xPos, yPos);
            lineCount++;

        }
        g.dispose();
//            g.drawString(overlayText, 300, 300);
//        g.dispose();

// Save as new image
        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
// TODO code application logic here
//        } catch (IOException ex) {
//            Logger.getLogger(GdTextDisplay.class.getName()).log(Level.SEVERE, null, ex);
    }
}
