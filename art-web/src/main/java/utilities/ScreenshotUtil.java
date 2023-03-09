package utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtil {
    
    private static final String FILE_TYPE = "jpg";

    // Capture screen and compress the captured image into a 5-5-5 RGB image
    // @param element - this is an instance for taking screenshot
    // @return byte array for the compressed image

    private static byte[] captureScreen(TakesScreenshot element) {
        byte[] rawData = element.getScreenshotAs(OutputType.BYTES);
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        //Compress the png data
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new ByteArrayInputStream(rawData));
            if(originalImage == null) {
                return rawData;
            }
            BufferedImage compressImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
            Graphics2D g2d = (Graphics2D) compressImage.getGraphics();
            g2d.drawImage(originalImage, 0, 0, null);
            ImageIO.write(compressImage, FILE_TYPE, result);
        } catch(IOException e) {
            throw new RuntimeException("Exception occurred when taking screenshot", e);
        }
        return result.toByteArray();
    }

    public static String adjustFileName(String filename) {
        DecimalFormat format = new DecimalFormat("0000");
        String prefix = format.format(noOfStep.get());
        return prefix + "-" + filename.replace(" ", "-") + "." + FILE_TYPE;
    }

    public static synchronized void saveScreenshotForStep(String filename) {
        String adjustedFilename = adjustFileName(filename);
        byte[] picData = captureScreen((TakesScreenshot) DriverContext.getDriver());
        String folder = System.getProperty("user.dir") + File.separator + "output" + File.separator + "screenshots" + File.separator + Base.getCaseID();
        File subfolder = new File(folder);
        if(!subfolder.exists()) {
            subfolder.mkdirs();
        }
        File file = new File(subfolder, adjustedFilename);
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(picData);
            String replacedString = folder + File.separator;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }
        increaseNoOfSteps();
    }

    private static void increaseNoOfSteps() {
        noOfStep.set(noOfStep.get() + 1);
    }

    public static void addScreenShot() {
        TakesScreenshot ts = (TakesScreenshot) DriverContext.getDriver();
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        myScenario.attach(screenshot, "image/png", "image");
    }
}
