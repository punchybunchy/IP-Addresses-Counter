package punchybunchy.code.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static Stream<String> getLineFromFile(String path) {
        try {
            return Files.lines(getFullPath(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getFullPath(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File not found! Check the file path and try again.");
        }
        return path;
    }
}
