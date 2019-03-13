package nio;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class CopyMoveExample {
    public static void main(String[] args) {
        Path dir1Path = Paths.get("dir1");
        Path dir2Path = Paths.get("dir2");
        try {
            if (! Files.exists(dir1Path)) {
                dir1Path = Files.createDirectory(dir1Path);
            }
            if (! Files.exists(dir2Path)) {
                dir2Path = Files.createDirectory(dir2Path);
            }

            Path file1Path = Paths.get(dir1Path.toString() + "/file1.txt");
            Path file2Path = Paths.get(dir2Path.toString() + "/file2.txt");
            Path file3Path = Paths.get(dir2Path.toString() + "/file3.txt");

            Files.deleteIfExists(file1Path);
            Files.deleteIfExists(file2Path);
            Files.deleteIfExists(file3Path);

            file1Path = Files.createFile(file1Path);
            file2Path = Files.createFile(file2Path);

            assert file1Path.toFile().exists();
            assert file2Path.toFile().exists();

            FileWriter fw = new FileWriter(file1Path.toFile());
            fw.append("test123!");
            fw.flush();
            fw.close();


            Files.copy(file1Path, file2Path, StandardCopyOption.REPLACE_EXISTING);
            Files.move(file1Path, file3Path);

            Files.deleteIfExists(file1Path);
            Files.deleteIfExists(file2Path);
            Files.deleteIfExists(file3Path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
