package sample;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LandlordHome
{
    public void Logout(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void CreateListingButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
       try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateListing.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Create listing Window");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
