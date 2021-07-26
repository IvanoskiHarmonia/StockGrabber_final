package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;

public class Content {

    //stuff for the login scene
    Button loginButton = new Button("Login");
    Label welcomeText = new Label("\"Welcome to StockGrabber\"");
    Label userText = new Label("UserName: ");
    TextField usernameField = new TextField();
    Label passText = new Label("Password: ");
    PasswordField passwordField = new PasswordField();
    Label wrongPass = new Label();
    Label notSignedUpL = new Label(" Don't have an account?");
    Button notSignedUp = new Button("Sign up");

    //Stuff for the sign up scene
    //text and password fields for sign up scene
    TextField newUsername = new TextField();
    PasswordField newPass = new PasswordField();
    PasswordField confirmPassword = new PasswordField();

    //buttons and logic for signup scene
    Button signUp = new Button("Confirm");
    Boolean accCreated = false;
    Boolean toSmall = false;
    Button loginSceneButton = new Button("Login");

    //Labels for sign up scene
    Label confirmPasswordL = new Label("confirm password: ");
    Label alreadyExistUsername = new Label();
    Label newPassL = new Label("new password: ");
    Label newUsernameL = new Label("create unique username: ");
    Label accountCreated = new Label();
    Label required = new Label("All of them are required");



    //Buttons Main Scene
    Button grabValue = new Button("Start");
    Button stock = new Button("STOCK?");
    Button crypto = new Button("CRYPTO?");
    Button exitButton = new Button( "Exit");
    Button showChart = new Button("Show Chart");

    //Labels Main Scene
    Label StockPrice = new Label();
    Label differenceFromLastPrice = new Label();
    Label labelCryptoStock = new Label("Stock\t or \tCrypto");
    Label nameOfTheStock = new Label("Stock/Crypto name: ");

    //TextFields Main Scene
    TextField nameOfStock = new TextField();

    //Variables Main Scene
    String price = "0";
    String lastPrice = "0";

    boolean pass = true;
    boolean closeIt = false;
    boolean exists = false;

    //storing passwords and usernames.
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    File file = new File("Customers.csv");
    String row;


    ArrayList<Double> pricesChart = new ArrayList<>();

    String stockOrCrypto = "";
    String nameOfStockCrypto = "";

}
