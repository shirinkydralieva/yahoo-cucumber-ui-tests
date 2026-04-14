package framework.properties;

public class EnvironmentProperty {

  private static String env;
  private static String envProperty;

  private EnvironmentProperty() {
  }

  public static String getEnv(){
    setEnv();
    return env;
  }

  public static String getEnvProperty() {
    setEnv();
    return envProperty;
  }

  public static void setEnv(){
    envProperty = System.getProperty("env");
    if (envProperty == null){
      throw new IllegalArgumentException("Environment property 'env' is not set");
    }
    switch (envProperty){
      case "PROD":
        env = EnvironmentPropertyConst.PROD;
        break;
      default:
        throw new IllegalArgumentException("Unsupported environment " + envProperty);
    }
  }
}
