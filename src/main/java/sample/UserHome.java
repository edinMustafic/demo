package sample;

import entity.Listing;
import entity.Reservation;
import entity.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


public class UserHome implements Initializable
{

    @FXML
    private HBox listing1;
    @FXML
    private HBox listing2;
    @FXML
    private HBox listing3;
    @FXML
    private HBox listing4;
    @FXML
    private HBox listing5;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView imageView1;
    @FXML
    private Label title1;
    @FXML
    private Button deleteButton1;
    @FXML
    private Label grade1;
    @FXML
    private Button view1;

    @FXML
    private ImageView imageView2;
    @FXML
    private Label title2;
    @FXML
    private Button deleteButton2;
    @FXML
    private Label grade2;
    @FXML
    private Button view2;

    @FXML
    private ImageView imageView3;
    @FXML
    private Label title3;
    @FXML
    private Button deleteButton3;
    @FXML
    private Label grade3;
    @FXML
    private Button view3;

    @FXML
    private ImageView imageView4;
    @FXML
    private Label title4;
    @FXML
    private Button deleteButton4;
    @FXML
    private Label grade4;
    @FXML
    private Button view4;

    @FXML
    private ImageView imageView5;
    @FXML
    private Label title5;
    @FXML
    private Button deleteButton5;
    @FXML
    private Label grade5;
    @FXML
    private Button view5;

    @FXML
            private Label username;

    List<Reservation> l;
    int timesClicked = 0;

    public void Logout(ActionEvent event) throws Exception
    {
        Buffer.userLoggedInFlag = false;
        LoadHomeScene(event);
    }

    public void LoadHomeScene(ActionEvent event) throws Exception
    {
        System.out.println("===============================");
        Parent logInParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene logInScene = new Scene(logInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }

    public void CreateListingButtonPressed(ActionEvent event) throws Exception
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

    public void SetListingsInvisible()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
    }

    private void GetAllListings()
    {
        l = (List<Reservation>) Buffer.bufferUser.getReservationsByIdUser();
    }

    private void ShowNextFiveListings(int startIndex)
    {
        if(startIndex == 0)
        {
            backButton.setVisible(false);
        }

        int upperBound = l.size() < 5 ? l.size() : 5;
        for(int i = 0; i < upperBound; i++)
        {
            if(i+startIndex < l.size())
            {
                Reservation currentReservation = l.get(i+startIndex);
                System.out.println("sample/"+(currentReservation.getListingByIdListing().getImage()).substring(1));
                Image image = new Image("sample/"+(currentReservation.getListingByIdListing().getImage()).substring(1));
                EventHandler<ActionEvent> editListing = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event)
                    {
                        System.out.println("Treba otvorit Listing");
                        try {
                            Buffer.bufferReservation = currentReservation;
                            //Load second scene
                            System.out.println("1");
                            FXMLLoader loader = new FXMLLoader(Main.class.getResource("BookListing.fxml"));
                            System.out.println("2");
                            Parent root = (Parent) loader.load();

                            System.out.println("3");
                            //Get controller of scene2
                            BookListing scene2Controller = loader.getController();
                            //Pass whatever data you want. You can have multiple method calls here
                            scene2Controller.ViewBooking();

                            //Show scene 2 in new window
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Second Window");
                            stage.show();
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
                };
                EventHandler<ActionEvent> deleteListing = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("Treba odbit listing");

                        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
                        EntityManager entityManager = entityManagerFactory.createEntityManager();
                        EntityTransaction transaction = entityManager.getTransaction();
                        try {
                            transaction.begin();
                            Reservation u = entityManager.find(Reservation.class, currentReservation.getIdReservation());
                            entityManager.remove(u);
                            transaction.commit();
                        } finally {
                            if (transaction.isActive()) {
                                transaction.rollback();
                            }
                            entityManager.close();
                            entityManagerFactory.close();
                        }

                        EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("default");
                        EntityManager entityManager1 = entityManagerFactory1.createEntityManager();
                        EntityTransaction transaction1 = entityManager1.getTransaction();

                        try
                        {
                            transaction1.begin();

                            List<User> li;

                            Query q = entityManager1.createQuery("SELECT e FROM User e WHERE e.username = :UN");
                            q.setParameter("UN", Buffer.bufferUser.getUsername());
                            li = q.getResultList();


                            for (Object p : li) {
                                System.out.println(p);
                            }

                            if(li.isEmpty())
                            {
                                System.out.println("User with username " + username.getText() + " does not exist in the database");
                                return;
                            }
                            else
                            {
                                System.out.println("User with username " + username.getText() + " exists in the database");
                                Buffer.bufferUser = li.get(0);
                            }

                            transaction1.commit();
                        }
                        finally {
                            if (transaction1.isActive()) {
                                transaction1.rollback();
                            }
                            entityManager1.close();
                            entityManagerFactory1.close();
                        }

                        Parent logInParent = null;
                        try {
                            logInParent = FXMLLoader.load(getClass().getResource("UserHome.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Scene logInScene = new Scene(logInParent);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(logInScene);
                        window.show();
                    }
                };
                switch (i)
                {

                    case 0:
                    {
                        imageView1.setImage(image);
                        title1.setText(currentReservation.getListingByIdListing().getTitle());
                        deleteButton1.setOnAction(deleteListing);
                        view1.setOnAction(editListing);
                        listing1.setVisible(true);
                        break;
                    }
                    case 1:
                    {
                        imageView2.setImage(image);
                        title2.setText(currentReservation.getListingByIdListing().getTitle());
                        deleteButton2.setOnAction(deleteListing);
                        view2.setOnAction(editListing);
                        listing2.setVisible(true);
                        break;
                    }
                    case 2:
                    {
                        imageView3.setImage(image);
                        title3.setText(currentReservation.getListingByIdListing().getTitle());
                        deleteButton3.setOnAction(deleteListing);
                        //details3.setText(currentListing.getDetailedDescription());
                        view3.setOnAction(editListing);
                        listing3.setVisible(true);
                        break;
                    }
                    case 3:
                    {
                        imageView4.setImage(image);
                        title4.setText(currentReservation.getListingByIdListing().getTitle());
                        deleteButton4.setOnAction(deleteListing);
                        //details4.setText(currentListing.getDetailedDescription());
                        view4.setOnAction(editListing);
                        listing4.setVisible(true);
                        break;
                    }
                    case 4:
                    {
                        imageView5.setImage(image);
                        title5.setText(currentReservation.getListingByIdListing().getTitle());
                        deleteButton5.setOnAction(deleteListing);
                       // details5.setText(currentListing.getDetailedDescription());
                        view5.setOnAction(editListing);
                        listing5.setVisible(true);
                        break;
                    }
                }
            }
            else
            {
                nextButton.setVisible(false);
                break;
            }
        }
    }


    public void NextPageClicked()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
        backButton.setVisible(true);
        timesClicked++;
        ShowNextFiveListings(timesClicked*5);
    }

    public void PreviousPageClicked()
    {
        listing1.setVisible(false);
        listing2.setVisible(false);
        listing3.setVisible(false);
        listing4.setVisible(false);
        listing5.setVisible(false);
        backButton.setVisible(true);
        timesClicked--;
        nextButton.setVisible(true);
        ShowNextFiveListings(timesClicked*5);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setVisible(false);
        username.setText(Buffer.bufferUser.getUsername());
        timesClicked = 0;
        SetListingsInvisible();
        GetAllListings();
        ShowNextFiveListings(0);
    }
}
