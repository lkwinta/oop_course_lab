package agh.ics.oop.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class WorldElementBoxFactory {
    public static VBox getWorldElementBox(IWorldElement<Vector2d> worldElement){
        ImageView imageView = new ImageView(new Image("textures/" + worldElement.getResourceName()));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Label label = new Label(worldElement.getPosition().toString());

        VBox vBox = new VBox();
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }
}
