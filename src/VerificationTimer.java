import java.util.TimerTask;

public class VerificationTimer extends TimerTask {
    private GameRoom gameRoom;

    //constructeur :
    public VerificationTimer(GameRoom gameRoom) {

        this.gameRoom=gameRoom;
    }

    @Override
    public void run() {

        this.gameRoom.ControlePeriodique();
    }
}
