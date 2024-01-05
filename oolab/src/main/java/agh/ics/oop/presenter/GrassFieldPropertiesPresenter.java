package agh.ics.oop.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GrassFieldPropertiesPresenter {
    @FXML
    private TextField grassCountTextField;

    public int getGrassCount(){
        return Integer.parseInt(grassCountTextField.getText());
    }
}
