package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.ConsoleMapDisplay;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SimulationConfigurationPresenter {
    private final SimulationEngine simulationEngine = new SimulationEngine();
    private final List<Stage> stagesList = new ArrayList<>();
    private Object propertiesController;
    private final List<AnimalPropertyEntryPresenter> animalsControllers = new ArrayList<>();
    
    @FXML
    private TextField argsInput;
    @FXML
    private Label informationLabel;
    @FXML
    private ComboBox<String> mapSelector;
    @FXML
    private Pane propertiesPane;
    @FXML
    private ListView<HBox> animalsList;

    private String[] getArgsInput() {
        return argsInput.getText().split(" ");
    }
    @FXML
    private void onSimulationStartClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));

            BorderPane viewRoot = fxmlLoader.load();
            Stage stage = new Stage();
            configureStage(stage, viewRoot);

            AbstractWorldMap map = getAbstractWorldMap(fxmlLoader, stage);
            
            List<MoveDirection> moveDirections = OptionsParser.parse(getArgsInput());

            informationLabel.setText("Please enter move directions");
            informationLabel.setTextFill(Color.BLACK);

            Simulation simulation = new Simulation(getAnimalPositions(), moveDirections, map);

            simulationEngine.runSingleAsyncInThreadPool(simulation);

            stagesList.add(stage);
            stage.show();
        } catch (IllegalArgumentException ex) {
            System.out.println("Passed illegal argument, please enter correct data: " + ex.getMessage());
            informationLabel.setText("Passed illegal argument, please enter correct data: " + ex.getMessage());
            informationLabel.setTextFill(Color.RED);
        }
        catch (IOException ex) {
            /* Crash the application, can't continue without necessary view */
            System.out.println("Could not load fxml file: " + ex.getMessage());
            Platform.exit();
        }
    }

    private AbstractWorldMap getAbstractWorldMap(FXMLLoader fxmlLoader, Stage stage) {
        SimulationPresenter presenter = fxmlLoader.getController();

        AbstractWorldMap map;
        if (mapSelector.getValue().equals("GrassField")) {
            GrassFieldPropertiesPresenter properties = (GrassFieldPropertiesPresenter) propertiesController;
            map = new GrassField(properties.getGrassCount());
        }
        else {
            RectangularMapPropertiesPresenter properties = (RectangularMapPropertiesPresenter) propertiesController;
            map = new RectangularMap(properties.getWidth(), properties.getHeight());
        }

        presenter.setWorldMap(map);

        map.addListener((worldMap, message) -> Platform.runLater(presenter::drawMap));
        map.addListener((worldMap, message) -> Platform.runLater(() -> presenter.setMoveInformationLabel(message)));
        map.addListener(((worldMap, message) ->
                System.out.printf("%s %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), message)));
        map.addListener((worldMap, message) -> Platform.runLater(stage::sizeToScene));
        return map;
    }

    private List<Vector2d> getAnimalPositions(){
        return animalsControllers.stream().map((controller) -> new Vector2d(controller.getXPosition(), controller.getYPosition())).toList();
    }

    public void onApplicationClose() throws InterruptedException {
        simulationEngine.awaitAllSimulationsEnd();

        stagesList.forEach(Stage::close);
    }

    public void loadPropertiesPane(){
        FXMLLoader loader = new FXMLLoader();

        try {
            if (mapSelector.getValue().equals("GrassField"))
                loader.setLocation(getClass().getClassLoader().getResource("grassFieldProperties.fxml"));
            else
                loader.setLocation(getClass().getClassLoader().getResource("rectangularMapProperties.fxml"));

            VBox rootView = loader.load();

            propertiesPane.getChildren().clear();
            propertiesPane.getChildren().add(rootView);

            propertiesController = loader.getController();
        } catch(IOException ex){
            /* Crash the application, can't continue without necessary view */
            System.out.println("Can't load fxmlFile" + ex.getMessage());
            Platform.exit();
        }
    }

    @FXML
    private void onItemSelected() {
        loadPropertiesPane();
    }

    @FXML
    private void onAddAnimalClicked() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("animalPropertyEntry.fxml"));

        try {
            HBox root = loader.load();
            animalsList.getItems().add(root);
            animalsControllers.add(loader.getController());
        } catch (IOException ex){
            /* Crash the application, can't continue without necessary view */
            System.out.println("Can't load fxmlFile" + ex.getMessage());
            Platform.exit();
        }
    }

    @FXML
    private void onRemoveAnimalClicked() {
        int lastIndex = animalsList.getItems().size() - 1;

        if (lastIndex >= 0){
            animalsList.getItems().remove(lastIndex);
            animalsControllers.remove(lastIndex);
        }
    }

    /*
        Not worth refactoring this duplicated code for the sake of readebility and keeping related things together,
        I don't want to make any connection between app and presenter and i think that creating separate helper class
        for this pice is not worth it
    */
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
