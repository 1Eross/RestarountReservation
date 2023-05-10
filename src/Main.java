import IOEllements.Interface.Details.Color;
import LogicalParts.Database;
import LogicalParts.RootConsole;

import java.io.IOException;
import java.util.Scanner;

import static LogicalParts.RootConsole.clearConsole;


public class Main {

    public static void main(String[] args)  throws IOException {
        Database database = new Database("login.txt", "clients.txt", "reservation.txt", "tables.txt");
        RootConsole.Update(database);
        System.out.print(Color.ANSI_RESET);
    }
}