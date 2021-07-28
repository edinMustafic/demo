package sample;

//import com.sun.jndi.toolkit.dir.SearchFilter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class Search
{
    @FXML
    private TextField country;
    @FXML
    private TextField city;
    @FXML
    private TextField noOfRooms;
    @FXML
    private TextField noOfBeds;
    @FXML
    private CheckBox wiFi;
    @FXML
    private CheckBox parking;
    @FXML
    private TextField pricePerNight;
    @FXML
    private Label errorMessage;

    private boolean allFieldsHaveValue = false;

    private boolean checkNumber(String str)
    {
        boolean retval = false;
        int n = str.length();

        for (int i = 0; i < n; i++)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                retval = true;
            }
            else
            {
                return false;
            }
        }
        return retval;
    }

    public void CheckSearchParameters()
    {
        System.out.println("Checking search parameters");
        if(country.getText() == "" || city.getText() == "" || noOfRooms.getText() == "" || noOfBeds.getText() == "" || pricePerNight.getText() == "")
        {
            errorMessage.setText("All search fields must be filled");
        }
        else if(!checkNumber(noOfBeds.getText()))
        {
            errorMessage.setText("Invalid beds number.");
        }
        else if(!checkNumber(noOfRooms.getText()))
        {
            errorMessage.setText("Invalid rooms number.");
        }
        else if(!checkNumber(pricePerNight.getText()))
        {
            errorMessage.setText("Invalid price number.");
        }
        else
        {
            allFieldsHaveValue = true;
        }
    }

    public void SearchFunc()
    {
        System.out.println("SearchFunc called");
        CheckSearchParameters();
        // odradi pretragu po prosljedjenim parametrima i nekako izlistaj samo oglase koji spadaju pod te kriterije kada load-as home.fxml
    }

    public void SearchButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        SearchFunc();

        if(allFieldsHaveValue)
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }
}
