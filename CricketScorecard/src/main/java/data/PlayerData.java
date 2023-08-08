package data;

public class PlayerData {

    private int score;
    private int fours;
    private int sixes;
    private int ballsfaced;
    private int wickets;

    public PlayerData() {
        this.score=0;
        this.fours=0;
        this.sixes=0;
        this.ballsfaced=0;
    }

    public void updateScore(int score){
        this.score+=score;
    }

    public void updateFours(){
        this.fours+=1;
    }

    public void updateSixes(){
        this.sixes+=1;
    }

    public void updateBallsFaced(){
        this.ballsfaced+=1;
    }

    public int getScore() {
        return score;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getBallsfaced() {
        return ballsfaced;
    }
}
