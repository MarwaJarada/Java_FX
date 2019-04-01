package multipleSelection;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * @author Marwa N. Jarada
 */
public class Main extends Application {

    @FXML private Button copyBtn;
    @FXML private TextArea txtArea;
    @FXML private ListView<String> lstView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("miltipleSelector"));
        primaryStage.setTitle("Multiple Selection List");
        txtArea= new TextArea();
        lstView = new ListView<>();
        copyBtn=new Button("Copy >>");
        lstView.setMaxSize(120, 200);
        txtArea.setMaxSize(120,200);
        copyBtn.setMaxSize(90,20);
        lstView.getItems().addAll("Black","Blue","Cyan","DarkGray","Gray","Green","Pink","Yellow","Blue");
        lstView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        copyBtn.setOnAction(e->buttonClicked());
        FlowPane flowPane=new FlowPane();
        flowPane.getChildren().addAll(lstView,copyBtn,txtArea);
        Scene scene=new Scene(flowPane,350,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void buttonClicked(){

        String items="";
        ObservableList<String> mySelectedItems;
        mySelectedItems = lstView.getSelectionModel().getSelectedItems();

        if (mySelectedItems.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No items selected !");

            alert.showAndWait();}
        else {
            for (String item : mySelectedItems) {
                items += item + "\n";
            }
            txtArea.appendText(items);
        }
    }
}
