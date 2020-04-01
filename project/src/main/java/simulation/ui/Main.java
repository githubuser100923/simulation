
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import simulation.domain.Service;

/*

Main UI

*/

public class Main extends Application {
    private Service service;
    
    private int windowWidth = 600;
    private int windowHeight = 400;
    
    private Scene joinScene;
    private Scene gameScene;
    
    private Label usernameLabel = new Label();

    @Override
    public void init() throws Exception {
        service = new Service();
    }
    
    @Override
    public void start(Stage window) {
        setupJoinScene(window);
        setupGameScene(window);
        
        // Setup window
        window.setTitle("Simulation");
        window.setScene(joinScene);
        window.show();
    }
    
    public void setupJoinScene(Stage window) {
        Label label = new Label("username");
        TextField usernameInput = new TextField();
        Button start = new Button("join");
        Label errorMessage = new Label();
        
        start.setOnAction((event) -> startButtonAction(window, usernameInput, errorMessage));
        
        VBox joinLayout = new VBox(label, errorMessage, usernameInput, start);

        joinLayout.setAlignment(Pos.CENTER);
        joinLayout.setPadding(new Insets(20, 20, 20, 20));
        joinScene = new Scene(joinLayout, windowWidth, windowHeight);
    }
    
    public void startButtonAction(Stage window, TextField usernameInput, Label errorMessage) {
        String usernameText = usernameInput.getText();
        usernameLabel.setText(usernameText);
        String error = service.joinButtonPressed(usernameText);
        if (error == null) {
            service.createNewPlayer(usernameText);
            window.setScene(gameScene);
            usernameInput.setText("");
        } else {
            errorMessage.setText(error);
            errorMessage.setTextFill(Color.RED);
        }
    }
    
    public void setupGameScene(Stage window) {
        usernameLabel = new Label("username");
        
        HBox gameLayout = new HBox(usernameLabel);
        gameScene = new Scene(gameLayout, windowWidth, windowHeight);
    }
    
    @Override
    public void stop() {
        // do stuff here when user leaves the program
        System.out.println("exiting");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
