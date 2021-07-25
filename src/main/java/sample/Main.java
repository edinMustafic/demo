package sample;
/*import entity.Listing;
import entity.User;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try
        {
            transaction.begin();

            User newUser = new User();
            newUser.setIdUser(1);
            newUser.setName("Edin");
            newUser.setLastName("Mustafic");
            newUser.setPhoneNumber("061817940");
            newUser.setUsername("EdoBog");
            newUser.setPassword("zazaPachulia06061944");
            newUser.setIsApproved(1);
            newUser.setBalance(9999.9);
            newUser.setType(1);
            entityManager.persist(newUser);
            transaction.commit();
        }
        finally
        {
            if(transaction.isActive())
            {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}*/

// komentar


import entity.Listing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    Listing l1 = new Listing();
    Listing l = new Listing();

    @Override
    public void start(Stage stage) throws Exception{
        URL resource = getClass().getResource("sample.fxml");
        /*System.out.println(resource.toString());
        System.exit(0);*/
        if(resource == null)
        {
            System.out.println("Hi");
            System.exit(0);
        }
        else {
            Parent root = FXMLLoader.load(resource);
        /*primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 880, 690));
        primaryStage.show();*/
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }

    private static void search()
    {

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
