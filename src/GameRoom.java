import Helpers.ConsoleBackground;
import Helpers.ConsoleForeground;

import java.util.*;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.ReadInt;

public class GameRoom {

    private final List<Poste> postes = new ArrayList<Poste>();
    private final List<Reservation> reservations = new ArrayList<Reservation>();
    private final List<Games> GamesList= new ArrayList<Games>(); // Games list
    private final Map<Poste,List<Reservation>> waiting=new HashMap<>();



public GameRoom(){
    Games fifa = new Games(GameCategory.Sport, "FIFA");
    GamesList.add(fifa);
    Games pes = new Games(GameCategory.Sport, "PES");
    GamesList.add(pes);
    Games callofduty = new Games(GameCategory.Fps, "Call Of Duty");
    GamesList.add(callofduty);
    Games halo = new Games(GameCategory.Fps, "Halo Infinite");
    GamesList.add(halo);
    Games blackdesert = new Games(GameCategory.Rpg, "Black Desert Online");
    GamesList.add(blackdesert);
    Games assassincreed = new Games(GameCategory.Rpg, "ASSASIN'S CREED");
    GamesList.add(assassincreed);

    // Initialising the Posts

    Poste p1=new Poste(Console.Xbox, Tv.HP,Arrays.asList(fifa,pes));
    postes.add(p1);
    waiting.put(p1,new ArrayList<Reservation>());
    Poste p2=new Poste(Console.Xbox, Tv.Dell,Arrays.asList(callofduty,halo));
    postes.add(p2);
    waiting.put(p2,new ArrayList<Reservation>());
    Poste p3=new Poste(Console.Xbox, Tv.Asus,Arrays.asList(blackdesert,assassincreed));
    postes.add(p3);
    waiting.put(p3,new ArrayList<Reservation>());
    Poste p4=new Poste(Console.Playstation_5, Tv.Asus,Arrays.asList(fifa,pes));
    postes.add(p4);
    waiting.put(p4,new ArrayList<Reservation>());
    Poste p5=new Poste(Console.Playstation_5, Tv.Samsung,Arrays.asList(pes,assassincreed));
    postes.add(p5);
    waiting.put(p5,new ArrayList<Reservation>());
    Poste p6=new Poste(Console.Playstation_5, Tv.Dell,Arrays.asList(callofduty,assassincreed));
    postes.add(p6);
    waiting.put(p6,new ArrayList<Reservation>());

}
public Games SelectGame(){
int choice= -1;
do{
    Print("");
    Print("---------- Select the game ----------", ConsoleForeground.PURPLE);
        for (int i = 0; i<GamesList.size();i++){
            Print((i+1)+". "+GamesList.get(i).getGameName()+".");
        }
    Print((GamesList.size()+1)+". Quit.");
        choice=ReadInt("Select The Game : ");
        if(choice<1 || choice >(GamesList.size()+1)){
            Print("Choice Invalid.", ConsoleForeground.RED);
        }
    }while(choice<1 || choice >(GamesList.size()+1));
        if(choice<= GamesList.size()){
//    System.out.println(choice);
        return GamesList.get(choice-1);

}
return  null;
}
    public Period SelectPeriod(){
        // menu de selection periode
        return Period.SelectPeriod();
    }
public Player SelectPlayer(){
    return Player.NewPlayer();
}
public void AddReservation(){
    Player player = SelectPlayer();
        if(player==null){
            Print(" Operation Annulled", ConsoleBackground.RED);
        return;
}
    Games games = SelectGame();
        if(games==null){
            Print(" Operation Annulled",ConsoleBackground.RED);
        return;
}
        Period period= SelectPeriod();

        if(period==null){
            Print(" Operation Annulled",ConsoleBackground.RED);
            return;
        }

        Reservation reservation=new Reservation(player,period,games);
//        ReservationState reservationState=VerifyReservation(reservation);
}

//    public ReservationState VerifyReservation(Reservation reservation){
//
//    }
}