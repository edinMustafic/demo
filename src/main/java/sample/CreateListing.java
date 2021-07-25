package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class CreateListing
{
    @FXML
    private TextField listingTitle;
    @FXML
    private TextField country;
    @FXML


    public void CreateListingFunc()
    {
        System.out.println("haha");
    }

    public void CreateListingButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        Parent logInParent = FXMLLoader.load(getClass().getResource("CreateListing.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

}
