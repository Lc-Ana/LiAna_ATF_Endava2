package configurations;

import enums.DriverType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "src/test/java/configurations/config.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties  not found " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else
            throw new RuntimeException("Driver Path not specified in the Config.properties file for the Key:driverPath");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else
            throw new RuntimeException("Application Url not specified in the Config.properties file for the key : url");
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
            //else if (browserName.equalsIgnoreCase("firefox")) return  DriverType.FIREFOX ;
        else
            throw new RuntimeException("Browser Name key value in Config.properties is not matched:  " + browserName);
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximaze");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) {
            try {
                return Long.parseLong(implicitlyWait);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Not able to parse value: " + implicitlyWait + " in to long.");
            }
        }
        return 35;
    }

    public String getDashboardURLEndpoint() {
        String dashboardURLEndpoint = properties.getProperty("dashboardURLEndpoint");
        if (dashboardURLEndpoint != null) return dashboardURLEndpoint;
        else
            throw new RuntimeException("Dashboard Url not specified in the Config.properties file for the key : dashboardURL");
    }

    public String getUser() {
        String user = properties.getProperty("user");
        return user;
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        return password;
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        return baseUrl;
    }

}
