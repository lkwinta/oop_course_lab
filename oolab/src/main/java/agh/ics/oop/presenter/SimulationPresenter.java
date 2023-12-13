package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.*;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter {

    private final static int CELL_WIDTH = 40;
    private final static int CELL_HEIGHT = 40;

    private IWorldMap<IWorldElement<Vector2d>, Vector2d> worldMap;

    @FXML
    private GridPane mapGridPane;
    @FXML
    private TextField argsInput;
    @FXML
    private Label moveInformation;

    public void setWorldMap(IWorldMap<IWorldElement<Vector2d>, Vector2d> map){
        worldMap = map;
    }

    public void drawMap() {
        clearGrid();

        Boundary currentBounds = worldMap.getCurrentBounds();
        int width = currentBounds.topRight().getX() - currentBounds.bottomLeft().getX() + 1;
        int height = currentBounds.topRight().getY() - currentBounds.bottomLeft().getY() + 1;

        while(mapGridPane.getColumnConstraints().size() <= width){
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        while(mapGridPane.getRowConstraints().size() <= height){
            mapGridPane.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        Label yx = new Label("y/x");
        GridPane.setHalignment(yx, HPos.CENTER);
        mapGridPane.add(yx, 0, 0);

        int x = currentBounds.bottomLeft().getX();
        for (int i = 1; i <= width; i++){
            Label x_pos = new Label(String.valueOf(x));
            GridPane.setHalignment(x_pos, HPos.CENTER);
            mapGridPane.add(x_pos, i, 0);
            x++;
        }

        int y = currentBounds.topRight().getY();
        for (int i = 1; i <= height; i++){
            Label y_pos = new Label(String.valueOf(y));
            GridPane.setHalignment(y_pos, HPos.CENTER);
            mapGridPane.add(y_pos, 0, i);
            y--;
        }

        for(IWorldElement<Vector2d> element : worldMap.getElements()){
            Label elementLabel = new Label(element.toString());
            GridPane.setHalignment(elementLabel, HPos.CENTER);
            mapGridPane.add(elementLabel,
                    element.getPosition().getX() + 1 - currentBounds.bottomLeft().getX(),
                    height - (element.getPosition().getY() - currentBounds.bottomLeft().getY()));
        }



    }

    public void setMoveInformationLabel(String text) {
        moveInformation.setText(text);
    }

    private void clearGrid() {
        mapGridPane.getChildren().retainAll(mapGridPane.getChildren().get(0)); // hack to retain visible grid lines
        mapGridPane.getColumnConstraints().clear();
        mapGridPane.getRowConstraints().clear();
    }

    private String[] getArgsInput() {
        return argsInput.getText().split(" ");
    }

    @FXML
    private void onSimulationStartClicked() {
        List<Vector2d> positions = List.of(
                new Vector2d(0,0),
                new Vector2d(2,2)
        );

        try {
            List<MoveDirection> moveDirections = OptionsParser.parse(getArgsInput());

            Simulation simulation = new Simulation(positions, moveDirections, worldMap);

            SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
            simulationEngine.runAsync();
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Passed illegal argument, please enter correct data: " + ex.getMessage());
            moveInformation.setText("Passed illegal argument, please enter correct data: " + ex.getMessage());
        }
    }
}
