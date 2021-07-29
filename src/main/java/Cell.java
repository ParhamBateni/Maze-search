import javafx.scene.shape.Rectangle;

public class Cell {
    private int i,j;
    private int greedyValue;
    private boolean isWall;
    private Rectangle rectangle;
    public Cell(int i,int j,boolean isWall,Rectangle rectangle,int[]endCoordinates){
        this.i=i;
        this.j=j;
        this.isWall=isWall;
        this.rectangle=rectangle;
        greedyValue=Math.abs(i-endCoordinates[0])+Math.abs(j-endCoordinates[1]);
    }

    public int getGreedyValue() {
        return greedyValue;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isWall() {
        return isWall;
    }
}
