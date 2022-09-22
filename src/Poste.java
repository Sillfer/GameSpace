import java.util.Calendar;
import java.util.List;
public class Poste {
    static int IDPOSTE = 1;
    int id;
    private Console console;
    private Tv tvscreen;
    private List<Games> games;
    private Reservation used;
    private boolean lightup;

//Constructor

public Poste(Console console, Tv tvscreen, List<Games> games){
this.id=Poste.IDPOSTE;
Poste.IDPOSTE++;

this.console=console;
this.tvscreen=tvscreen;
this.games=games;
this.used=null;
this.lightup=false;
}

// methods :

    //Function that works if the post is being used
public boolean Reserved(){
    return this.used!=null;
}

    public Reservation getInProgress(){
        return used;
    }

//Setting the atribute beingused (Rservation)
    public void setInProgress(Reservation reservation){
      this.used=reservation;
    this.Lightup();
    }

    public void Release(){
        this.used=null;
        this.SwitchOff();
    }



// Controlling the end of time for the utilisation of the post;
public boolean  ControlPeriodique(){
    if(this.Reserved()){
        Calendar Now=Calendar.getInstance(); // Get the current date

if(Now.get(Calendar.HOUR_OF_DAY) >= this.used.getUtulisationEnd().get(Calendar.HOUR_OF_DAY) &&
        Now.get(Calendar.MINUTE) >= this.used.getUtulisationEnd().get(Calendar.MINUTE)){
            this.CleanUp();
            return true;
        }
    }
    return false;
}
    public void CleanUp(){
        this.used=null;
        this.SwitchOff();
    }

    public void Lightup(){  // Message shown
        this.lightup=true;
    }

    public void SwitchOff(){ // Message shown
        this.lightup=false;
    }

    public boolean ContainsGame (Games game){
        for (int i=0;i<=this.games.size();i++){
            if(this.games.get(i).equals(game)){
                return true;
        }
    }
        return  false;
}

    public String toString(){
        return(String.valueOf(this.id));
    }

}
