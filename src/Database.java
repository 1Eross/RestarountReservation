import InfoElements.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    private String loginTableName;
    public HashTable loginsBuffer;
    private String clientsTableName;
    public HashTable clientsBuffer;
    private String reservationTableName;
    public HashTable reservationBuffer;
    private String tablesTableName;
    private HashTable tablesBuffer;

    public Database(String loginTableName, String clientsTableName, String tablesTableName, String reservationTableName) {
        this.loginTableName = loginTableName;
        try {
            FileRead tempFileread = new FileRead(loginTableName);
            ArrayList<String> tempArray = tempFileread.readAll();
            loginsBuffer = new HashTable(tempArray.size());
            for (String line :
                    tempArray) {
                String[] tempBuff = line.split(" ");
                loginsBuffer.add(tempBuff[1] + tempBuff[2], tempBuff[0]);
            }
            tempFileread.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.clientsTableName = clientsTableName;
        try {
            FileRead tempFileread = new FileRead(clientsTableName);
            ArrayList<String> tempArray = tempFileread.readAll();
            clientsBuffer = new HashTable(tempArray.size());
            for (String line :
                    tempArray) {
                String[] tempBuff = line.split(" ");
                clientsBuffer.add(tempBuff[0], line);//Какой ключ идет

            }
            tempFileread.close();
        } catch (IOException e) {
            System.out.print("Не найден файл авторизации");
            this.reservationTableName = reservationTableName;
            //Считываем все и создаем хэщ-таблицу
            this.tablesTableName = tablesTableName;
            //Считываем все и создаем хэщ-таблицу
        }
    }

}
