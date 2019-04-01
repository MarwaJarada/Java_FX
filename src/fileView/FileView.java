package fileView;

import com.sun.webkit.ColorChooser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

/**
 * @author Marwa N. Jarada
 */
public class FileView extends Application {
    @FXML
    BorderPane borderPane;
    @FXML
    MenuBar menuBar;
    @FXML
    Menu fileMenu;
    @FXML
    Menu editMenu;
    @FXML
    MenuItem openMenuItem;
    @FXML
    MenuItem closeMenuItem;
    @FXML
    MenuItem exitMenuItem;
    @FXML
    MenuItem fontMenuItem;
    @FXML
    MenuItem colorMenuItem;
    @FXML
    TextArea txtArea;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File View");
        borderPane = new BorderPane();
        menuBar = new MenuBar();
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        openMenuItem = new MenuItem("Open");
        javafx.event.EventHandler eventHandler = new EventHandler();
        openMenuItem.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fileChooser = new FileChooser();
                try {

                    File selectedFile = fileChooser.showOpenDialog(primaryStage);
                    Scanner scanner = new Scanner(selectedFile);
                    while (scanner.hasNextLine()){
                        txtArea.appendText(scanner.nextLine());


                    }
                    txtArea.setEditable(true);
                }catch (Exception e) {
                    System.out.println("Empty File");
                }


            }
        });
        closeMenuItem = new MenuItem("Close");
        closeMenuItem.setOnAction(eventHandler);
        exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(eventHandler);
        fontMenuItem = new MenuItem("Font");
        fontMenuItem.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] items = {"10","20","30","40","50"};
                ChoiceDialog choiceDialog=(new ChoiceDialog(items[0],items));
                choiceDialog.setTitle("Size Selection");
                choiceDialog.setHeaderText("Select the Size from list");
                choiceDialog.setContentText("Avaliable size :");
                choiceDialog.showAndWait();
                String selected = choiceDialog.getSelectedItem().toString();
                txtArea.setFont(new Font("Tahoma",Integer.parseInt(selected)));



            }
        });
        colorMenuItem = new MenuItem("Color");
        colorMenuItem.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] items={"Red","Green","Yellow","Pink"};
                ChoiceDialog choiceDialog=new ChoiceDialog(items[0],items);
                choiceDialog.setTitle("Color Selection ");
                choiceDialog.setHeaderText("Select the color from list");
                choiceDialog.setContentText("Avaliable colors :");
                choiceDialog.showAndWait();
                String selected = choiceDialog.getSelectedItem().toString();
                switch (selected.toLowerCase()){
                    case "red":
                        txtArea.setStyle("-fx-text-fill: red;");
                        break;
                    case "green":
                        txtArea.setStyle("-fx-text-fill: green;");
                        break;
                    case "yellow":
                        txtArea.setStyle("-fx-text-fill: yellow;");
                        break;
                    case "pink":
                        txtArea.setStyle("-fx-text-fill: pink;");
                        break;


                }

            }
        });
        txtArea = new TextArea("Here is the text ...");
        txtArea.setMinSize(350, 250);
        fileMenu.getItems().addAll(openMenuItem, closeMenuItem, exitMenuItem);
        editMenu.getItems().addAll(fontMenuItem, colorMenuItem);


        FlowPane flowPane = new FlowPane();
        menuBar.getMenus().addAll(fileMenu, editMenu);
        borderPane.setTop(menuBar);
        borderPane.setCenter(txtArea);
        Scene scene = new Scene(borderPane, 350, 250);

        primaryStage.setScene(scene);
        primaryStage.show();





    }


    private class EventHandler implements javafx.event.EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            MenuItem item = (MenuItem) event.getSource();
            switch (item.getText().toLowerCase()) {
                case "exit":
                    System.exit(0);
                    break;
                case "close":
                    txtArea.setText(null);
                    txtArea.setEditable(false);
                    break;
            }
        }





    }




}
