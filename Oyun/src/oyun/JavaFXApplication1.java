package oyun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class JavaFXApplication1 extends Application {
    
    private Oyun gamer;
    private int hamle;
    private int seviyePuani;
    private Label statusLabel;
    private Label gameStatusLabel;
    private TextField[] selectionFields = new TextField[6];

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Savas Araclari Kart Oyunu");
        ilkSahne(primaryStage);
    }

    private void ilkSahne(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Rectangle rectangle = new Rectangle(400, 200);
        rectangle.setFill(Color.BLACK);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        Label label = new Label("Seviye sayisi giriniz:");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Arial", 18));

        TextField seviyeField = new TextField();
        seviyeField.setPromptText("Seviye sayisini girin");
        seviyeField.setMaxWidth(200);

        Button submitButton = new Button("Gonder");
        submitButton.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 15;"
        );

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.WHITE);
        statusLabel.setFont(new Font("Arial", 16));

        submitButton.setOnAction(e -> {
            String input = seviyeField.getText();
            try {
                seviyePuani = Integer.parseInt(input);
                System.out.println("Seviye sayisi: " + seviyePuani);
                statusLabel.setText("Seviye sayisi " + seviyePuani + " olarak kaydedildi.");
                ikinciSahne(primaryStage);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Lutfen gecerli bir sayi giriniz!");
            }
        });

        VBox rectangleContent = new VBox(15);
        rectangleContent.setAlignment(Pos.CENTER);
        rectangleContent.getChildren().addAll(label, seviyeField, submitButton, statusLabel);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, rectangleContent);
        stackPane.setAlignment(Pos.CENTER);

        root.setCenter(stackPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ikinciSahne(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Rectangle rectangle = new Rectangle(400, 200);
        rectangle.setFill(Color.BLACK);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);

        Label label = new Label("Hamle sayisini giriniz:");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Arial", 18));

        TextField hamleField = new TextField();
        hamleField.setPromptText("Hamle sayisini girin");
        hamleField.setMaxWidth(200);

        Button submitButton = new Button("Gonder");
        submitButton.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 15;"
        );

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.WHITE);
        statusLabel.setFont(new Font("Arial", 16));

        submitButton.setOnAction(e -> {
            String input = hamleField.getText();
            try {
                hamle = Integer.parseInt(input);
                System.out.println("Hamle sayisi: " + hamle);
                statusLabel.setText("Hamle sayisi " + hamle + " olarak kaydedildi.");
                ucuncuSahne(primaryStage, hamle);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Lutfen gecerli bir sayi giriniz!");
            }
        });

        VBox rectangleContent = new VBox(15);
        rectangleContent.setAlignment(Pos.CENTER);
        rectangleContent.getChildren().addAll(label, hamleField, submitButton, statusLabel);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, rectangleContent);
        stackPane.setAlignment(Pos.CENTER);

        root.setCenter(stackPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ucuncuSahne(Stage stage, int hamleSayisi) {
        BorderPane gameRoot = new BorderPane();
        gameRoot.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        gameStatusLabel = new Label();
        gameStatusLabel.setTextFill(Color.WHITE);
        gameStatusLabel.setFont(new Font("Arial", 18));

        HBox imagesBox = new HBox(10);
        imagesBox.setAlignment(Pos.CENTER);

        Image[] cardImages = new Image[6];
        ImageView[] imageViews = new ImageView[6];
        String imageFolderPath = "file:///C:/Users/masum/OneDrive/Desktop/220201142_230201024/Oyun/resources/images/";

        for (int i = 0; i < 6; i++) {
            try {
                String imagePath = imageFolderPath + "image" + (i + 1) + ".png";
                cardImages[i] = new Image(imagePath);
                imageViews[i] = new ImageView(cardImages[i]);
                imageViews[i].setFitWidth(100);
                imageViews[i].setFitHeight(150);

                Label cardLabel = new Label("Kart " + (i));
                cardLabel.setFont(new Font("Arial", 18));
                cardLabel.setTextFill(Color.WHITE);

                VBox cardBox = new VBox(5);
                cardBox.setAlignment(Pos.CENTER);
                cardBox.getChildren().addAll(cardLabel, imageViews[i]);
                imagesBox.getChildren().add(cardBox);

                selectionFields[i] = new TextField();
                selectionFields[i].setPromptText("Secim " + (i + 1));
                selectionFields[i].setMaxWidth(100);

            } catch (IllegalArgumentException ex) {
                System.out.println("Bulamadi: " + (i + 1) + ". Resim yolu: " + imageFolderPath);
            }
        }

        VBox textFieldBox = new VBox(10);
        textFieldBox.setAlignment(Pos.CENTER);

        Label selectLabel = new Label("Lutfen kart seciniz:");
        selectLabel.setTextFill(Color.WHITE);
        selectLabel.setFont(new Font("Arial", 18));

        Button submitSelectionButton = new Button("Secimleri Gonder");
        submitSelectionButton.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-font-size: 16px;" +
            "-fx-padding: 10 20 10 20;" +
            "-fx-background-radius: 15;"
        );

        submitSelectionButton.setOnAction(e -> {
            StringBuilder selections = new StringBuilder();
            boolean tumAlanlarGecerli = true;
            ArrayList<Integer> secilenKartlar = new ArrayList<>();
            
            int maxSecilebilirKartlar = seviyePuani < 20 ? 2 : 5;
        
            for (TextField alan : selectionFields) {
                String girdi = alan.getText().trim();
        
                if (girdi.isEmpty()) {
                    tumAlanlarGecerli = false;
                    selections.append("Alan bos olamaz. ");
                } else {
                    try {
                        int kartNumarasi = Integer.parseInt(girdi);
                        if (kartNumarasi < 0 || kartNumarasi > maxSecilebilirKartlar) {
                            tumAlanlarGecerli = false;
                            selections.append("Gecersiz sayi: " + girdi + ". 0 ile " + maxSecilebilirKartlar + " arasinda olmalidir. ");
                        } else {
                            secilenKartlar.add(kartNumarasi);
                            selections.append(girdi).append(" ");
                        }
                    } catch (NumberFormatException ex) {
                        tumAlanlarGecerli = false;
                        selections.append("Gecersiz girdi: " + girdi + ". Lutfen 0 ile " + maxSecilebilirKartlar + " arasinda bir sayi girin. ");
                    }
                }
            }
        
            if (secilenKartlar.size() != 6) {
                tumAlanlarGecerli = false;
                selections.append("Tam olarak 6 kart secmelisiniz. ");
            }

            if (tumAlanlarGecerli) {
                System.out.println("Secilen kartlar: " + selections);
                gameStatusLabel.setText("Secimlerinizi aldik: " + selections);
                dorduncuSahne(stage, secilenKartlar);
            } else {
                gameStatusLabel.setText("Hatalı girişler var: " + selections);
            }
        });

        textFieldBox.getChildren().addAll(selectLabel);
        textFieldBox.getChildren().addAll(selectionFields);
        textFieldBox.getChildren().add(submitSelectionButton);

        VBox gameContent = new VBox(20);
        gameContent.setAlignment(Pos.CENTER);
        gameContent.getChildren().addAll(imagesBox, textFieldBox, gameStatusLabel);

        gameRoot.setCenter(gameContent);

        Scene gameScene = new Scene(gameRoot, 800, 600);
        stage.setScene(gameScene);
    }

    private void dorduncuSahne(Stage stage, ArrayList<Integer> selectedCards) {
        BorderPane thirdSceneRoot = new BorderPane();
        thirdSceneRoot.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox opponentCardsBox = new HBox(10);
        opponentCardsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 6; i++) {
            ImageView opponentCardView = new ImageView(new Image("file:///C:/Users/masum/OneDrive/Desktop/220201142_230201024/Oyun/resources/images/white.png"));
            opponentCardView.setFitWidth(100);
            opponentCardView.setFitHeight(150);
            opponentCardsBox.getChildren().add(opponentCardView);
        }

        Label opponentCardsLabel = new Label("Rakibin Kartları:");
        opponentCardsLabel.setTextFill(Color.WHITE);
        opponentCardsLabel.setFont(new Font("Arial", 18));

        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(opponentCardsLabel, opponentCardsBox);

        HBox userCardsBox = new HBox(10);
        userCardsBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < selectedCards.size(); i++) {
            int cardIndex = selectedCards.get(i);
            ImageView userCardView = new ImageView(new Image("file:///C:/Users/masum/OneDrive/Desktop/220201142_230201024/Oyun/resources/images/image" + (cardIndex + 1) + ".png"));
            userCardView.setFitWidth(100);
            userCardView.setFitHeight(150);
            userCardsBox.getChildren().add(userCardView);
        }

        Label userCardsLabel = new Label("Secilen Kartlariniz:");
        userCardsLabel.setTextFill(Color.WHITE);
        userCardsLabel.setFont(new Font("Arial", 18));

        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(userCardsLabel, userCardsBox);

        thirdSceneRoot.setTop(topBox);
        thirdSceneRoot.setBottom(bottomBox);

        Scene thirdScene = new Scene(thirdSceneRoot, 800, 600);
        stage.setScene(thirdScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}