import data.Player;
import data.Team;
import game.Game;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Team india = new Team("INDIA");
        india.addPlayer(new Player("Sharma"));
        india.addPlayer(new Player("Gill"));
        india.addPlayer(new Player("Kohli"));
        india.addPlayer(new Player("Yadav"));
        india.addPlayer(new Player("Pandya"));

        Team pakistan = new Team("Pakistan");
        pakistan.addPlayer(new Player("Azam"));
        pakistan.addPlayer(new Player("Younis"));
        pakistan.addPlayer(new Player("Yousuf"));
        pakistan.addPlayer(new Player("Afridi"));
        pakistan.addPlayer(new Player("Amer"));

        Game INDvPAK = new Game(Arrays.asList(india,pakistan));

        List<String> INDBAT = Arrays.asList("1","1","1","1","1","2",
                "W","4","4","Wd","W","1","6");

        List<String> PAKBAT = Arrays.asList("4","6","W","W","1","1",
                "6","1","W","W","W","1");

        INDvPAK.play(INDBAT,PAKBAT);

    }
}
