package framework.properties;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class UserProperty {
  private static ResourceBundle resourceBundle = getEnvironmentPropertiesFilePath();

  private static ResourceBundle getEnvironmentPropertiesFilePath() {
    return ResourceBundle.getBundle("userCredentials_" + EnvironmentProperty.getEnvProperty());
  }

  public static String getValueOf(String key) {
    String bundleValue = resourceBundle.getString(key);
    return new String(bundleValue.getBytes(StandardCharsets.UTF_8));
  }
}
