package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ScreenshotUtil {

  private static final String BASE_DIR =
      "target/surefire-reports/screenshots";
  private static final DateTimeFormatter DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_TIME_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

  private ScreenshotUtil() {
  }

  public static byte[] takeScreenshot(WebDriver driver, String testName) {
    String dateFolder = LocalDate.now().format(DATE_FORMAT);
    String timestamp = LocalDateTime.now().format(DATE_TIME_FORMAT);
    String safeTestName = testName.replaceAll("[^a-zA-Z0-9-_.]", "_");
    Path directory = Paths.get(BASE_DIR, dateFolder);
    Path destination = directory.resolve(safeTestName + "_" + timestamp + ".png");

    try {
      Files.createDirectories(directory);

      byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      Files.write(destination, screenshotBytes);

      log.info("Screenshot saved locally: {}", destination.toAbsolutePath());

      return screenshotBytes;

    } catch (IOException e) {
      log.error("Failed to save screenshot for test {}", testName, e);
      return null;
    }
  }
}