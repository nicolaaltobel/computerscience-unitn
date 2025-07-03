package com.company.view;

import com.company.data.Sale;
import com.company.model.SalesList;
import com.company.data.Vehicle;
import com.company.model.ReadFile2;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import java.util.Collections;

public class Window extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // variables
    public static int buttonWidth= 250;
    ReadFile2 vehicles;
    SalesList sales;
    //  --- graphic elements

    // alerts
    Alert invalidInputAlert;
    Alert saleAlert;

    // text areas
    TextArea vehiclesTextArea;
    TextArea salesTextArea;

    // buttons for vehicles list
    Button orderVehiclesbyYearB;
    Button orderVehiclesbyMakerB;
    Button orderVehiclesbyWeightB;


    // button to buy
    Button buyButton;

    // buttons to show sales
    Button orderSalesByCostB;
    Button orderSalesByDateB;
    Button orderSalesByMakerB;

    // fields to buy vehicles
    TextField IDtoBuyField;
    TextField amountToBuyField;

    @Override
    public void start(Stage primaryStage) {
        vehicles = new ReadFile2("src/com/company/files/pricelist.txt");
        sales = new SalesList();

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1600, 700);

        // --- setup alerts

        // invalid input alert
        invalidInputAlert = new Alert(Alert.AlertType.ERROR);
        invalidInputAlert.setTitle("Invalid Input");

        // successful sale message box
        saleAlert = new Alert(Alert.AlertType.INFORMATION);
        saleAlert.setTitle("Sale correctly entered");


        // --- set up the text areas
        vehiclesTextArea = new TextArea();
        vehiclesTextArea.setPrefSize(780, 500);
        vehiclesTextArea.setEditable(false);
        vehiclesTextArea.setText(vehicles.toString()); // already displays the vehicles

        salesTextArea = new TextArea();
        salesTextArea.setPrefSize(780, 500);
        salesTextArea.setEditable(false);

        // --- set up the buttons

        // order vehicles by year
        orderVehiclesbyYearB = new Button("Order vehicles by year");
        orderVehiclesbyYearB.setPrefWidth(buttonWidth);
        orderVehiclesbyYearB.setOnAction(e -> {
            Collections.sort(vehicles.catalog);
            vehiclesTextArea.setText(vehicles.toString());
        });

        // order vehicles by maker
        orderVehiclesbyMakerB = new Button("Order vehicles by Maker");
        orderVehiclesbyMakerB.setPrefWidth(buttonWidth);
        orderVehiclesbyMakerB.setOnAction(e -> {
            Collections.sort(vehicles.catalog, Vehicle.makerComparator);
            vehiclesTextArea.setText(vehicles.toString());
        });

        // order vehicles by weight
        orderVehiclesbyWeightB = new Button("Order vehicles by weight");
        orderVehiclesbyWeightB.setPrefWidth(buttonWidth);
        orderVehiclesbyWeightB.setOnAction(e -> {
            Collections.sort(vehicles.catalog, Vehicle.weightComparator);
            vehiclesTextArea.setText(vehicles.toString());
        });


        // buy button
        buyButton = new Button("Buy");
        buyButton.setPrefWidth(buttonWidth);
        buyButton.setOnAction(e -> {buy(IDtoBuyField.getText(), amountToBuyField.getText());});

        // order sales by cost
        orderSalesByCostB = new Button("Order sales by cost");
        orderSalesByCostB.setPrefWidth(buttonWidth);
        orderSalesByCostB.setOnAction(e -> {
            Collections.sort(sales);
            salesTextArea.setText(sales.toString());
        });

        // order sales by date
        orderSalesByDateB = new Button("Order sales by date");
        orderSalesByDateB.setPrefWidth(buttonWidth);
        orderSalesByDateB.setOnAction(e -> {
            Collections.sort(sales, Sale.dateComparator);
            salesTextArea.setText(sales.toString());
        });

        // order sales by maker
        orderSalesByMakerB = new Button("Order sales by Maker");
        orderSalesByMakerB.setPrefWidth(buttonWidth);
        orderSalesByMakerB.setOnAction(e -> {
            Collections.sort(sales, Sale.makerComparator);
            salesTextArea.setText(sales.toString());
        });


        // --- set up the fields

        IDtoBuyField = new TextField();
        IDtoBuyField.setPromptText("Enter Vehicle ID...");
        IDtoBuyField.setPrefWidth(buttonWidth);

        amountToBuyField = new TextField();
        amountToBuyField.setPromptText("Enter Amount to buy...");
        amountToBuyField.setPrefWidth(buttonWidth);

        // --- scene organization

        // a horizontal box for the text areas
        HBox textAreasBox = new HBox(10, vehiclesTextArea, salesTextArea);
        textAreasBox.setAlignment(Pos.CENTER);

        // one for the vehicle list buttons
        HBox mainButtonsBox = new HBox(10, orderVehiclesbyYearB, orderVehiclesbyMakerB, orderVehiclesbyWeightB);
        mainButtonsBox.setAlignment(Pos.CENTER);

        // one for the buy stuff
        HBox buyItemsBox = new HBox(10,IDtoBuyField, amountToBuyField, buyButton);
        buyItemsBox.setAlignment(Pos.CENTER);

        // one for the sales list buttons
        HBox boughtItemsBox = new HBox(10, orderSalesByCostB, orderSalesByDateB, orderSalesByMakerB);
        boughtItemsBox.setAlignment(Pos.CENTER);

        // put all the hboxes in a vertical box
        VBox pane = new VBox(10,textAreasBox,mainButtonsBox,buyItemsBox,boughtItemsBox);

        // add the vertical box in the root
        root.getChildren().add(pane);

        // show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vehicle shop");
        primaryStage.show();
    }

    // function to buy vehicles
    public void buy(String vehicleID, String amount){
        // parse the vehicleID and the amount to an int, if error call the invalidInput alert
        int id;
        int am;
        boolean found = false;
        Sale s;

        try{
            id = Integer.parseInt(vehicleID);
            am = Integer.parseInt(amount);

            if (am <= 0){
                throw new Exception();
            }

            for (Vehicle v : vehicles.catalog){
                if (v.getId() == id){
                    found = true;
                    s = new Sale(v.getId(), am, v.getMaker(), v.getModel(), v.getCost());
                    sales.add(s); // add this sale to the sales list

                    // update the sales text area
                    salesTextArea.setText(sales.toString());

                    // display the receipt alert
                    saleAlert.setContentText("Sale correctly entered. Details of the sale:\n" + s.toString());
                    saleAlert.showAndWait();

                    break;
                }
            }
            if (!found){ // if we can't find such vehicle, throw an exception
                throw new Exception();
            }

        } catch (NumberFormatException e){ // invoked by Int.parseInt if it goes wrong
            // show the invalid input alert
            invalidInputAlert.setContentText("Invalid Vehicle ID or invalid amount. Try again.");
            invalidInputAlert.showAndWait();
        } catch (Exception e){ // if it's not a parsing error, it means that it did not find the vehicle id in the list, or that the amount is <= 0
            // show the invalid input alert
            invalidInputAlert.setContentText("Vehicle ID not found in vehicles list or invalid amount. Try again.");
            invalidInputAlert.showAndWait();
        }
    }
}
