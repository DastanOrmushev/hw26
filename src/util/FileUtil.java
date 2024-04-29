package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path path;

    private FileUtil(String filename) {
        path = Paths.get("data/" + filename + ".json");
    }

    public static Movie

}
