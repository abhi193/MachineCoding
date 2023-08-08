package data;

public class Over {

    private int completedOvers;
    private int ballsInCurrentOver;

    public Over() {
        this.completedOvers=0;
        this.ballsInCurrentOver=0;
    }

    public int getCompletedOvers() {
        return completedOvers;
    }

    public int getBallsInCurrentOver() {
        return ballsInCurrentOver;
    }

    public void update(){
        if(ballsInCurrentOver==5){
            this.completedOvers++;
            this.ballsInCurrentOver=0;
        }else {
            this.ballsInCurrentOver++;
        }
    }
}
