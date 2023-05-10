package IOEllements.Interface;

import IOEllements.Interface.Details.*;
import InfoElements.Node;
import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.util.ArrayList;
import java.util.Scanner;

public class PersonalAccount {
    Node account;
    private String[] userData;
    private ArrayList<String> reservations = new ArrayList<>();
    private static final Frame rootFrame = new Frame(0, 0, 40, 156, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text title = new Text(5,3,"ACCOUNT", Color.ANSI_RESET, "");
    private static final Text idLabel = new Text(5, 5, "ID: ", Color.ANSI_RESET, "");
    private static final Text idUser = new Text(9, 5, "", Color.ANSI_RESET, "");
    private static final Text nameLabel = new Text(5, 7, "Name: ", Color.ANSI_RESET, "");
    private static final Text nameUser = new Text(11, 7, "", Color.ANSI_RESET, "");
    private static final Text surnameLabel = new Text(5,9, "Surname: ", Color.ANSI_RESET, "");
    private static final Text surnameUser = new Text(14,9, "", Color.ANSI_RESET, "");
    private static final Text phoneNumberLabel = new Text(5, 11, "Phone number: ", Color.ANSI_RESET, "");
    private static final Text phoneNumberUser = new Text(19, 11, "", Color.ANSI_RESET, "");
    private static final Frame terminalFrame = new Frame(0, 28, 3 ,13, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text terminal = new Text(3, 29, "Terminal", Color.ANSI_RESET, "");
    private static final Frame inputFrame = new Frame(13, 28, 3, 142, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Frame userFrame = new Frame(0,0, 29, 38, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text tableHeader = new Text(40, 3, "ID UserID Date Time Minutes Guests", Color.ANSI_RESET, "");
    /*    private static final Text activereservation1 = new Text();*/
    public PersonalAccount(Node data){
        this.account = data;
        userData = data.dataline.split(" ");
        idUser.setText(userData[0]);
        nameUser.setText(userData[1]);
        surnameUser.setText(userData[2]);
        phoneNumberUser.setText(userData[3]);
    }
    public void Draw(){
        rootFrame.draw();
        tableHeader.draw();
        title.draw();
        idLabel.draw();
        idUser.draw();
        nameLabel.draw();
        nameUser.draw();
        surnameLabel.draw();
        surnameUser.draw();
        phoneNumberLabel.draw();
        phoneNumberUser.draw();
        terminalFrame.draw();
        terminal.draw();
        inputFrame.draw();
        userFrame.draw();
        int y = 5;
        for (String line:
             reservations) {
            Pointer.set(40, y);
            System.out.print(Color.ANSI_RESET + Color.ANSI_RESET + line);
            y += 2;
        }
    }
    public void Update(Database database){

        this.reservations = database.findReservation(userData[0]);
        RootConsole.clearConsole();
        this.Draw();

        Scanner scanner = new Scanner(System.in);

        Pointer.set(15 ,29);
        switch(scanner.nextLine().toLowerCase()) {
            case "update":
                reservations = database.findReservation(userData[0]);
                this.Update(database);
            case "new":
                String temp;
                if ((temp = ReservationAdd.Update(database)) != null) {
                    database.addNewReservation(userData[0], temp);
                    this.Update(database);
                }
                this.Update(database);
            case "logout":
                HomeScreen.Update(database);
                break;
            case "close":
                System.exit(0);
                break;
            default:
                this.Update(database);
                break;
        }
    }
}
