package db;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SwScMenu extends Application {
    Stage window;
    Scene sceneAdd, sceneMenu, sceneShow, sceneFind,sceneEdit;
    //Person person;
    int tmpId;

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

//---------------------
        btnAdd.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneAdd);
        });

        btnFind.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneFind);
        });

//-----------------------

        btnExit.setOnAction(e -> {
            window.close();
        });

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



        Text actiontarget = new Text();
        grid1.add(actiontarget, 1, 6);


         sceneAdd = new Scene(grid1, 500, 500);


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
        //------------

        btnMenu.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneMenu);
        });

//----------SHOW-------------
        //StackPane layoutSp = new StackPane();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.BASELINE_CENTER.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(20, 20, 20, 20));


//        ListView<String> ListView = new ListView<String>();
//        ObservableList<String> items =FXCollections.observableArrayList (
//                "Single", "Double", "Suite", "Family App");
//        ListView.setItems(items);

        ListView<Person> ListView = new ListView<Person>();
//
        ObservableList<Person> person = FXCollections.observableArrayList ();
//        person.add(new Person("Hans", "Muster","1988"));
//        person.add(new Person("Hans1", "Muster1","1988"));
//        person.add(new Person("Hans2", "Muster2","1988"));

        ListView.setItems(person);

        ListView.setPrefSize(400, 400);
        ListView.setEditable(true);

//        layoutSp.getChildren().addAll(ListView);
//        StackPane.setAlignment(layoutSp, Pos.TOP_CENTER);
        Button btnMenuS = new Button("MENU");
        //StackPane.setAlignment(btnMenuS, Pos.BOTTOM_CENTER);

        gp.add(ListView,0,1);
        gp.add(btnMenuS,0,3);
        //sceneShow = new Scene(layoutSp,500,500);

        sceneShow = new Scene(gp,500,500);
        //Text listPerson = new Text("list of Persons");
        //layoutSh.getChildren().add(listPerson);


       // ObservableList<Person> personData = FXCollections.observableArrayList();

        btnShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                BD db = new BD();
                String query = "SELECT * FROM Person";
                person.clear();
                //ListView<Person> ListView = new ListView<Person>();

                //ObservableList<Person> person =FXCollections.observableArrayList ();

                try {
//                    window.setScene(sceneShow);

                    Statement stmt = db.conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        String birth = rs.getString("birthDate");
                        int id = rs.getInt("id");
                        //Person p = new Person(name, surname,birth);
                        //System.out.println(p);

                        person.add(new Person(id,name, surname,birth));

                        //String person = p;
                        //actiontarget.setText((String)p);

//                        listPerson.setText(name+" "+surname+" "+birth);
                        //listPerson.setText(p.toString());

                    }
                    window.setScene(sceneShow);

//
                } catch (Exception ex ) {
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }
        });



        btnMenuS.setOnAction(e->{
            window.setScene(sceneMenu);
        });

//-----FIND---------------
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.BASELINE_CENTER.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle2 = new Text("Welcome to find person");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(scenetitle2, 0, 0, 2, 1);

        Label findPerson = new Label("Enter surname:");
        grid2.add(findPerson, 0, 1);
        TextField find = new TextField(); //------
        grid2.add(find, 1, 1);

        Button btnFindPerson = new Button("FIND");


        Button btnMenu1 = new Button("MENU");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().addAll(btnFindPerson,btnMenu1);
        grid2.add(hbBtn1, 1, 4);



//        final Text actiontarget = new Text();
//        grid1.add(actiontarget, 1, 6);


        sceneFind = new Scene(grid2, 500, 500);

        btnMenu1.setOnAction(e -> {
            //add = new Add();
            window.setScene(sceneMenu);
        });

        btnFindPerson.setOnAction(e -> {

                actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");
                if(!find.getText().isEmpty()) {

                    String surName = find.getText();
                    person.clear();

                    try {
                        BD db = new BD();
                        PreparedStatement ps = db.conn.prepareStatement("SELECT * FROM Person WHERE surname = ?");
                        ps.setString(1,surName);
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            String name = rs.getString("name");
                            String surname = rs.getString("surname");
                            String birth = rs.getString("birthDate");

                            //System.out.println("Name: " + name + " Surname: " + surname + " Birth date: " + birth);
                            person.add(new Person(name, surname,birth));

                        }

                        window.setScene(sceneShow);

                    } catch (Exception ex ) {
                        //JDBCTutorialUtilities.printSQLException(e);
                    }
//                    finally {
//                         if (ps != null) { ps.close(); }
//                    }

                    actiontarget.setText("OK");
                } else {
                    actiontarget.setText("The fields cannot be left blank.");
                }

        });

//-------------EDIT--------------------
        GridPane gridEdit = new GridPane();
        gridEdit.setAlignment(Pos.BASELINE_CENTER.CENTER);
        gridEdit.setHgap(10);
        gridEdit.setVgap(10);
        gridEdit.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitleEdit = new Text("Welcome to edit person");
        scenetitleEdit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridEdit.add(scenetitleEdit, 0, 0, 2, 1);

        Label userNameEdit = new Label("User Name:");
        gridEdit.add(userNameEdit, 0, 1);
        TextField userNameEditField = new TextField(); //------
        gridEdit.add(userNameEditField, 1, 1);

        Label userSurnameEdit = new Label("User SurName:");
        gridEdit.add(userSurnameEdit, 0, 2);
        TextField userSurnameEditField = new TextField(); //------
        gridEdit.add(userSurnameEditField, 1, 2);

        Label userBirthEdit = new Label("User Birth:");
        gridEdit.add(userBirthEdit, 0, 3);
        TextField userBirthEditField = new TextField(); //------
        gridEdit.add(userBirthEditField, 1, 3);

        Button btnMenuEdit = new Button("MENU");
        Button btnEdit = new Button("EDIT");
        Button btnDelete  = new Button("DELETE");

        HBox hbBtnEdit = new HBox(10);
        hbBtnEdit.setAlignment(Pos.CENTER);
        hbBtnEdit.getChildren().addAll(btnMenuEdit,btnEdit,btnDelete);
        gridEdit.add(hbBtnEdit, 1, 4);


        sceneEdit = new Scene(gridEdit, 500, 500);
       // Person personEdit;

        btnMenuEdit.setOnAction(e->{
            window.setScene(sceneMenu);
        });

        //int personId = 0;
        ListView.setOnMouseClicked(e-> {
            //System.out.println("clicked on " + ListView.getSelectionModel().getSelectedItem());
            Person personEdit = ListView.getSelectionModel().getSelectedItem();
            System.out.println(personEdit);
            userNameEditField.setText(personEdit.getName());
            userSurnameEditField.setText(personEdit.getSurname());
            userBirthEditField.setText(personEdit.getBirthDate());
            tmpId = personEdit.getId();
            System.out.println(tmpId);
            window.setScene(sceneEdit);
        });

        btnEdit.setOnAction(e->{
            if(!userNameEditField.getText().isEmpty() && !userSurnameEditField.getText().isEmpty() && !userBirthEditField.getText().isEmpty()) {
                String name = userNameEditField.getText();
                String surname = userSurnameEditField.getText();
                String birth = userBirthEditField.getText();
                System.out.println(name+" "+surname+" "+birth +" "+tmpId);
                BD db = new BD();
                db.update(name, surname, birth,tmpId);
                //db.update1(name);
                actiontarget.setText("OK");
            } else {
                actiontarget.setText("The fields cannot be left blank.");
            }

        });
        btnDelete.setOnAction(e->{
            BD db = new BD();
            db.delete(tmpId);

        });
//----------------------------

//--------ex----
//        final ListView lv = new ListView(FXCollections.observableList(Arrays.asList("one", "2", "3")));
//        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("clicked on " + lv.getSelectionModel().getSelectedItem());
//            }
//        });
//--------------click---
//        ListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("clicked on " + ListView.getSelectionModel().getSelectedItem());
//            }
//        });

//-----------------------------

        window.setScene(sceneMenu);
        window.setTitle("Title");

        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
