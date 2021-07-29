import javafx.scene.paint.Color;

public class Stack extends Queue{
    @Override
    public Node remove() {
        Node node=frontier.get(frontier.size()-1);
        if(!node.isEndOrStartOfThePath()) {
            node.state.getRectangle().setFill(Color.BLUE);
        }
        frontier.remove(node);
        return node;
    }
}
