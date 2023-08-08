package scoreboard;

public class GameScoreBoard implements IScoreBoard{


    private final IScoreBoard battingScoreBoard;
    private final IScoreBoard bowlingScoreBoard;

    public GameScoreBoard(IScoreBoard battingScoreBoard, IScoreBoard bowlingScoreBoard) {
        this.battingScoreBoard = battingScoreBoard;
        this.bowlingScoreBoard = bowlingScoreBoard;
    }
    public void display(){
        battingScoreBoard.display();
        bowlingScoreBoard.display();
    }

    public void update(String ballOutput){
        battingScoreBoard.update(ballOutput);
        bowlingScoreBoard.update(ballOutput);
    }
}
