package sample;

import entity.Listing;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateListing implements Initializable
{
    @FXML
    private TextField listingTitle;
    @FXML
    private TextField country;
    @FXML
    private TextField address;
    @FXML
    private MenuItem house;
    @FXML
    private MenuItem villa;
    @FXML
    private MenuItem apartment;
    @FXML
    private MenuItem room;
    @FXML
    private MenuItem cottage;
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
    @FXML
    private Label errorMessage;
    @FXML
    private Label infoLabel;

    private boolean registrationSuccessful = false;

    public void HouseMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        infoLabel.setText("House");
    }

    public void VillaMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        infoLabel.setText("Villa");
    }
    public void ApartmentMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        infoLabel.setText("Apartment");
    }

    public void RoomMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        infoLabel.setText("Room");
    }
    public void CottageMenuItemSelected(javafx.event.ActionEvent event) throws Exception
    {
        infoLabel.setText("Cottage");
    }

    private boolean checkTitle(String str)
    {
        System.out.println("Checking title");

        if(str.length() == 0)
        {
            errorMessage.setText("Title is required");
            return false;
        }

        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || ch == ' ') )
            {
                errorMessage.setText("Invalid name.\nCountry name should contain only letters.");
                return false;
            }
        }
        return true;
    }

    private boolean checkCountry(String str)
    {
        System.out.println("Checking name");

        if(str.length() == 0)
        {
            errorMessage.setText("Country name is required");
            return false;
        }

        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || ch == ' ') )
            {
                errorMessage.setText("Invalid name.\nCountry name should contain only letters.");
                return false;
            }
        }
        return true;
    }

    private boolean checkNumber(String str)
    {
        boolean retval = false;
        int n = str.length();

        if(str == null || str.equals(" "))
        {
            errorMessage.setText("Number is required.");
            return false;
        }

        for (int i = 0; i < n; i++)
        {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
            {
                retval = true;
            }
            else
            {
                errorMessage.setText("Invalid number.");
                return false;
            }
        }
        return retval;
    }

    private boolean CheckImageURL(String str)
    {
        if(str == null || str.equals(" "))
        {
            return false;
        }
        return true;
    }

    public void CreateListingFunc()
    {
        System.out.println("Creating a listing");
        // provjeri da u imenu drzave nema brojeva
        // no of rooms, no of beds, surface area, price per night i discount for returning users moraju bit iskljucivo brojevi
        if(checkTitle(listingTitle.getText()) && checkCountry(country.getText()) && checkNumber(noOfRooms.getText()) && checkNumber(noOfBeds.getText()) && checkNumber(area.getText()) && checkNumber(pricePerNight.getText()) && checkNumber(discountForReturningCustomers.getText()) && CheckImageURL(imageURL.getText()) && CheckImageURL(detailedDescription.getText()))
        {
            Listing newListing = new Listing();

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();



            try
            {
                transaction.begin();

                List<Integer> l = entityManager.createQuery(
                        "SELECT u.idListing FROM Listing u")
                        .getResultList();

                for (int i = 0; i < l.size(); i++)
                {
                    System.out.println(l.get(i));
                }

                Random rand = new Random();
                int upperbound = 500;

                int ID = 0;
                boolean IDexists = true;


                // generating unique ID for the new user
                while(IDexists)
                {
                    ID = rand.nextInt(upperbound);
                    System.out.println(ID);
                    for (int i = 0; i < l.size(); i++)
                    {
                        if(ID == l.get(i))
                        {
                            IDexists = true;
                            break;
                        }
                    }
                    IDexists = false;
                }


                newListing.setIdListing(ID);
                newListing.setTitle(listingTitle.getText());
                newListing.setCountry(country.getText());
                newListing.setAddress(address.getText());

                int t = 0;
                switch(infoLabel.getText())
                {
                    case "House":
                    {
                        t = 1;
                        break;
                    }
                    case "Villa":
                    {
                        t = 2;
                        break;
                    }
                    case "Apartment":
                    {
                        t = 3;
                        break;
                    }
                    case "Room":
                    {
                        t = 4;
                        break;
                    }
                    case "Cottage":
                    {
                        t = 5;
                        break;
                    }
                }
                newListing.setType(t);
                newListing.setNumberOfRooms(Integer.parseInt(noOfRooms.getText()));
                newListing.setNumberOfBeds(Integer.parseInt(noOfBeds.getText()));
                newListing.setSurfaceArea(Integer.parseInt(area.getText()));
                newListing.setPets((byte) (petsAllowed.isSelected() ? 1 : 0));
                newListing.setWiFi((byte) (wiFi.isSelected() ? 1 : 0));
                newListing.setTv((byte) (tv.isSelected() ? 1 : 0));
                newListing.setAc((byte) (ac.isSelected() ? 1 : 0));
                newListing.setKitchen((byte) (kitchen.isSelected() ? 1 : 0));
                newListing.setBalcony((byte) (balcony.isSelected() ? 1 : 0));
                newListing.setGarden((byte) (garden.isSelected() ? 1 : 0));
                newListing.setSmoking((byte) (smokingAllowed.isSelected() ? 1 : 0));
                newListing.setParking((byte) (parking.isSelected() ? 1 : 0));
                newListing.setPricePerNight(Double.parseDouble(pricePerNight.getText()));
                newListing.setDiscount(Integer.parseInt(discountForReturningCustomers.getText()));
                newListing.setImage(imageURL.getText());
                newListing.setDetailedDescription(detailedDescription.getText());
                newListing.setUserByIdUser(Buffer.bufferUser);
                entityManager.persist(newListing);
                transaction.commit();
            }
            finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
            }
            registrationSuccessful = true;
            System.out.println("Registered new listing: \n" + newListing.toString());

        }
    }



    public void SubmitButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        CreateListingFunc();

        if (registrationSuccessful)
        {
            Parent logInParent = FXMLLoader.load(getClass().getResource("CreateListingSuccessful.fxml"));
            Scene logInScene = new Scene(logInParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logInScene);
            window.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Buffer.bufferUser);
    }
}
