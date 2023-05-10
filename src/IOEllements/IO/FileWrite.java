package IOEllements.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileWrite {
    public static void WriteInFile(String path, String line) throws FileNotFoundException {
        try (FileOutputStream outstream = new FileOutputStream(System.getProperty("user.dir") + "\\" + path, true)) {
            outstream.write(("\n" + line).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
