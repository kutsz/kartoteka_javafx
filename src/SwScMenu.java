import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class SwScMenu extends Application {
    Stage window;
    Scene sceneAdd, sceneMenu, sceneShow;
    SceneAdd sceneAdd1;

    public void start(Stage primaryStage) {
        window = primaryStage;
        //-------MENU--
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle = new Text("Menu");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);


        Button btnAdd = new Button("ADD");
        Button btnFind = new Button("FIND");
        Button btnShow = new Button("SHOW");
        Button btnExit = new Button("EXIT");


        VBox vbBtn = new VBox(10);
        vbBtn.getChildren().addAll(btnAdd,btnFind,btnShow,btnExit);
        grid.add(vbBtn, 1, 1);

        sceneMenu = new Scene(grid, 500, 500);
//======================

//----------------ADD------------
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle1 = new Text("Welcome to add person");
        scenetitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle, 0, 0, 2, 1);

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


//--------------------
         sceneAdd = new Scene(grid1, 500, 500);

        //sceneAdd1 = new SceneAdd();
        //sceneAdd = sceneAdd1.getSceneAdd();


//----------SHOW-------------
        StackPane layoutSh = new StackPane();

        final ListView listView = new ListView();
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        layoutSh.getChildren().add(listView);

        sceneShow = new Scene(layoutSh,500,500);
        Text listPerson = new Text();
        layoutSh.getChildren().add(listPerson);
//====================================
//        btnShow.setOnAction(e -> {
//            //add = new Add();
//            window.setScene(sceneShow);
//        });
//-------------
        btnMenu.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneMenu);
        });
//---------------------
        btnAdd.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneAdd);
        });

//-----------------------

        btnExit.setOnAction(e -> {
            window.close();
        });
//----------------------------------
        btnShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                BD db = new BD();
                String query = "SELECT * FROM Person";

                try {
                    window.setScene(sceneShow);

                    Statement stmt = db.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        String birth = rs.getString("birthDate");
                        Person p = new Person(name, surname,birth);
                        //System.out.println("Name: " + name + " Surname: " + surname + " Birth date: " + birth);
                        System.out.println(p);
                        //String person = p;
                        //actiontarget.setText((String)p);

//                        listPerson.setText(name+" "+surname+" "+birth);
                        //listPerson.setText(p.toString());

                    }
                } catch (Exception ex ) {
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }
        });
//-----------------------------
        btnSign.setOnAction(new EventHandler<ActionEvent>() {
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
        //---------------------

        window.setScene(sceneMenu);
        window.setTitle("Title");

        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
