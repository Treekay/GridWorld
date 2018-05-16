// ImagaReaderRunner.java
import imagereader.Runner;

/**
 * This class runs the ImageReader
 */
public final class ImageReaderRunner {
    private ImageReaderRunner() {}
    public static void main(String[] args) {
        ImplementImageIO imageioer = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        Runner.run(imageioer, processor);
    }
}