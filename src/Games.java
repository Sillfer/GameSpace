import java.util.Objects;

public class Games {
    private GameCategory Category;
    private String GameName;

    //Constructor
    public Games(GameCategory Game,String Name){
      this.Category=Game;
      this.GameName=Name;
    }

    public String getGameName() {
        return this.GameName;
    }


    public boolean equals(Games s) {
        return Category == s.Category && Objects.equals(GameName, s.GameName);
    }

    @Override
    public String toString(){
    return "Game{"+"Category: "+Category +
            ", Name of the game: " + GameName + '\'' + '}';
    }
}
