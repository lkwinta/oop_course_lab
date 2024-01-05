package agh.ics.oop.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class WorldElementBoxFactory {
    private static final HashMap<String, Image> imagesHashMap = new HashMap<>();

    public static VBox getWorldElementBox(IWorldElement<Vector2d> worldElement){
        String url = "textures/" + worldElement.getResourceName();
        if(!imagesHashMap.containsKey(url)){
            imagesHashMap.put(url, new Image(url));
        }

        ImageView imageView = new ImageView(imagesHashMap.get(url));
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
