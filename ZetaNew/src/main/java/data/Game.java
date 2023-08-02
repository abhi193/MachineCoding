package data;

import java.util.List;

public class Game {

    private final Board board;

    public Game(int noOfPlayers, int noOfMoves, char[] cellSeq) {
        this.board = new Board(noOfPlayers,noOfMoves,cellSeq);
    }

    public void play(){
        int moves = board.getNoOfMoves();
        while (moves-->0) {
            for (int i = 0; i < board.getPlayers().size(); i++) {
                board.movePlayer(i,board.getDice().roll());
            }
        }
        int maxScore=Integer.MIN_VALUE,player=0;
        for (int i=0;i<board.getCurrentScoreOfEachPlayer().size();i++){
            if (maxScore<board.getCurrentScoreOfEachPlayer().get(i)){
                player=i;
                maxScore=board.getCurrentScoreOfEachPlayer().get(i);
            }
        }
        System.out.println("Player " +player+ " has won with score "+maxScore);
    }
}
