package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;

public class CreateListing
{
    @FXML
    private TextField listingTitle;
    @FXML
    private TextField country;
    @FXML
    private TextField address;
    @FXML
    private MenuButton type;
    @FXML
    private TextField noOfRooms;
    @FXML
    private TextField noOfBeds;
    @FXML
    private TextField area;
    @FXML
    private CheckBox petsAllowed;
    @FXML
    private CheckBox wiFi;
    @FXML
    private CheckBox tv;
    @FXML
    private CheckBox ac;
    @FXML
    private CheckBox kitchen;
    @FXML
    private CheckBox balcony;
    @FXML
    private CheckBox garden;
    @FXML
    private CheckBox smokingAllowed;
    @FXML
    private CheckBox parking;
    @FXML
    private TextField pricePerNight;
    @FXML
    private CheckBox freeCancellation;
    @FXML
    private TextField discountForReturningCustomers;
    @FXML
    private TextField imageURL;
    @FXML
    private TextField detailedDescription;



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
