package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login
{

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label errorMessage;

    private boolean loginSuccessful = false;

    private void LoginLandlord()
    {
        if(username.getText().length() == 0)
        {
            errorMessage.setText("Username is required.");
            return;
        }

        if(password.getText().length() == 0)
        {
            errorMessage.setText("Password is required.");
            return;
        }

        // povuci landlorda sa datim username iz baze podataka
        if (true) // ako postoji takav landlord
        {
            loginSuccessful = true;
            System.out.println("Landlord logged in:");
        }
    }

    public void LoginButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        LoginLandlord();

        if (loginSuccessful)
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("LandlordHome.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }
}
