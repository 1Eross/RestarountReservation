package IOEllements.Interface.Details;

import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.util.Scanner;

public class ReservationAdd {
    private static final Frame rootFrame = new Frame(0, 0, 40, 156, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text title = new Text(5,3,"NEW RESERVATION", Color.ANSI_RESET, "");
    private static final Text date = new Text(5, 5, "Date: ", Color.ANSI_RESET, "");
    private static final Text time = new Text(5, 7, "Time: ", Color.ANSI_RESET, "");
    private static final Text minutes = new Text(5, 9, "Minutes: ", Color.ANSI_RESET, "");
    private static final Text guests = new Text(5,11, "Guests: ", Color.ANSI_RESET, "");
    private static final Text table = new Text(5, 13, "Table: ", Color.ANSI_RESET, "");
    private static final Frame inputframe = new Frame(0, 0, 29 ,50, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Frame terminalFrame = new Frame(0, 28, 3 ,13, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text terminal = new Text(3, 29, "Terminal", Color.ANSI_RESET, "");
    private static final Frame inputFrame = new Frame(13, 28, 3, 142, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text errors = new Text(54,18, "", Color.ANSI_RED, "");
    public static void Draw(){
        rootFrame.draw();
        title.draw();
        date.draw();
        time.draw();
        minutes.draw();
        guests.draw();
        table.draw();
        inputframe.draw();
        terminalFrame.draw();
        terminal.draw();
        inputFrame.draw();
        errors.draw();
    }

    public static String Update(Database database){
        RootConsole.clearConsole();
        ReservationAdd.Draw();
        String[] tempbuff = new String[5];

        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        date.pointerAfter();
        tempbuff[0] = scanner.nextLine();
        stringBuilder.append(tempbuff[0]);
        stringBuilder.append(" ");


        time.pointerAfter();
        tempbuff[1] = scanner.nextLine();
        stringBuilder.append(tempbuff[1]);
        stringBuilder.append(" ");

        minutes.pointerAfter();
        tempbuff[2] = scanner.nextLine();
        stringBuilder.append(tempbuff[2]);
        stringBuilder.append(" ");

        guests.pointerAfter();
        tempbuff[3] = scanner.nextLine();
        stringBuilder.append(tempbuff[3]);
        stringBuilder.append(" ");

        table.pointerAfter();
        tempbuff[4] = scanner.nextLine();
        stringBuilder.append(tempbuff[4]);
        stringBuilder.append(" ");

        Pointer.set(15 ,29);
        switch (scanner.nextLine().toLowerCase()){
            case "accept":
                if ((database.CompareTime(tempbuff[0], tempbuff[1], tempbuff[2], tempbuff[4]))) {
                    return stringBuilder.substring(0);
                }
                else{
                    errors.setText("Wrong time");
                    return ReservationAdd.Update(database);
                }
            case "rewrite":
                return ReservationAdd.Update(database);
            default:
                return null;
        }

    }
}
