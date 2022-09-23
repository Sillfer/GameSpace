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
        Print("3: Quit");
    choice=ReadInt("Please choose an option : ");
        switch(choice){
            case 1: gameRoom.AddReservation();
            break;
            case 2:
                int StatisticChoice=-1;
                do{
                    Print("");
                    Print("---------- Statistic Choice ----------",ConsoleForeground.PURPLE);
                    Print("1: The Day");
                    Print("2: The Month");
                    Print("3: Quit");
                    StatisticChoice=ReadInt("Choose what type of statistics do you want : ");
                    switch (StatisticChoice){
                        case 1: gameRoom.CalculateGainDay();
                        break;
                        case 2: gameRoom.CalculateGainMonth();
                        break;
                    }
                }while (StatisticChoice<0 || choice>3);
        }
    }while (choice!=3);
    timer.cancel();
    }
}