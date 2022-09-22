import Helpers.ConsoleForeground;

import java.util.Timer;
import static Helpers.ConsoleHelper.*;
public class Main {
    public static void main(String[] args) {
    GameRoom gameRoom = new GameRoom();
    Timer timer = new Timer(true);
//    timer.scheduleAtFixedRate();

    int choice=-1;
    do {
        Print("---------- Home Menu ----------", ConsoleForeground.PURPLE);
        Print("1: Add a reservation");
        Print("2: Statistics");
        Print("2: Quit");
    choice=ReadInt("Please choose an option : ");
        switch(choice){
            case 1: gameRoom.AddReservation();
            break;
        }
    }while (choice!=3);
    timer.cancel();
    }
}