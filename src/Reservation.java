//import java.rmi.server.RemoteServer;
import Helpers.ConsoleBackground;
import Helpers.ConsoleForeground;

import java.util.Calendar;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.padRight;
public class Reservation {

    private Player player;
    private Period period;
    private Games games;
    private Calendar reservationDate;
    private Calendar utulisationStart, utulisationEnd;
    private Poste poste;


    public Reservation(Player player, Period period, Games games){
        this.player=player;
        this.period=period;
        this.games=games;
        this.reservationDate=Calendar.getInstance();
        this.poste=null;
        this.utulisationStart=null;
        this.utulisationEnd=null;
    }

    public Period getPeriod(){
        return this.period;
    }
    public Calendar getReservationDate(){
        return this.reservationDate;
    }

    public void setUtulisationStart(Calendar date){
            this.utulisationStart=date;
            this.CalculateUtulisationEnd();
}

    public Calendar getUtulisationStart(){
        return this.utulisationStart;
    }

    public Calendar getUtulisationEnd(){
        return this.utulisationEnd;
    }

    public void setPoste(Poste poste){
        this.poste=poste;
    }

    public Poste getPoste(){
        return this.poste;
    }

    public Games getGames(){
        return this.games;
    }

    public void CalculateUtulisationEnd(){
        this.utulisationEnd=(Calendar) this.utulisationStart.clone();
        //Utulisation period of the poste by the user who reserved
        switch (this.period) {
            case Min30 -> {
                this.utulisationEnd.add(Calendar.MINUTE, 30);  //utulisation date end
//                this.utulisationStart.set(Calendar.MINUTE,30);
//                this.utulisationStart.set(Calendar.SECOND,0);


                // 30 min occupy : 9:47 -> end date is 10:17
                if (this.reservationDate.get(Calendar.HOUR_OF_DAY)>= 12 && this.reservationDate.get(Calendar.HOUR_OF_DAY)<14) {
                    Print("============================================",ConsoleForeground.RED, ConsoleBackground.BLACK);
                    Print("|    We're out of service.     |",ConsoleForeground.RED,ConsoleBackground.BLACK);
                    Print("============================================",ConsoleForeground.RED,ConsoleBackground.BLACK);
                     // If the reservation end time is between >=12
                    //We add 2 hours so that the reservation continues at 14h.
                }
                else if(this.utulisationStart.get(Calendar.HOUR_OF_DAY) < 12 && this.utulisationEnd.get(Calendar.HOUR_OF_DAY) >= 12){
                    this.utulisationEnd.add(Calendar.HOUR, 2);

//
                }

            }
            case Hours_1 -> {
                this.utulisationEnd.add(Calendar.HOUR, 1);
                if (this.utulisationStart.get(Calendar.HOUR_OF_DAY) < 12 && this.utulisationEnd.get(Calendar.HOUR_OF_DAY) >= 12) {
                    this.utulisationEnd.add(Calendar.HOUR, 2);
                }
            }
            case Hours_2 -> {
                this.utulisationEnd.add(Calendar.HOUR, 2);
                if (this.utulisationStart.get(Calendar.HOUR_OF_DAY) < 12 && this.utulisationEnd.get(Calendar.HOUR_OF_DAY) >= 12) {
                    this.utulisationEnd.add(Calendar.HOUR, 2);
                }
            }
            case Hours_5 -> {
                this.utulisationEnd.add(Calendar.HOUR, 5);
                if (this.utulisationStart.get(Calendar.HOUR_OF_DAY) < 12 && this.utulisationEnd.get(Calendar.HOUR_OF_DAY) >= 12) {
                    this.utulisationEnd.add(Calendar.HOUR, 2);
                }
            }
            case WholeDay -> {
                this.utulisationEnd.set(Calendar.HOUR_OF_DAY, 20);
                this.utulisationEnd.set(Calendar.MINUTE, 0);
                this.utulisationEnd.set(Calendar.SECOND, 0);
            }
        }
    }

public String toString(){
    return
        "| "+padRight("Player      = " + player, 43) +" |\n"+
        "| "+padRight("Period      = " + period, 43) +" |\n"+
        "| "+padRight("Game        = " + games.getGameName(), 43) + " |\n"+
        "| "+padRight("Reservation = " + reservationDate.getTime(), 43) + " |\n"+
        "| "+padRight("Start       = " + utulisationStart.getTime(), 43) + " |\n"+
        "| "+padRight("End         = " + utulisationEnd.getTime(), 43) + " |\n"+
        "| "+padRight("Poste      = " + poste, 43) + " |\n";
}
}