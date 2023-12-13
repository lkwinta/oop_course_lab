package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));

            BorderPane viewRoot = fxmlLoader.load();
            configureStage(primaryStage, viewRoot);
            SimulationPresenter presenter = fxmlLoader.getController();

            AbstractWorldMap map = new GrassField(10);
            presenter.setWorldMap(map);
            ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();

            map.addListener((worldMap, message) -> Platform.runLater(presenter::drawMap));
            map.addListener((worldMap, message) -> Platform.runLater(() -> presenter.setMoveInformationLabel(message)));
            map.addListener(consoleMapDisplay);
            map.addListener((worldMap, message) -> Platform.runLater(primaryStage::sizeToScene));

            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Could not load fxml file: " + ex.getMessage());
        }
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
