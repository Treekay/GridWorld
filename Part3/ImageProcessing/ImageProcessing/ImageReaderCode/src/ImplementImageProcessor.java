// ImplemetnImageProcess.java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {
    /* change the picture into a red chanel and show in a new window */
    public Image showChanelR(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new RedFilter()));
    }
    /* change the picture into a green chanel and show in a new window */
    public Image showChanelG(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new GreenFilter()));
    }
    /* change the picture into a blue chanel and show in a new window */
    public Image showChanelB(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new BlueFilter()));
    }
    /* change the picture into a gray chanel and show in a new window */
    public Image showGray(Image sourceImage) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(sourceImage.getSource(), new GrayFilter()));
    }
    /* the fliter change the picture's rgb into a red chanel */
    private class RedFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xffff0000;
        }
    }
    /* the fliter change the picture's rgb into a greenchanel */
    private class GreenFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff00ff00;
        }
    }
    /* the fliter change the picture's rgb into a blue chanel */
    private class BlueFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff0000ff;
        }
    }
    /* the fliter change the picture's rgb into a gray chanel */
    private class GrayFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            int red = (int)(((rgb & 0x00ff0000) >> 16) * 0.299);
            int green = (int)(((rgb & 0x0000ff00) >> 8) * 0.587);
            int blue = (int)(((rgb & 0x000000ff)) * 0.114);
            int gray = red + green + blue;
            return 0xff000000 +( gray << 16) + (gray << 8) + gray;
        }
    }
}