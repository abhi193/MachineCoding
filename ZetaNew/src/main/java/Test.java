import data.Game;

public class Test {

    public static void main(String[] args) {
        //5 -20 -10 50
        char[] cellSeq = new char[]{
                'N','J','H','T',
                'N','J','H','T',
                'N','J','H','T',
                'N','J','H','T',
                'N','J','H','T'
        };
        new Game(4,2,cellSeq).play();
    }
}
