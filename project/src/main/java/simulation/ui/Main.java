
package simulation.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        Label label = new Label("username");
        TextField username = new TextField();
        Button start = new Button("start");
        
        start.setOnAction((event) -> startPressed(username.getText()));
        
        GridPane layout = new GridPane();
        
        layout.add(label, 0, 0);
        layout.add(username, 0, 1);
        layout.add(start, 0, 2);

        layout.setPrefSize(600, 400);
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(20,20,20,20));
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    public boolean startPressed(String username){
        if(username.length() < 3) {
            return false;
        }
        return true;
    }
}
