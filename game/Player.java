package game;

// import java.util.ArrayList;
// import java.util.List;

public class Player {
    private final String name;
    private int points;
    private int level;
    // private List<PowerUp> powerUps;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.level = 1;
        // this.powerUps = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    // public List<PowerUp> getPowerUps() {
    //     return powerUps;
    // }

    // public void addPowerUp(PowerUp pup) {
    //     powerUps.add(pup);
    // }

    // public boolean usePowerUp(PowerUp pup) {
    //     if (powerUps.contains(pup)){
    //         powerUps.remove(pup); 
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    public void changePoints(int p) {
        points = points + p;
        if (points < 0) {
            points = 0;
        }
    }

    public void advanceLevel(){
        level++;
    }

    @Override
    public String toString() {
        return String.format("{Name: %s\nPoints: %d\nLevel: %d}", name, points, level);
    }
}