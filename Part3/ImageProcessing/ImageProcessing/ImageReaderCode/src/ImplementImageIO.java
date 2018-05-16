// ImplementImageIO.java
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {

    private int width, height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image myRead(String filePath) throws IOException {

        // bitmap-file header, includes 14 bytes
        byte[] fileHeader = new byte[14];

        // bitmap-information header, includes 40 bytes
        byte[] infoHeader = new byte[40];

        /**
         * read the file's bitmap-file header and bitmap-infomation header 
         * into the fileHeader arrays and infoHeader arrays
         */
        FileInputStream input = new FileInputStream(filePath);
        input.read(fileHeader, 0, 14);
        input.read(infoHeader, 0, 40);


        // get the width of the bitmap
        width = num(infoHeader[4], 0) + num(infoHeader[5], 8) + num(infoHeader[6], 16) + num(infoHeader[7], 24);
        
        // get the height of the bitmap
        height = num(infoHeader[8], 0) + num(infoHeader[9], 8) + num(infoHeader[10], 16) + num(infoHeader[11], 24);
        
        // get the bit count of the bitmap
        int bitCount = num(infoHeader[14], 0) + num(infoHeader[15], 8);
        
        // get the size of the image
        int size = num(infoHeader[20], 0) + num(infoHeader[21], 8) + num(infoHeader[22], 16) + num(infoHeader[23], 24);
        
        /**
         * get the color of the image
         */
        int []color = new int[size];
        if (bitCount == 24) {
            int nullBlock = 4 -  width * 3 % 4;
            nullBlock = (nullBlock == 4) ? 0 : nullBlock;

            byte[] temp = new byte[size];
            input.read(temp, 0, size);

            int index = 0;
            for (int i = 0 ; i < height ; i++) {
                for (int j = 0 ; j < width ; j++) {
                    // from down to up, from left to right
                    color[width*(height-1-i) + j] = num(temp[index+2], 16) + num(temp[index+1], 8) + num(temp[index], 0) + (255<<24);
                    index += 3;
                }
                index += nullBlock;
            }
        } 
        else {
            throw new IllegalArgumentException("the bitCount isn't 24");
        }
        
        /**
         * return the image
         */
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, color, 0, width));
    }

    // get num from byte
    private int num(byte a, int carry) {
        return (carry == 0) ? (a & 0xff) : ((a & 0xff) << carry);
    }

    /* write the image by the imageio of java */
    public Image myWrite(Image image, String filePath) throws IOException {
        File newBmpFile = new File(filePath + ".bmp");
        BufferedImage bufferImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics drawer = bufferImage.createGraphics();

        drawer.drawImage(image, 0, 0, null);
        drawer.dispose();
        ImageIO.write(bufferImage, "bmp", newBmpFile);

        return image;
    }
}