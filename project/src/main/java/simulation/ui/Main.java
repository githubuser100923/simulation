
package simulation.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import simulation.domain.Player;

import simulation.domain.Service;

/*

Main UI

*/

public class Main extends Application {
    private Service service;
    
    private int windowWidth = 600;
    private int windowHeight = 400;
    
    private Image selectedCharacter = new Image("https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074__340.jpg");
    
    private Scene joinScene;
    private Scene chooseCharacterScene;
    private Scene gameScene;
    
    private Label usernameLabel = new Label();

    @Override
    public void init() throws Exception {
        service = new Service();
    }
    
    @Override
    public void start(Stage window) {
        setupJoinScene(window);
        setupChooseCharacterScene(window);
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
            service.createNewPlayer(usernameText, 0, 0);
            // service.loadOthers();
            window.setScene(chooseCharacterScene);
            usernameInput.setText("");
        } else {
            errorMessage.setText(error);
            errorMessage.setTextFill(Color.RED);
        }
    }
    
    public void setupChooseCharacterScene(Stage window) {
        Image image1 = new Image("https://paradisepuppies.com/wp-content/uploads/2017/10/Webp.net-resizeimage-9-copy-3-4.jpg");
        ImageView iv1 = new ImageView();
        iv1.setFitWidth(100);
        iv1.setFitHeight(100);
        iv1.setImage(image1);
        iv1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedCharacter = image1;
                setupGameScene(window);
                window.setScene(gameScene);
                event.consume();
            }
        });
        
        Image image2 = new Image("https://images-na.ssl-images-amazon.com/images/I/81-yKbVND-L.png");
        ImageView iv2 = new ImageView();
        iv2.setFitWidth(100);
        iv2.setFitHeight(100);
        iv2.setImage(image2);
        iv2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedCharacter = image2;
                setupGameScene(window);
                window.setScene(gameScene);
                event.consume();
            }
        });
        
        Image image3 = new Image("https://media.wired.com/photos/5cdefc28b2569892c06b2ae4/master/w_2560%2Cc_limit/Culture-Grumpy-Cat-487386121-2.jpg");
        ImageView iv3 = new ImageView();
        iv3.setFitWidth(100);
        iv3.setFitHeight(100);
        iv3.setImage(image3);
        iv3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selectedCharacter = image3;
                setupGameScene(window);
                window.setScene(gameScene);
                event.consume();
            }
        });
        
        HBox layout = new HBox(iv1, iv2, iv3);
        chooseCharacterScene = new Scene(layout, windowWidth, windowHeight);
    }
    
    public void setupGameScene(Stage window) {
        Pane layout = new Pane();
        
        Player player = service.getPlayer();
        player.setImage(selectedCharacter);
        
        player.setCharacterPosition(100, 100);
        
        layout.getChildren().add(player.getCharacter());
        
        Map<KeyCode, Boolean> pressedButtons = new HashMap<>();
        
        gameScene = new Scene(layout, windowWidth, windowHeight);
        gameScene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        gameScene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            @Override
            public void handle(long current) {
                if(pressedButtons.getOrDefault(KeyCode.RIGHT, false)) {
                    player.move(5,0);
                }
                if(pressedButtons.getOrDefault(KeyCode.LEFT, false)) {
                    player.move(-5,0);
                }
                if(pressedButtons.getOrDefault(KeyCode.UP, false)) {
                    player.move(0,-5);
                }
                if(pressedButtons.getOrDefault(KeyCode.DOWN, false)) {
                    player.move(0,5);
                }
            }
        }.start();
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
