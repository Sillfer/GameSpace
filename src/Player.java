import Helpers.ConsoleForeground;

import static Helpers.ConsoleHelper.*;
import java.util.Scanner;

public class Player {
private static int __ID;
private int ID;
private String PlayerName;

public Player(String PlayerName){
Player.__ID++;
this.ID=Player.__ID;
this.PlayerName=PlayerName;
}

public static Player NewPlayer(){
    Print("");
    Print(" -------- Player Information -------- ",ConsoleForeground.PURPLE);
    do{
        String PlayerName = ReadString("Enter the player name : ");
    if(PlayerName.trim().length() >= 3 && PlayerName.trim().length() <= 10){
        return  new Player(PlayerName);
    }else{
        Print("The name is not respecting the rules.");
    }
    }while(true);
}

@Override
    public String toString() { return "CODE-"+ID;}
}
