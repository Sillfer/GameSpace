import Helpers.ConsoleForeground;
import Helpers.ConsoleHelper;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.ReadInt;

public enum Period {
    Min30("30min",5),
    Hours_1("1Hours",10),
    Hours_2("2Hours", 18),
    Hours_5("5Hours",40),
    WholeDay("WholeDay", 65);

    private final int price;
    private final String period;

    Period(String period, int price){
        this.price =price;
        this.period = period;


    }
    public int getPrice(){
        return this.price;
    }

    public String getPeriod() {
        return this.period;
    }

    public static Period SelectPeriod(){
    int choice =-1;
    do{
        Print("");
        Print("------- Period Selection -------", ConsoleForeground.PURPLE);
        Print("1: 30 Min");
        Print("2: 1 Hour");
        Print("3: 2 Hours");
        Print("4: 5 Hours");
        Print("5: Whole Day");
        Print("6: Quit");

        choice=ReadInt("Select The Period");
//        System.out.println(choice);
    }while(choice<1 || choice>6);
        switch(choice){
            case 1: return Min30;
            case 2: return Hours_1;
            case 3: return Hours_2;
            case 4: return Hours_5;
            case 5: return WholeDay;
            case 6: break;
            default:Print("Choice Invalid.",ConsoleForeground.RED);
            break;
        }
        return null;
    }
}
