package data;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Cell> cells;
    private final List<Player> players;
    private final List<Integer> currentPositionOfEachPlayer;
    private final List<Integer> currentScoreOfEachPlayer;
    private final Dice dice;
    private final int noOfMoves;

    public Board(int noOfPlayers, int noOfMoves, char[] cellSeq){
        this.currentPositionOfEachPlayer = new ArrayList<>();
        this.currentScoreOfEachPlayer = new ArrayList<>();
        this.noOfMoves = noOfMoves;
        this.dice = new Dice();
        this.players = initialisePlayers(noOfPlayers);
        this.cells = initialiseCells(cellSeq);
    }

    private List<Player> initialisePlayers(int noOfPlayers){
        List<Player> playerList = new ArrayList<>();

        for(int i=0;i<noOfPlayers;i++)
            playerList.add(new Player(i));

        for(int i=0;i<noOfPlayers;i++)
            this.currentPositionOfEachPlayer.add(-1);

        for(int i=0;i<noOfPlayers;i++)
            this.currentScoreOfEachPlayer.add(0);

        return playerList;
    }

    private List<Cell> initialiseCells(char[] cellSeq){
        List<Cell> cellList = new ArrayList<>();
        for (char c : cellSeq) {
            Cell cell;
            if (c == 'N')
                cell = new Cell(CellType.NORMAL, 5);
            else if (c == 'J')
                cell = new Cell(CellType.JAIL, -20);
            else if (c == 'H')
                cell = new Cell(CellType.HOTEL, -10);
            else if (c == 'T')
                cell = new Cell(CellType.TREASURE, 50);
            else
                throw new RuntimeException("Cell not valid");

            cellList.add(cell);
        }
        return cellList;
    }

    public void movePlayer(int index, int num){
        System.out.println("player "+index+" rolled "+num);
        int currentPosition = currentPositionOfEachPlayer.get(index);
        System.out.println("player "+index+" currentPosition "+currentPosition);
        int newPosition = (currentPosition + num) % cells.size();
        System.out.println("player "+index+" newPosition "+newPosition);
        int newScore = cells.get(newPosition).getValue();
        System.out.println("player "+index+" newScore "+newScore);
        currentPositionOfEachPlayer.set(index,newPosition);
        currentScoreOfEachPlayer.set(index,currentScoreOfEachPlayer.get(index)+newScore);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Integer> getCurrentPositionOfEachPlayer() {
        return currentPositionOfEachPlayer;
    }

    public List<Integer> getCurrentScoreOfEachPlayer() {
        return currentScoreOfEachPlayer;
    }

    public Dice getDice() {
        return dice;
    }

    public int getNoOfMoves() {
        return noOfMoves;
    }
}
