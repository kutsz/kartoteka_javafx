import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SwitchScene extends Application {
     Stage window;
     Scene scene1, scene2;

    public void start(Stage primaryStage) {
        window = primaryStage;
        //---------
        Label l1 = new Label("label1");
        Button b1 = new Button("scene2");
        b1.setOnAction(e->window.setScene(scene2));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(l1,b1);
        scene1 = new Scene(layout1,500,500);
        //--------
        Label l2 = new Label("label2");
        Button b2 = new Button("scene1");
        b2.setOnAction(e->window.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(l2,b2);
        scene2 = new Scene(layout2,1000,1000);

        //----------

        window.setScene(scene1);
        window.setTitle("Title");

        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
