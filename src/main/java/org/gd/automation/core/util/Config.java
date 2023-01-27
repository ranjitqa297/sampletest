package org.gd.automation.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  private static Config config;
  private static Properties properties;

  private Config() {
    String file = this.getClass().getClassLoader().getResource("config.properties").getFile();
    try (InputStream inputStream = new FileInputStream(file)) {
      properties = new Properties();
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Config initConfig() {
    if (config == null) {
      config = new Config();

    }
    return config;
  }

  public static String getConfig(final String propName) {
    return String.valueOf(properties.get(propName));
  }

  public static void setConfig(final String propName, final String propValue) {
    properties.setProperty(propName, propValue);
  }
}
