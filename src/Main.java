import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.math.BigInteger;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Podpis cyfrowy");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(5);
        grid.setHgap(5);

        Label signLabel = new Label("Podaj ścieżkę do pliku do podpisania");
        GridPane.setConstraints(signLabel, 0, 0);

        TextField fileInput = new TextField();
        fileInput.setPrefWidth(300);
        GridPane.setConstraints(fileInput, 0, 1);

        Button buttonSign = new Button("Podpisz");
        GridPane.setConstraints(buttonSign, 1,1);

        Label verifyLabel = new Label("Podaj ścieżkę do pliku do sprawdzenia");
        GridPane.setConstraints(verifyLabel, 0,2);

        TextField verifyInput = new TextField();
        verifyInput.setPrefWidth(300);
        GridPane.setConstraints(verifyInput, 0,3);

        Button buttonVerify = new Button("Sprawdź");
        GridPane.setConstraints(buttonVerify,1,3);

        Files files = new Files();

        buttonSign.setOnAction(e ->{
            Signature signature = new Signature();
            signature.keyGen();
            files.publicKeyToFile(signature.getP(),signature.getG(),signature.getA());
            byte[] toSign = files.readFromFile(fileInput.getText());
            signature.generate(toSign);
            files.SignatureToFile(signature.getR(),signature.getS());
        });

        buttonVerify.setOnAction(e ->{
            BigInteger key[] = files.readNumbers("C:\\Users\\Łukasz\\Desktop\\key.txt", 3);
            BigInteger sign[] = files.readNumbers("C:\\Users\\Łukasz\\Desktop\\signature.txt", 2);
            byte[] toVerify = files.readFromFile(verifyInput.getText());
            Verification  verification = new Verification(key[0],key[1],key[2],files.hashAFile(toVerify),sign[0],sign[1]);
            if (verification.verify())AlertBox.display("Podpis jest prawidłowy");
            else AlertBox.display("Podpis jest nieprawidłowy");
            /*if (verification.verify())System.out.println("siuuuusiaaaak");
            else System.out.println("SIUSIAG");*/ //to jest jakby alertbox miał nie działąć ale na 95% działa
        });

        grid.getChildren().addAll(signLabel,fileInput,buttonSign,verifyLabel,verifyInput,buttonVerify);
        Scene scene = new Scene(grid, 400, 275);
        window.setScene(scene);
        window.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
