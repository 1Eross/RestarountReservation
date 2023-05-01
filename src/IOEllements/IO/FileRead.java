package IOEllements.IO;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileRead {
    StringBuilder filePath = new StringBuilder(System.getProperty("user.dir") + "\\");
    File file;
    private BufferedReader bufferedReader;

    public FileRead(String fileName) {
        try {
            file = new File(filePath.append(fileName).toString());
            bufferedReader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readLines(int firstline, int valueOfLines) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        String line;
        for (int i = 0; i < firstline - 1; i++) {
            bufferedReader.readLine();
        }
        for (int i = 0; i < valueOfLines + 1; i++) {
            if ((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }
        }
        return arrayList;
    }

    public ArrayList<String> readAll() throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            arrayList.add(line);
        }
        return arrayList;
    }

    public String readNLine(int n) throws IOException {
        for (int i = 0; i < n; i++) {
            bufferedReader.readLine();
        }
        return bufferedReader.readLine();
    }

    public void close() throws IOException {
        bufferedReader.close();
    }
}
