package IOEllements.Interface;

import IOEllements.IO.FileWrite;
import IOEllements.Interface.Details.Color;
import IOEllements.Interface.Details.Frame;
import IOEllements.Interface.Details.Text;
import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.util.Scanner;

public class Registration {
    private static final Frame rootFrame = new Frame(0, 0, 40, 156, ' ', Color.ANSI_WHITE, Color.ANSI_WHITE_BACKGROUND);
    private static final Text title = new Text(55,10,"REGISTRATION", Color.ANSI_RESET, "");
    private static final Text name = new Text(40, 13, "Name: ", Color.ANSI_RESET, "");
    private static final Text surname = new Text(40,15, "Surname: ", Color.ANSI_RESET, "");
    private static final Text phoneNumber = new Text(40, 17, "PhoneNumber: ", Color.ANSI_RESET, "");
    private static final Text login = new Text(40, 19, "Login", Color.ANSI_RESET, "");
    private static final Text password = new Text(40, 21, "Password", Color.ANSI_RESET, "");
    public static void Draw(){
        RootConsole.clearConsole();
        rootFrame.draw();
        title.draw();
        name.draw();
        surname.draw();
        phoneNumber.draw();
        login.draw();
        password.draw();
    }

    public static void Update(Database database){ //Необходимо обработать неправильные значения
        Scanner scanner = new Scanner(System.in);
        StringBuilder data = new StringBuilder();

        Registration.Draw();

        name.pointerAfter();
        data.append(scanner.next());

        surname.pointerAfter();
        data.append(" ");
        data.append(scanner.next());

        phoneNumber.pointerAfter();
        data.append(" ");
        data.append(scanner.next());

        //Отправка в БД

        login.pointerAfter();
        data.append(scanner.next());

        password.pointerAfter();
        data.append(" ");
        data.append(scanner.next());
        //Отправка в БД


        HomeScreen.Update(database);
    }
}