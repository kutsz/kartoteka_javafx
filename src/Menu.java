import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
;import java.sql.ResultSet;
import java.sql.Statement;

public class Menu extends Application {
    Add add = new Add();

    public void start(Stage primaryStage) {
        primaryStage.setTitle("MENU");

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

//        HBox hbBtn = new HBox(10);
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().addAll(btnAdd,btnFind,btnShow,btnExit);
//        grid.add(hbBtn, 1, 1);

        VBox vbBtn = new VBox(10);
        vbBtn.getChildren().addAll(btnAdd,btnFind,btnShow,btnExit);
        grid.add(vbBtn, 1, 1);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //primaryStage.close();
                //Add1 a = new Add1();
               // a.run();
                //add = new Add();
           primaryStage.setScene(add.getScene());
            }
        });
        btnExit.setOnAction(e -> {
            primaryStage.close();
        });

        btnShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                BD db = new BD();
               String query = "SELECT * FROM Person";

                try {

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

                    }
                } catch (Exception ex ) {
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }
        });
//        btnAdd.setOnAction(e -> {
//            //add = new Add();
//            primaryStage.setScene(add.getScene());
//        });


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


