import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
;

public class Add extends Application {

    Scene sceneAdd;

    public void start(Stage primaryStage) {
        primaryStage.setTitle(" Welcome to add person");
//----------------------------
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle = new Text("Welcome to add person");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField(); //------
        grid.add(userTextField, 1, 1);

        Label userSurname = new Label("User SurName:");
        grid.add(userSurname, 0, 2);
        TextField userSurnameTextField = new TextField(); //------
        grid.add(userSurnameTextField, 1, 2);

        Label userBirth = new Label("User Birth:");
        grid.add(userBirth, 0, 3);
        TextField userBirthTextField = new TextField(); //------
        grid.add(userBirthTextField, 1, 3);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        Button btnMenu = new Button("MENU");


//--------------------
        Scene sceneAdd = new Scene(grid, 500, 500);
        primaryStage.setScene(sceneAdd);
        primaryStage.show();
//------------------
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");
                if(!userTextField.getText().isEmpty() && !userSurnameTextField.getText().isEmpty() && !userBirthTextField.getText().isEmpty()) {
                    String name = userTextField.getText();
                    String surname = userSurnameTextField.getText();
                    String birth = userBirthTextField.getText();
                    BD db = new BD();
                    db.insert(name, surname, birth);
                    actiontarget.setText("OK");
                } else {
                    actiontarget.setText("The fields cannot be left blank.");
                }
            }
        });

    }
    public Scene getScene(){
        return sceneAdd;
    }
    public void run(){
        launch();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


