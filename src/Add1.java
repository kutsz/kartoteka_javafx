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

public class Add1 extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle = new Text("Welcome");
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

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");

                String name = userTextField.getText();
                String surname = userSurnameTextField.getText();
                String birth = userBirthTextField.getText();
                actiontarget.setText(name);

                BD db = new BD();

                //String query = "UPDATE Person SET name =" + text + ",surname = 'nazwisko',birthDate = 'Rok_urodzenia' WHERE id = 1";
                // String query = "UPDATE Person SET name = 'test',surname = 'nazwisko',birthDate = 'Rok_urodzenia' WHERE id = 1";
                ////String query = String.format("UPDATE Person SET name = %s,surname = 'nazwisko',birthDate = 'Rok_urodzenia' WHERE id = 1",text);
                //db.update1(text);

                //db.update2(name,surname,birth);
                db.insert(name,surname,birth);
            }
        });

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void run(){
        launch();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


