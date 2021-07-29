import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.management.timer.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Maze {

    public static Cell[][] cells;
    public static int width;
    public static int length;
    private int[] startCoordinates;
    private int[] endCoordinates;
    private String[][] map;
    private Thread timerThread;

    public Maze() {
        int width = 0;
        int length = 0;
        Random random = new Random();
        while (true) {
            length = random.nextInt(20);
            width = random.nextInt(20);
            if (length > 10 && width > 10) {
                break;
            }
        }
        this.length = 2 * length + 1;
        this.width = 2 * width + 1;
        this.map = new String[this.width][this.length];
        int[] start = new int[2];
        start[0] = 1;
        start[1] = 1;
        int[] end = new int[2];
        end[0] = this.width - 2;
        end[1] = this.length - 2;
        this.startCoordinates = start;
        this.endCoordinates = end;
        createRandomMap();
    }

    private void createRandomMap() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                if (j % 2 == 0 || i % 2 == 0) {
                    map[i][j] = "*";
                } else map[i][j] = "0";
            }
        }
        map[1][1] = "1";
        drawMaze(1, 1);
        removeRandomWalls();
        printMaze();


    }

    private void removeRandomWalls() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                if (map[i][j].equals("1"))
                    map[i][j] = " ";
            }
        }
        int countChosenWalls = 0;
        while (countChosenWalls < length * width / 15) {
            Random random = new Random();
            int i = random.nextInt(width);
            int j = random.nextInt(length);
            if (i == 0 || j == 0 || i == width - 1 || j == length - 1) continue;


                //condition for better maze shape
            else if (map[i][j].equals("*") && !((map[i + 1][j].equals("*") && map[i][j + 1].equals("*") &&
                    map[i - 1][j].equals("*") && map[i][j - 1].equals("*"))) &&
                    !((map[i + 1][j].equals(" ") && map[i][j + 1].equals(" ") &&
                            map[i - 1][j].equals(" ") && map[i][j - 1].equals(" "))) && !
                    (getCountNeighbors(i + 1, j) == 3 || getCountNeighbors(i - 1, j) == 3 ||
                            getCountNeighbors(i, j - 1) == 3 || getCountNeighbors(i, j + 1) == 3)) {
                map[i][j] = " ";
                countChosenWalls++;
            }
        }
    }

    private int getCountNeighbors(int i, int j) {
        int count = 0;
        try {
            if (map[i + 1][j].equals(" ")) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (map[i - 1][j].equals(" ")) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (map[i][j + 1].equals(" ")) {
                count++;
            }
        } catch (Exception e) {
        }
        try {
            if (map[i][j - 1].equals(" ")) {
                count++;
            }
        } catch (Exception e) {
        }
        return count;
    }

    private void drawMaze(int i, int j) {
        ArrayList<Integer> moves = getRandomMoves();
        for (Integer k : moves) {
            switch (k) {
                case 0: {
                    try {
                        if (map[i + 2][j].equals("0")) {
                            map[i + 2][j] = "1";
                            map[i + 1][j] = " ";
                            drawMaze(i + 2, j);
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }
                    break;
                }
                case 1: {
                    try {
                        if (map[i - 2][j].equals("0")) {
                            map[i - 2][j] = "1";
                            map[i - 1][j] = " ";
                            drawMaze(i - 2, j);
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }
                    break;
                }
                case 2: {
                    try {
                        if (map[i][j + 2].equals("0")) {
                            map[i][j + 2] = "1";
                            map[i][j + 1] = " ";
                            drawMaze(i, j + 2);
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }
                    break;

                }
                case 3: {
                    try {
                        if (map[i][j - 2].equals("0")) {
                            map[i][j - 2] = "1";
                            map[i][j - 1] = " ";
                            drawMaze(i, j - 2);
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }

                }
            }
        }


    }

    public static ArrayList getRandomMoves() {
        ArrayList moves = new ArrayList();
        moves.add(1);
        moves.add(2);
        moves.add(3);
        moves.add(0);
        Collections.shuffle(moves);
        return moves;
    }

    public void printMaze() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.length; j++) {
                if (map[i][j].equals("1"))
                    map[i][j] = " ";
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addMazeToPane(Pane pane) {
        cells = new Cell[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setX(20 * j);
                rectangle.setY(20 * i);
                if (map[i][j].equals("*")) {
                    rectangle.setFill(Color.BLACK);
                } else {
                    if (i == startCoordinates[0] && j == startCoordinates[1]) {
                        rectangle.setFill(Color.GREEN);
                    } else if (i == endCoordinates[0] && j == endCoordinates[1]) {
                        rectangle.setFill(Color.RED);
                    } else
                        rectangle.setFill(Color.WHITE);
                }
                pane.getChildren().add(rectangle);
                cells[i][j] = new Cell(i, j, map[i][j].equals("*"), rectangle,endCoordinates);
                if (i == width - 1 && j == length - 1) {
                    pane.setPrefHeight(rectangle.getY() + rectangle.getWidth());
                    pane.setPrefWidth(rectangle.getX() + rectangle.getHeight()+250);

                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                new Node(cells[i][j], null);
            }
        }
    }

    public void solve(boolean isDFS,boolean isGreedy) {
        Cell startCell = cells[1][1];
        Queue queue;
        if(isDFS)queue=new Stack();
        else queue = new Queue();
        queue.add(Node.getNodeByCell(startCell));
        ArrayList<Node> visitedNodes = new ArrayList<>();
        new Thread(() -> {
            Main.disableButtons();
            startTimer();
            while (true) {
                    if (queue.isEmpty()) {
                        System.out.println("queue emptied");
                        break;
                    }
                    Node child = queue.remove();
                    visitedNodes.add(child);
                    if (child.state.getI()==endCoordinates[0]&&child.state.getJ()==endCoordinates[1]) {
                        ArrayList<Node> path = new ArrayList<>();
                        path.add(child);
                        while (child.parent != null) {
                            path.add(child.parent);
                            child = child.parent;
                        }
                        Main.countPath.setText(Main.countPath.getText()+path.size());
                        Collections.reverse(path);
                        for (Node node:path){
                            if (!node.isEndOrStartOfThePath())
                            node.state.getRectangle().setFill(Color.CHARTREUSE);
                        }
                        System.out.println("solved");
                        break;
                    }
                    for (Node node : child.getNeighbors(isGreedy)) {
                        if (!visitedNodes.contains(node) && !queue.contains(node)) {
                            node.parent = child;
                            queue.add(node);
                        }
                    }
                    try {
                        int sleepTime=20;
//                        if(isDFS)
//                            sleepTime=50;
//                        else sleepTime=20;
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
            }
            endTimer();
            Main.activateButtons();
        }).start();

    }

    private void endTimer() {
        timerThread.stop();
    }

    public void startTimer(){
        timerThread=new Thread(()->{
            Long t0=System.currentTimeMillis();
            while (true) {
                Long t1=System.currentTimeMillis();
                Main.timer.setText("Timer>> "+(t1 - t0)/1000+":"+
                        (t1-t0)%1000/100);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });
        timerThread.start();
    }
    public void resetMaze(){
        for (int i = 0; i <width ; i++) {
            for (int j = 0; j <length ; j++) {
                if(!cells[i][j].isWall()&&!Node.getNodeByCell(cells[i][j]).isEndOrStartOfThePath())
                cells[i][j].getRectangle().setFill(Color.WHITE);
            }
        }
        Main.timer.setText("Timer>> 0:0");
        Main.countPath.setText("Path Length>> ");
    }
}
