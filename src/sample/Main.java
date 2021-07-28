package sample;

import javafx.animation.*;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.lang.*;
import java.io.*;

import javafx.util.Duration;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //login grid
        GridPane loginGrid = new GridPane();
        Scene loginScene = new Scene(loginGrid, 400, 350);

        //Sign up grid
        GridPane signUpGrid = new GridPane();
        Scene signUpScene = new Scene(signUpGrid,  400, 350);

        //Main grabber Scene grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10 ,10));
        Scene scene = new Scene(grid, 700, 380);

        grid.setHgap(8);
        grid.setVgap(10);
        loginGrid.setHgap(8);
        loginGrid.setVgap(10);
        signUpGrid.setHgap(8);
        signUpGrid.setVgap(10);

        Content content = new Content();
        StockFetcher stockFetch = new StockFetcher();
        Controller controller = new Controller();
        Chart chart = new Chart();


        //linking the CSS with JavaFx
        grid.getStylesheets().add(getClass().getResource("style.css").toString());
        loginGrid.getStylesheets().add(getClass().getResource("style.css").toString());
        signUpGrid.getStylesheets().add(getClass().getResource("style.css").toString());


        grid.add(content.labelCryptoStock, 0, 0);
        content.labelCryptoStock.setStyle( "-fx-text-fill: white;");
        grid.add(content.stock, 0, 1);
        grid.add(content.crypto, 1, 1);
        grid.add(content.wrongStock, 1, 3);
        content.stock.getStyleClass().add("cryptoStock");
        content.crypto.getStyleClass().add("cryptoStock");
        content.stock.setOnAction(ex -> content.stockOrCrypto = "stocks");
        content.crypto.setOnAction(ex -> content.stockOrCrypto = "crypto");


        grid.add(content.nameOfStock, 1, 2);
        grid.add(content.nameOfTheStock,  0, 2);
        content.nameOfTheStock.setStyle( "-fx-text-fill: white;");
        content.nameOfStock.getStyleClass().add("styleText");

        //stock button
        grid.add(content.grabValue, 0, 3);
        content.grabValue.getStyleClass().add("stock");

        grid.add(content.StockPrice, 0, 4);
        content.StockPrice.getStyleClass().add("StockPrice");
        grid.add(content.differenceFromLastPrice, 1, 4);

        grid.add(content.showChart, 6,8);

        grid.add(content.exitButton, 10, 10);
        content.exitButton.getStyleClass().add( "Exit");
        content.exitButton.setOnAction(e -> {
            Platform.setImplicitExit(true);
            Platform.exit();
        });

        //login grid things
        loginGrid.add(content.welcomeText, 5, 0);
        loginGrid.add(content.userText, 0, 1);
        loginGrid.add(content.usernameField, 1, 1);
        loginGrid.add(content.passText, 0, 2);
        loginGrid.add(content.passwordField, 1, 2);
        loginGrid.add(content.wrongPass, 1,3);
        loginGrid.add(content.loginButton, 1, 4);
        loginGrid.add(content.notSignedUpL, 0, 15);
        loginGrid.add(content.notSignedUp, 1, 15);
        content.welcomeText.getStyleClass().add( "welcomeText");
        content.userText.getStyleClass().add( "turnWhite");
        content.passText.getStyleClass().add( "turnWhite");
        content.notSignedUpL.getStyleClass().add( "turnWhite");
        content.notSignedUp.getStyleClass().add( "notSignedUpB");
        content.loginButton.getStyleClass().add( "loginButton");
        content.wrongPass.setStyle( "-fx-text-fill: red;");


        //Sign up grid content
        signUpGrid.add(content.newUsernameL, 0, 1);
        signUpGrid.add(content.newUsername, 1, 1);
        signUpGrid.add(content.alreadyExistUsername, 1, 2);
        signUpGrid.add(content.newPassL, 0, 3);
        signUpGrid.add(content.newPass, 1, 3);
        signUpGrid.add(content.confirmPasswordL, 0, 4);
        signUpGrid.add(content.confirmPassword, 1, 4);
        signUpGrid.add(content.required,1,5);
        signUpGrid.add(content.signUp, 1, 6);
        signUpGrid.add(content.accountCreated, 1, 7);
        signUpGrid.add(content.loginSceneButton, 1, 9);
        content.loginSceneButton.getStyleClass().add( "notSignedUpB");
        content.signUp.getStyleClass().add( "SignUpButton");
        content.newUsernameL.getStyleClass().add( "turnWhite");
        content.confirmPasswordL.getStyleClass().add( "turnWhite");
        content.required.getStyleClass().add( "turnWhite");
        content.newPassL.getStyleClass().add( "turnWhite");



        //transition from adding to the scenes to code that controls the stuff on the scenes


        //reading for the login
        BufferedReader csvReader = new BufferedReader(new FileReader("Customers.csv"));
        while ((content.row = csvReader.readLine()) != null){
            String[] data = content.row.split(",");
            content.usernames.add(data[0]);
            content.passwords.add(data[1]);
        }

        //login scene code
        content.loginButton.setOnAction(e -> {
            for(int i = 0; i < content.usernames.size(); ++i){
                if(content.passwordField.getText().equals( controller.hexToStringReverted(content.passwords.get(i))) && content.usernameField.getText().equalsIgnoreCase(content.usernames.get(i))){
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                else{
                    if(i == content.usernames.size()-1)
                        content.wrongPass.setText( "Wrong Password or wrong Username, Try Again");
                }
            }
        });


        PrintWriter write = new PrintWriter(new FileWriter(content.file, true));

        //writing aka Sign up
        content.signUp.setOnAction(e -> new Thread(() -> {
            for(int i = 0; i < content.usernames.size(); ++i){
                if(content.newUsername.getText().equalsIgnoreCase(content.usernames.get(i)))
                    content.exists = true;
                if(content.newPass.getText().length() < 7)
                    content.toSmall = true;
                if(!content.exists && i == content.usernames.size()-1 && !content.toSmall && (content.newPass.getText().equals(content.confirmPassword.getText()))) {
                    write.append("\n").append(content.newUsername.getText()).append(",").append(controller.stringToHexInverted(content.newPass.getText()));
                    write.close();
                    content.usernames.add(content.newUsername.getText());
                    content.passwords.add(controller.stringToHexInverted(content.newPass.getText()));
                    content.accCreated = true;
                    break;
                }
                content.closeIt = true;
            }
            if(content.closeIt)
                write.close();

            // update ProgressIndicator on FX thread
            Platform.runLater(() -> {
                if(content.accCreated){
                    content.accountCreated.setStyle( "-fx-text-fill: green;");
                    content.accountCreated.setText("Your account was created, Welcome!");
                    content.required.setText("");
                    content.alreadyExistUsername.setText("");
                    content.exists = false;
                    content.toSmall = false;
                }
                if(content.exists){
                    content.alreadyExistUsername.setText("that username already exist!");
                    content.alreadyExistUsername.setStyle( "-fx-text-fill: red;");
                }
                if(content.toSmall){
                    content.required.setStyle( "-fx-text-fill: red;");
                    content.required.setText("Password much have more than 8 characters.");
                }


            });

        }).start());
        //end of the writing in the customer.csv file




        //Chart in the main scene
        content.showChart.setOnAction(e -> {
            if(!content.deleteChart){
                grid.add(chart.lineChart, 7 , 4);
                primaryStage.setMaximized(true);
                content.deleteChart = true;
            }
            chart.xAxis.setForceZeroInRange(false);
            chart.yAxis.setForceZeroInRange(false);
            chart.lineChart.setCreateSymbols(false);

            chart.xAxis.setLabel("Number of seconds after start of monitoring");

            chart.lineChart.setTitle("Stock Monitoring, 2021");
            //updating the UI every 2 sec, works like magic lol:D
            Timeline chartPrices2 = new Timeline(
                    new KeyFrame(Duration.seconds(2),
                            event -> {
                                if(content.getPricesChart().size() >= 10){
                                    chart.lineChart.getData().clear();

                                    XYChart.Series series = new XYChart.Series();
                                    series.setName("My portfolio");

                                    //populating the series with data
                                    for(int i = 0; i < content.getPricesChart().size(); i+= content.getPricesChart().size()/10){
                                        series.getData().add(new XYChart.Data(i, content.getPricesChart().get(i)));
                                    }

                                    chart.lineChart.getData().add(series);
                                }
                            }));
                chartPrices2.setCycleCount(Timeline.INDEFINITE);
                chartPrices2.play();
        });


        //Class for the method I have to grab the price of any stock/crypto
        content.grabValue.setOnAction(e -> {
            content.setAlreadyCheckedURL(false);
            try{
                if(!content.nameOfStockCrypto.equalsIgnoreCase(content.nameOfStock.getText()) && !stockFetch.stock(content.stockOrCrypto,content.nameOfStock.getText().toUpperCase()).equalsIgnoreCase("incorrect stock/crypto")){
                    content.wrongStock.setStyle("-fx-text-fill: green;");
                    content.wrongStock.setText("Correct Stock");
                    content.nameOfStockCrypto = content.nameOfStock.getText().toUpperCase();
                    content.setAlreadyCheckedURL(true);
                    new Timer().schedule(
                            new TimerTask() {

                                @Override
                                public void run() {
                                    try{
                                        if(content.pass){
                                            content.lastPrice = content.price;
                                            content.price = stockFetch.stock( content.stockOrCrypto , content.nameOfStockCrypto);
                                            content.pricesChartlist.add(Double.parseDouble(content.price));
                                            content.setPricesChart(content.pricesChartlist);
                                            content.pass = false;
                                            content.i++;
                                        }
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }

                                }
                            }, 0, 500);
                    Timeline settingPrices = new Timeline(
                            new KeyFrame(Duration.seconds(0.5),
                                    event -> {
                                        if(!content.pass){
                                            content.StockPrice.setText("$"+content.price);
                                            String diff = String.format( "%.6f", Double.parseDouble(content.price) - Double.parseDouble(content.lastPrice));
                                            content.differenceFromLastPrice.setText( diff);
                                            content.differenceFromLastPrice.setStyle(Double.parseDouble(content.price) - Double.parseDouble(content.lastPrice) >= 0 ? "-fx-background-color: green; -fx-text-fill: white;" : "-fx-background-color: red; -fx-text-fill: white;");
                                            content.StockPrice.setStyle(Double.parseDouble(content.price) >= Double.parseDouble(content.lastPrice) ? "-fx-effect: dropshadow( gaussian , #033500 , 1,3,2,5 );-fx-background-color: green; -fx-text-fill: white;" : "-fx-effect: dropshadow( gaussian , #800000 , 1,2,1,3 );-fx-background-color: red; -fx-text-fill: white;");
                                            content.pass = true;
                                        }
                                    }));
                    settingPrices.setCycleCount(Timeline.INDEFINITE);
                    settingPrices.play();
                }else {
                    content.wrongStock.setStyle("-fx-text-fill: red;");
                    content.wrongStock.setText("Wrong Stock, or incorrect specifications");
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });

        primaryStage.setTitle("Welcome to Martin's Stock Grabber with API");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        //Platform.setImplicitExit(false);

        //changing scenes
        content.notSignedUp.setOnAction(e -> {
            primaryStage.setScene(signUpScene);
            primaryStage.show();
        });
        content.loginSceneButton.setOnAction(e -> {
            primaryStage.setScene(loginScene);
            primaryStage.show();
        });

    }

    public static void main(String[] args) { launch(args); }

}
