import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Queue {
    ArrayList<Node> frontier = new ArrayList<>();
    public static ArrayList<Node>removed=new ArrayList<>();


    public Queue() {

    }

    public void add(Node node) {
        frontier.add(node);
    }

    public Node remove() {
        Node node = frontier.get(0);
        if(!node.isEndOrStartOfThePath()) {
            node.state.getRectangle().setFill(Color.CYAN);
        }
        frontier.remove(0);
        removed.add(node);
        return node;
    }

    public boolean isEmpty() {
        return frontier.isEmpty();
    }

    public boolean contains(Node node) {
        return frontier.contains(node);
    }

}
