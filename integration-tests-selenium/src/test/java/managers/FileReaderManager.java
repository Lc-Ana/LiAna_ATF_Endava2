package managers;

import configurations.ConfigFileReader;
import configurations.UserDetailsReader;

public class FileReaderManager {
    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileReader configFileReader;
    private static UserDetailsReader userDetailsReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public static ConfigFileReader getConfigReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }

    public static UserDetailsReader getUserDetailsReader() {
        return (userDetailsReader == null) ? new UserDetailsReader() : userDetailsReader;
    }

}
