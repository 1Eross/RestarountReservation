package LogicalParts;

import HashUtilities.HashTable;
import IOEllements.IO.FileRead;
import InfoElements.Schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Database {
    private static final int TABLECOUNT = 25;

    private final String loginTableName;
    public HashTable loginsBuffer;

    private String clientsTableName;
    public HashTable clientsBuffer;

    private String reservationTableName;
    public HashTable reservationBuffer;
    public ArrayList<String> reservationList;

    private String tablesTableName;
    private HashTable tablesBuffer;

    public Database(String loginTableName, String clientsTableName, String reservationTableName, String tablesTableName) {


        this.loginTableName = loginTableName;
        try {

            FileRead tempFileread = new FileRead(loginTableName);
            ArrayList<String> tempArray = tempFileread.readAll();
            tempFileread.close();

            loginsBuffer = new HashTable(tempArray.size());
            for (String line :
                    tempArray) {
                String[] tempBuff = line.split(" ");
                loginsBuffer.add(tempBuff[1] + tempBuff[2], tempBuff[0]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        this.clientsTableName = clientsTableName;
        try {

            FileRead tempFileread = new FileRead(clientsTableName);
            ArrayList<String> tempArray = tempFileread.readAll();
            tempFileread.close();

            clientsBuffer = new HashTable(tempArray.size());
            for (String line :
                    tempArray) {
                String[] tempBuff = line.split(" ");
                clientsBuffer.add(tempBuff[0], line);//Какой ключ идет

            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }


        this.reservationTableName = reservationTableName;
        try {

            FileRead tempfileRead = new FileRead(reservationTableName);
            reservationList = tempfileRead.readAll();
            tempfileRead.close();
            HashSet<String> daysCount = new HashSet<>();
            for (String line: reservationList){
                String[] tempBuff = line.split(" ");
                daysCount.add(tempBuff[2]);
            }

            reservationBuffer = new HashTable(TABLECOUNT * daysCount.size() * Schedule.getPartsCount());

            for (String line
                    : reservationList) {
                String[] tempBuff = line.split(" ");
                reservationBuffer.add(tempBuff[0], line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        this.tablesTableName = tablesTableName;
        try {

            FileRead tempFileRead = new FileRead(tablesTableName);
            ArrayList<String> tempArray = tempFileRead.readAll();
            tempFileRead.close();

            tablesBuffer = new HashTable(tempArray.size());
            for (String line :
                    tempArray) {
                String[] tempBuff = line.split(" ");
                clientsBuffer.add(tempBuff[0], line);
            }
            tempFileRead.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
