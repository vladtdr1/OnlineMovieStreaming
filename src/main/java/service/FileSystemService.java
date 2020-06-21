package service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    public static String APPLICATION_FOLDER = "FletNix";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    public static Path getPathToFile(String... path) {
        APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER); //makes tests run in test folder
        return APPLICATION_HOME_PATH.resolve(Paths.get("", path));
    }

    public static Path getApplicationHomePath() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static void initApplicationHomeDir() {
        if (!Files.exists(getApplicationHomePath()))
            getApplicationHomePath().toFile().mkdirs();
    }
}