package scoreboard;

import data.Constants;
import data.Over;
import data.Player;
import data.Team;

public class BattingScoreBoard implements IScoreBoard {

    private final Team batting;
    private int score;
    private int wickets;
    private Over overs;

    private Player striker;
    private Player nonstriker;

    public BattingScoreBoard(Team batting) {
        this.batting = batting;
        this.score=0;
        this.wickets=0;
        this.overs=new Over();
        striker=batting.getPlayers().get(0);
        nonstriker=batting.getPlayers().get(1);
    }

    @Override
    public void display() {
        System.out.println("Batting scorecard for team "+batting.getName());
        for (int i=0;i<batting.getPlayers().size();i++){
            Player player = batting.getPlayers().get(i);
            System.out.println(player.getName()+" "+player.getPlayerData().getScore()+" "+
                    player.getPlayerData().getFours()+" "+player.getPlayerData().getSixes()
            +" "+player.getPlayerData().getBallsfaced());
        }
        System.out.println("Total:"+score+"/"+wickets);
        System.out.println("Overs:"+overs.getCompletedOvers()+"."+overs.getBallsInCurrentOver());
    }

    @Override
    public void update(String bowlOutput) {
        if(this.wickets==batting.getPlayers().size()-1)
            return;
        if (bowlOutput.equals(Constants.WICKET)){
            this.wickets++;
            updateScore(0);
            if(this.wickets==batting.getPlayers().size()-1)
                return;
            striker = batting.getPlayers().get(wickets+1);
        }else if(bowlOutput.equals(Constants.WIDE) || bowlOutput.equals(Constants.NO_BALL)){
            this.score++;
        }else {
            int scoreOnThisBowl = Integer.parseInt(bowlOutput);
            this.score+=scoreOnThisBowl;
            updateScore(scoreOnThisBowl);

            if(scoreOnThisBowl%2==1)
                changeStrike();

            if(overs.getBallsInCurrentOver()==0)
                changeStrike();
        }
    }

    private void changeStrike(){
        Player temp = striker;
        striker = nonstriker;
        nonstriker = temp;
    }

    private void updateBallsFaced(){
        striker.getPlayerData().updateBallsFaced();
    }

    private void updateOvers(){
        overs.update();
    }

    private void updateScore(int score){
        if(score==4)
            striker.getPlayerData().updateFours();
        if(score==6)
            striker.getPlayerData().updateSixes();
        striker.getPlayerData().updateScore(score);
        updateBallsFaced();
        updateOvers();
    }
}
