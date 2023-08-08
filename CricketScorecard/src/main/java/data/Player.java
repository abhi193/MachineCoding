package data;

public class Player {

    private final String name;
    private PlayerData playerData;

    public Player(String name) {
        this.name = name;
        this.playerData = new PlayerData();
    }

    public String getName() {
        return name;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }
}
