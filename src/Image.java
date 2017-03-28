import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Image {
    private BufferedImage image;
    private int height;
    private int width;
    private String[] charMap = new String[]{" ", ".", "'", "`", "2", "^", ",", ":", ";", "I", "l", "!", "i", ">", "<", "~", "+", "_", "-", "?", "]", "[", "}", "{", "1", ")", "(", "|", "\\", "/", "t", "f", "j", "r", "x", "n", "u", "v", "c", "z", "X", "Y", "U", "J", "C", "L", "Q", "0", "O", "Z", "m", "w", "q", "p", "d", "b", "k", "h", "a", "o", "*", "#", "M", "W", "&", "8", "%", "B", "@", "$"};

    public Image (File file) throws IOException {
        this.image = ImageIO.read(file);
        this.height = image.getHeight();
        this.width = image.getWidth();
    }

    public String[][] getASCII(int charWidth) {
        //font height, width (presumed) 14, 8
        double decimalWidth = (double)width / charWidth;
        int charHeight = (int)(width / ((decimalWidth / 16) * 49));
        System.out.println(charHeight);
        double decimalHeight = (double)height / charHeight;
        int posX = 0;
        int posY = 0;
        int blockWidth;
        int blockHeight;
        String imageArray[][] = new String[charHeight][charWidth];

        for (int i = 0; i < charWidth; i++) {
            blockWidth = (int)((i + 1) * decimalWidth - posX);
            for (int j = 0; j < charHeight; j++) {
                blockHeight = (int)((j + 1) * decimalHeight - posY);
                imageArray[j][i] = charMap[(int)(getAvgGray(posX, posY, blockWidth, blockHeight) / (256.0/charMap.length))];
                posY += blockHeight;
            }
            posY = 0;
            posX += blockWidth;
        }
        return imageArray;
    }

    public String[][] getASCII() {
        return getASCII(500);
    }

    // Nothing sophisticated here, I'm afraid
    private int getAvgGray(int posX, int posY, int blockWidth, int blockHeight) {
        int avgGray = 0;
        for (int i = posX; i < posX + blockWidth; i++) {
            for (int j = posY; j < posY + blockHeight; j++) {
                Color color = new Color(image.getRGB(i, j));
                avgGray += color.getRed();
            }
        }
        return avgGray / (blockHeight * blockWidth);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void toGrayscale(String method) {
        Color color;
        int gray;

        switch (method) {
            case "average":
                for(int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        color = new Color(image.getRGB(i, j));
                        gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                        image.setRGB(i, j, new Color(gray, gray, gray).getRGB());
                    }
                }
                break;
            case "lightness":
                for(int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        color = new Color(image.getRGB(i, j));
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        gray = (max(max(red, green), blue) + min(min(red, green), blue)) / 2;
                        image.setRGB(i, j, new Color(gray, gray, gray).getRGB());
                    }
                }
                break;
            case "luminosity":
                for(int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        color = new Color(image.getRGB(i, j));
                        gray = (int)(color.getRed() * 0.21 + color.getGreen() * 0.72 + color.getBlue() * 0.07);
                        image.setRGB(i, j, new Color(gray, gray, gray).getRGB());
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("\"" + method + "\" is not a valid argument!");
        }
    }
}