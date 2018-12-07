import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Hello World");

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

        grid.getChildren().addAll(signLabel,fileInput,buttonSign,verifyLabel,verifyInput,buttonVerify);
        Scene scene = new Scene(grid, 400, 275);
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
