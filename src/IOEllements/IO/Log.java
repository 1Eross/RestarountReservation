package IOEllements.IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Log {
    private static final String LOGFILE = "\\messagelog.txt";

    public static void Write(String text) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + LOGFILE, true)) {
            StringBuilder stringBuilder = new StringBuilder(text);
            stringBuilder.append("\n");
            fileOutputStream.write(stringBuilder.substring(0).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.print("Ошибка при создании потока вывода лога");
        }
    }

    public static void Clear() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + LOGFILE)) {
            fileOutputStream.write("".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.print("Ошибка при очистке лога");
        }
    }
}
