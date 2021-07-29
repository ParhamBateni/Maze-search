import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    static ArrayList<Button> buttons;
    public static Text timer=new Text();
    public static Text countPath=new Text();
    static {
        buttons=new ArrayList<>();
    }
    private static Stage mazeStage;

    public static void disableButtons() {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    public static void activateButtons() {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        mazeStage = new Stage();
        mazeStage.setTitle("maze");
        mazeStage.getIcons().add(new Image("/maze.jpg"));
        createAndShowNewMaze();
    }

    private void createAndShowNewMaze() {
        Maze maze = new Maze();
        Pane pane = initializePane(maze);
        Scene scene = new Scene(pane);
        Main.timer.setText("Timer>> 0:0");
        Main.countPath.setText("Path Length>> ");
        mazeStage.setScene(scene);
        mazeStage.setResizable(false);
        mazeStage.centerOnScreen();
        mazeStage.show();
    }

    private Pane initializePane(Maze maze) {
        Pane pane = new Pane();
        maze.addMazeToPane(pane);
        addButtonsToPane(pane, maze);
        addLabelsToPane(pane);
        pane.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, null)));
        return pane;
    }

    private void addLabelsToPane(Pane pane) {
        timer.setLayoutX(pane.getPrefWidth()-180);
        timer.setFont(new Font(20));
        timer.setFill(Color.RED);
        timer.setLayoutY(pane.getPrefHeight()/2-80);
        countPath.setLayoutX(pane.getPrefWidth()-200);
        countPath.setFont(new Font(20));
        countPath.setFill(Color.RED);
        countPath.setLayoutY(pane.getPrefHeight()/2-120);
        pane.getChildren().add(timer);
        pane.getChildren().add(countPath);
    }

    private void addButtonsToPane(Pane pane, Maze maze) {
        javafx.scene.control.Button bfsButton = new javafx.scene.control.Button("Breadth First Search");
        bfsButton.setLayoutX(pane.getPrefWidth() - 200);
        bfsButton.setLayoutY(pane.getPrefHeight() / 2 - 40);
        bfsButton.setPrefWidth(150);
        javafx.scene.control.Button dfsButton = new Button("Deap First Search");
        dfsButton.setLayoutX(pane.getPrefWidth() - 200);
        dfsButton.setPrefWidth(150);
        dfsButton.setLayoutY(pane.getPrefHeight() / 2);
        Button gdfsButton = new Button("Greedy Deep First Search");
        gdfsButton.setLayoutX(pane.getPrefWidth() - 200);
        gdfsButton.setLayoutY(pane.getPrefHeight() / 2 + 40);
        gdfsButton.setPrefWidth(150);
        Button newMazeButton = new Button("New Maze");
        newMazeButton.setLayoutX(pane.getPrefWidth() - 200);
        newMazeButton.setLayoutY(pane.getPrefHeight() / 2 + 80);
        newMazeButton.setPrefWidth(150);

        bfsButton.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, null)));
        dfsButton.setBackground(new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, null)));
        gdfsButton.setBackground(new Background(new BackgroundFill(Color.OLIVE, CornerRadii.EMPTY, null)));
        newMazeButton.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, null)));
        addButtonsHandlers(bfsButton, dfsButton, gdfsButton, newMazeButton, maze);
        pane.getChildren().add(dfsButton);
        pane.getChildren().add(bfsButton);
        pane.getChildren().add(gdfsButton);
        pane.getChildren().add(newMazeButton);
        buttons.add(dfsButton);
        buttons.add(bfsButton);
        buttons.add(gdfsButton);
        buttons.add(newMazeButton);
    }

    private void addButtonsHandlers(Button bfsButton, Button dfsButton, Button gdfsButton, Button newMazeButton, Maze maze) {
        bfsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bfsButton.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, null)));
            }
        });
        bfsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bfsButton.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, null)));
            }
        });
        dfsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dfsButton.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, null)));
            }
        });
        dfsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dfsButton.setBackground(new Background(new BackgroundFill(Color.DARKSALMON, CornerRadii.EMPTY, null)));
            }
        });
        gdfsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gdfsButton.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, null)));
            }
        });
        gdfsButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gdfsButton.setBackground(new Background(new BackgroundFill(Color.OLIVE, CornerRadii.EMPTY, null)));
            }
        });
        newMazeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newMazeButton.setBackground(new Background(new BackgroundFill(Color.LIME, CornerRadii.EMPTY, null)));
            }
        });
        newMazeButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newMazeButton.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, null)));
            }
        });

        dfsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                maze.resetMaze();
                maze.solve(true, false);

            }
        });
        bfsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                maze.resetMaze();
                maze.solve(false, false);
            }
        });
        gdfsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                maze.resetMaze();
                maze.solve(true, true);
            }
        });
        newMazeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createAndShowNewMaze();
            }
        });
    }

}
