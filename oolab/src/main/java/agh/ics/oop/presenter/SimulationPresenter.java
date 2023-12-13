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

    private final Vector2d xAxisMask = new Vector2d(1, 0);
    private final Vector2d yAxisMask = new Vector2d(0, 1);

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

        addConstraints(width, height);
        createAxes(width, height, currentBounds);

        for(IWorldElement<Vector2d> element : worldMap.getElements()){
            Label elementLabel = new Label(element.toString());
            GridPane.setHalignment(elementLabel, HPos.CENTER);
            mapGridPane.add(elementLabel,
                    element.getPosition().getX() + 1 - currentBounds.bottomLeft().getX(),
                    height - (element.getPosition().getY() - currentBounds.bottomLeft().getY()));
        }
    }

    private void addConstraints(int width, int height) {
        while(mapGridPane.getColumnConstraints().size() <= width){
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        while(mapGridPane.getRowConstraints().size() <= height){
            mapGridPane.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
    }

    private void createAxes(int width, int height, Boundary currentBounds) {
        Label yx = new Label("y/x");
        GridPane.setHalignment(yx, HPos.CENTER);
        mapGridPane.add(yx, 0, 0);

        createAxis(width, currentBounds.bottomLeft().getX(), 1, xAxisMask);
        createAxis(height, currentBounds.topRight().getY(), -1, yAxisMask);
    }

    private void createAxis(int size, int start_value, int step, Vector2d axis_mask) {
        int value = start_value;
        for (int i = 1; i <= size; i++){
            Label axis_value = new Label(String.valueOf(value));
            GridPane.setHalignment(axis_value, HPos.CENTER);
            mapGridPane.add(axis_value, i * axis_mask.getX(), i * axis_mask.getY());
            value += step;
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
                new Vector2d(5,5)
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
