import Helpers.ConsoleForeground;

import java.util.Timer;
import static Helpers.ConsoleHelper.*;
public class Main {
    public static void main(String[] args) {
    GameRoom gameRoom = new GameRoom();
    VerificationTimer ControlePeriodique=new VerificationTimer(gameRoom);
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(ControlePeriodique,0,60*1000); //min

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
//            case 2:
        }
    }while (choice!=3);
    timer.cancel();
    }
}