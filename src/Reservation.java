import java.rmi.server.RemoteServer;
import java.util.Calendar;
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

    public Calendar getReservationDate(){
        return this.reservationDate;
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
}
