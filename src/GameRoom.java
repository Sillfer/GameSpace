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
    Games mario = new Games(GameCategory.Rpg, "Mario Odyssey" );
    GamesList.add(mario);
    Games supersmash = new Games(GameCategory.Fighting, "Super Smash Bros. Ultimate");
    GamesList.add(supersmash);

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
    Poste p7=new Poste(Console.Nintendo_Switch, Tv.HP,Arrays.asList(assassincreed,mario));
    postes.add(p7);
    Poste p8=new Poste(Console.Nintendo_Switch, Tv.Samsung,Arrays.asList(mario,supersmash));
    postes.add(p8);

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
// Menu to add the player
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
        ReservationState reservationState=VerifyReservation(reservation);

        switch (reservationState){
            case ENDED:
                Print("============================================",ConsoleForeground.RED, ConsoleBackground.BLACK);
                Print("|    Reservation impossible for today.     |",ConsoleForeground.RED,ConsoleBackground.BLACK);
                Print("============================================",ConsoleForeground.RED,ConsoleBackground.BLACK);
                break;
            case In_QUEUE:
                Poste p=reservation.getPoste();
                if(waiting.get(p).size()<10){ //get(p) = poste -----> return value = list of reservations for that post
                    reservations.add(reservation);
                    waiting.get(p).add(reservation);

                 Print("============================================",ConsoleForeground.MAGENTA, ConsoleBackground.BLACK);
                 Print("|                IN QUEUE                  |",ConsoleForeground.MAGENTA,ConsoleBackground.BLACK);
                 Print("============================================",ConsoleForeground.MAGENTA,ConsoleBackground.BLACK);
                 Print(reservation.toString(),ConsoleForeground.MAGENTA,ConsoleBackground.BLACK);
                 Print("============================================",ConsoleForeground.MAGENTA, ConsoleBackground.BLACK);
                }else {
                    Print("Reservation annuled . The Queue is full", ConsoleForeground.RED,ConsoleBackground.BLACK);
                }
                break;

            case IN_POST:
                reservations.add(reservation);
                reservation.getPoste().setInProgress(reservation);
                Print("===============================================",ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                Print("|                    IN POST                  |",ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                Print("===============================================",ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                Print(reservation.toString(),ConsoleForeground.GREEN,ConsoleBackground.BLACK);

                Print("============================================",ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                break;
        }
}
// Reservation verification
    public ReservationState VerifyReservation(Reservation reservation){
//            Return the reservation state and assign if it's not annulled

        Calendar DateLimit=Calendar.getInstance();
        DateLimit.set(Calendar.HOUR_OF_DAY,20);
        DateLimit.set(Calendar.MINUTE,00);

        List<Poste> gamePosts=this.getGamePost(reservation.getGames());
        Calendar Mindate;
        if(Calendar.getInstance().compareTo(DateLimit)<0){
//        Post is available
            for (int i = 0; i < gamePosts.size(); i++){
                if(!gamePosts.get(i).Reserved()){
                    reservation.setPoste(gamePosts.get(i));
                    reservation.setUtulisationStart(reservation.getReservationDate());
                    if(reservation.getUtulisationEnd().compareTo(DateLimit)<=0){
                        return ReservationState.IN_POST;
                    }else {
                        return ReservationState.ENDED;
                    }
                }
            }
            //------------------- MAX -----------------------------------------------------------------
            List<PosteCalendar> MaxDates= new ArrayList<>();  //This is to stock the max date for the poste

            for (int i = 0; i < gamePosts.size(); i++){ // key : get(gamePosts.get(i))
            List<Reservation> reservationsPoste = waiting.get(gamePosts.get(i)); // Return the reservation list for the post

            Optional<Reservation> MaxPosteReservation = reservationsPoste.stream().max(new Comparator<Reservation>() {
                @Override
                public int compare(Reservation o1, Reservation o2) {
                    return o1.getUtulisationEnd().compareTo(o2.getUtulisationEnd());
                }
            });

            if(MaxPosteReservation.isPresent()){ // if the max exists (Players exist in the queue of this post)
                MaxDates.add(new PosteCalendar(gamePosts.get(i), MaxPosteReservation.get().getUtulisationEnd()));
            }else{
                // No one in the the queue
                MaxDates.add(new PosteCalendar(gamePosts.get(i), gamePosts.get(i).getInProgress().getUtulisationEnd()));
            }
            }
            //---------------------------MIN -------------------------------------------------------------

                Optional<PosteCalendar> selectedPoste = MaxDates.stream().min(new Comparator<PosteCalendar>() {
                    @Override
                    public int compare(PosteCalendar o1, PosteCalendar o2) {

                        return o1.getDate().compareTo(o2.getDate()); // Return the min Date
                    }
                });

            if(selectedPoste.isPresent()){
                reservation.setPoste(selectedPoste.get().getPoste());
                reservation.setUtulisationStart(selectedPoste.get().getDate()); // Start Date
                if(reservation.getUtulisationEnd().compareTo(DateLimit)<= 0){ // The case where the period chosen is < = 20
                        return ReservationState.In_QUEUE;
                }
            }

        }
            return ReservationState.ENDED;
        }

//        --------------------------------------------------------------------------------------

        public List<Poste> getGamePost(Games games){
            List<Poste> gamePosts=new ArrayList<Poste>();

            for(int i = 0; i<this.postes.size();i++){
                if (this.postes.get(i).ContainsGame(games)){
                    gamePosts.add(this.postes.get(i));
                }
            }
            return gamePosts;
        }

    public void ControlePeriodique(){
        //Calling the function controlePeriodique of postes and assigning a player of the queue list to it
        for (int i =0 ; i<postes.size();i++){
            if(postes.get(i).ControlPeriodique()){
                if(waiting.get(postes.get(i)).size()>0){
                // taking a reservation of the queue list and making it in place in the poste and deleting of the list
                    Reservation r=waiting.get(postes.get(i)).get(0);
                    waiting.get(postes.get(i)).remove(0);
                    postes.get(i).setInProgress(r);
                }
            }
        }
    }

    public float CalculateGainDay(){
        int day;
        int month;
//        int year;
        Print("\n--------- Calculation of the day --------- : ");
        day=ReadInt("Enter the day : ");
        month=ReadInt("Enter the month : ");

        float Total=0;

        for (int  i=0; i < reservations.size();i++){
            Calendar date=reservations.get(i).getReservationDate();
            if(date.get(Calendar.DAY_OF_MONTH)==day && date.get(Calendar.MONTH)+1 == month){
                Total+=reservations.get(i).getPeriod().getPrice();
            }
        }
        Print("The Total money attained of the day("+day+"/"+month+") is : "+Total+" DH.",ConsoleForeground.GREEN);
        return Total;
}
public float CalculateGainMonth(){
    int month;
    int year;

    Print("\n--------- Calculation of the month --------- : ");

    month=ReadInt("Enter the month : ");
    year=ReadInt("Enter the year : ");

    float Total=0;

    for(int i =0; i< reservations.size(); i++){
        Calendar date=reservations.get(i).getReservationDate();
        if(date.get(Calendar.MONTH)+1==month && date.get(Calendar.YEAR)==year){
            Total+=reservations.get(i).getPeriod().getPrice();
        }
    }
    Print("The Total money attained of the month("+month+"/"+year+") is : "+Total+" DH.",ConsoleForeground.GREEN);
    return Total;
}
}


