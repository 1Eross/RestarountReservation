package LogicalParts;

import HashUtilities.HashFunction;
import HashUtilities.HashTable;
import IOEllements.IO.FileRead;
import IOEllements.IO.FileWrite;
import InfoElements.Node;
import InfoElements.Schedule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Database {
    private static final int TABLECOUNT = 15;

    private final String loginTableName;
    public HashTable loginsBuffer;

    private String clientsTableName;
    public HashTable clientsBuffer;

    private String reservationTableName;
    public HashTable reservationBuffer;
    public ArrayList<Node> reservationList = new ArrayList<>();

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
            ArrayList<String> tempList = tempfileRead.readAll();
            for (String line : tempList) {
                String[] tempBuff = line.split(" ");
                reservationList.add(new Node(tempBuff[1], line));
            }
            tempfileRead.close();

            HashSet<String> daysCount = new HashSet<>();
            for (Node line : reservationList) {
                String[] tempBuff = line.dataline.split(" ");
                daysCount.add(tempBuff[2]);
            }

            reservationBuffer = new HashTable(TABLECOUNT * daysCount.size() * Schedule.getPartsCount() * 2);

            for (Node line
                    : reservationList) {
                String[] tempBuff = line.dataline.split(" ");
                int[] temp = Schedule.refractorTime(tempBuff[3], tempBuff[4]);
                for (int i = temp[0]; i < temp[1] + temp[0]; i += Schedule.PARTITION) {
                    StringBuilder key = new StringBuilder(tempBuff[2]);
                    key.append(tempBuff[2]);
                    key.append(i);
                    key.append(tempBuff[6]);
                    reservationBuffer.add(key.substring(0), line.dataline);
                }
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

    public Node authorization(String loginPassword) {
        Node user = loginsBuffer.find(loginPassword);
        if (user != null) {
            return clientsBuffer.find(user.dataline);
        } else {
            return null;
        }
    }

    public ArrayList<String> findReservation(String id) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Node node :
                reservationList) {
            if (node.key.equals(id)) {
                arrayList.add(node.dataline);
            }
        }
        return arrayList;
    }

    public void addNewReservation(String key, String line) {
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(reservationList.size() + 1));
        stringBuilder.append(" ");
        stringBuilder.append(key);
        stringBuilder.append(" ");
        stringBuilder.append(line);
        reservationList.add(new Node(key, stringBuilder.substring(0)));
        try {
            FileWrite.WriteInFile(reservationTableName, stringBuilder.substring(0));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean CompareTime(String date, String time, String duration, String table) {
        int[] temp = Schedule.refractorTime(time, duration);
        for (int i = temp[0]; i < temp[0] + temp[1]; i += Schedule.PARTITION) {
            StringBuilder key = new StringBuilder(date);
            key.append(i);
            key.append(table);
            if (reservationBuffer.find(key.substring(0)) != null) {
                return false;
            }
        }
        return true;
    }
}