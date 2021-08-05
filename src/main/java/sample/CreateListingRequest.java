package sample;

import entity.Listing;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateListingRequest
{
    @FXML
    private Label listingTitle;
    @FXML
    private Label country;
    @FXML
    private Label address;
    @FXML
    private Label type;
    @FXML
    private Label noOfRooms;
    @FXML
    private Label noOfBeds;
    @FXML
    private Label area;
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
    private Label pricePerNight;
    @FXML
    private CheckBox freeCancellation;
    @FXML
    private Label discountForReturningCustomers;
    @FXML
    private Label imageURL;
    @FXML
    private Label detailedDescription;




    public void SetFields(Listing listing)
    {
        listingTitle.setText(listing.getTitle());
        country.setText(listing.getCountry());
        address.setText(listing.getAddress());
        switch(listing.getType()) {
            case 1: {
                type.setText("House");
                break;
            }
            case 2: {
                type.setText("Villa");
                break;
            }
            case 3: {
                type.setText("Apartment");
                break;
            }
            case 4: {
                type.setText("Room");
                break;
            }
            case 5:
            {
                type.setText("Cottage");
                break;
            }
        }
        noOfRooms.setText(String.valueOf(listing.getNumberOfRooms()));
        noOfBeds.setText(String.valueOf(listing.getNumberOfBeds()));
        area.setText(String.valueOf(listing.getSurfaceArea()));
        petsAllowed.setSelected(listing.getPets() == 1 ? true : false);
        wiFi.setSelected(listing.getWiFi() == 1 ? true : false);
        tv.setSelected(listing.getTv() == 1 ? true : false);
        ac.setSelected(listing.getAc() == 1 ? true : false);
        kitchen.setSelected(listing.getKitchen() == 1 ? true : false);
        balcony.setSelected(listing.getBalcony() == 1 ? true : false);
        garden.setSelected(listing.getGarden() == 1 ? true : false);
        smokingAllowed.setSelected(listing.getSmoking() == 1 ? true : false);
        parking.setSelected(listing.getParking() == 1 ? true : false);
        pricePerNight.setText(String.valueOf(listing.getPricePerNight()));
        //freeCancellation.setSelected(listing.get() == 1 ? true : false);
        discountForReturningCustomers.setText(String.valueOf(listing.getDiscount()));
        imageURL.setText(listing.getImage());
        detailedDescription.setText(listing.getDetailedDescription());


    }

    public void BackButtonPressed(javafx.event.ActionEvent event) throws Exception
    {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
