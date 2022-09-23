import java.util.Calendar;

public class PosteCalendar {
    private Poste poste;
    private Calendar date; // date fin d'utilisation

    public  PosteCalendar(Poste poste,Calendar date){
        this.poste=poste;
        this.date=date;
    }

    public Calendar getDate() {
        return date;
    }

    public Poste getPoste() {
        return poste;
    }

    @Override
    public String toString() {
        return "PosteCalendar{" +
                "poste=" + poste +
                ", date=" + date.getTime() +
                '}';
    }
}
