import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SceneAdd {
    Scene sceneAdd;

    public Scene getSceneAdd() {

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle1 = new Text("Welcome to add person");
        scenetitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle1, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid1.add(userName, 0, 1);
        TextField userTextField = new TextField(); //------
        grid1.add(userTextField, 1, 1);

        Label userSurname = new Label("User SurName:");
        grid1.add(userSurname, 0, 2);
        TextField userSurnameTextField = new TextField(); //------
        grid1.add(userSurnameTextField, 1, 2);

        Label userBirth = new Label("User Birth:");
        grid1.add(userBirth, 0, 3);
        TextField userBirthTextField = new TextField(); //------
        grid1.add(userBirthTextField, 1, 3);

        Button btnMenu = new Button("MENU");

        Button btnSign = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnSign,btnMenu);
        grid1.add(hbBtn, 1, 4);



        final Text actiontarget = new Text();
        grid1.add(actiontarget, 1, 6);


        sceneAdd = new Scene(grid1, 500, 500);

        return sceneAdd;
    }
}
