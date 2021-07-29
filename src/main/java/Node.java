import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node {
    public static ArrayList<Node> nodes = new ArrayList<>();
    public Cell state;
    public Node parent;

    public Node(Cell state, Node parent) {
        this.parent = parent;
        this.state = state;
        nodes.add(this);
    }

    public static Node getNodeByCell(Cell cell) {
        for (Node node : nodes) {
            if (node.state.equals(cell)) {
                return node;
            }
        }
        return null;
    }

    public ArrayList<Node> getNeighbors(boolean isGreedy) {
        Cell[][] cells = Maze.cells;
        ArrayList<Node> neighbors = new ArrayList<>();
        int iState = state.getI();
        int jState = state.getJ();
        ArrayList<Integer> moves = Maze.getRandomMoves();
        for (int i : moves) {
            switch (i) {
                case 0: {
                    if (iState > 0 && !cells[iState - 1][jState].isWall()) {
                        neighbors.add(getNodeByCell(cells[iState - 1][jState]));
                    }
                    break;
                }
                case 1: {
                    if (iState < Maze.width - 1 && !cells[iState + 1][jState].isWall()) {
                        neighbors.add(getNodeByCell(cells[iState + 1][jState]));
                    }
                    break;
                }
                case 2: {
                    if (jState > 0 && !cells[iState][jState - 1].isWall()) {
                        neighbors.add(getNodeByCell(cells[iState][jState - 1]));
                    }
                    break;
                }
                case 3: {
                    if (jState < Maze.length - 1 && !cells[iState][jState + 1].isWall()) {
                        neighbors.add(getNodeByCell(cells[iState][jState + 1]));
                    }
                    break;
                }
            }
        }
        if(isGreedy) {
            Collections.sort(neighbors, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (o1.state.getGreedyValue() < o2.state.getGreedyValue()) return 1;
                    else return -1;
                }
            });
        }
        return neighbors;

    }

    public boolean isEndOrStartOfThePath() {
        if (state.getI() == 1 && state.getJ() == 1) {
            return true;
        } else if (state.getI() == Maze.width - 2 && state.getJ() == Maze.length - 2) {
            return true;
        }
        return false;
    }
}
