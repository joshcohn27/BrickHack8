package game;

public class Player {
    private final String name;
    private int points;
    private int level;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.level = 1;
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