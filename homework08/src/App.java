import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            Path filePath = Paths.get("src/input.txt");
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty()) continue;

                if (line.equals("END")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}