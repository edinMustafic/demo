package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller
{
    public void OpenRegisterScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("ChooseRegister.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OpenLoginScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void OpenSearchScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    private void RegisterRenter()
    {

    }



}
