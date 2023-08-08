package game;

import data.Team;
import scoreboard.BattingScoreBoard;
import scoreboard.BowlingScoreBoard;
import scoreboard.GameScoreBoard;
import scoreboard.IScoreBoard;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Team> teams; //1st team in list will bat first
    private List<IScoreBoard> scoreBoards;


    public Game(List<Team> teams) {
        this.teams = teams;
        this.scoreBoards = new ArrayList<>();
        for(int i=0;i<teams.size();i++){
            GameScoreBoard scoreBoard = new GameScoreBoard(new BattingScoreBoard(teams.get(i)),
                    new BowlingScoreBoard(teams.get((i+1)%teams.size())));
            this.scoreBoards.add(scoreBoard);
        }

    }

    public void play(List<String> bowlingOutputTeam1,List<String> bowlingOutputTeam2){
        for (String t1:bowlingOutputTeam1){
            scoreBoards.get(0).update(t1);
        }
        scoreBoards.get(0).display();

        for (String t2:bowlingOutputTeam2){
            scoreBoards.get(1).update(t2);
        }
        scoreBoards.get(1).display();
    }
}
