import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Automation extends Application {

    public void start(Stage primaryStage) throws Exception {

        // Create GridPane at center contains shape
        GridPane center = new GridPane();
        center.setPadding(new Insets(10, 10, 10, 10));
        center.setVgap(4);
        center.setHgap(4);
        center.setGridLinesVisible(true);
        final int numCols = 2;
        final int numRows = 2;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            colConst.setHalignment(HPos.CENTER);
            center.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            rowConst.setValignment(VPos.CENTER);
            center.getRowConstraints().add(rowConst);
        }

        // Create FlowPane at bottom for shape

        FlowPane shapePane = new FlowPane();
        Button btnSphere = new Button();
        btnSphere.setPrefSize(100, 40);
        btnSphere.setText("Sphere");

        Button btnCylinder = new Button();
        btnCylinder.setPrefSize(100, 40);
        btnCylinder.setText("Cylinder");

        Button btnBox = new Button();
        btnBox.setPrefSize(100, 40);
        btnBox.setText("Box");

        Button btnPolygon = new Button();
        btnPolygon.setPrefSize(100, 40);
        btnPolygon.setText("Polygon");

        Label shapeLabel = new Label();
        shapeLabel.setText("Select Shape: ");
        shapePane.getChildren().add(shapeLabel);
        shapePane.getChildren().add(btnSphere);
        shapePane.getChildren().add(btnCylinder);
        shapePane.getChildren().add(btnBox);
        shapePane.getChildren().add(btnPolygon);
        shapePane.setMinSize(800, 100);
        shapePane.setMaxSize(800, 100);
        shapePane.setPadding(new Insets(20, 20, 20, 20));
        shapePane.setHgap(60);

        // Flow Pane for animation

        FlowPane animationPane = new FlowPane();
        ObservableList<String> selection = FXCollections.observableArrayList("Rotate Transition", "Scale Transition",
                "Sequential Transition", "Fade Transition");
        ComboBox<String> comboBox = new ComboBox<>(selection);
        Label animationLabel = new Label();
        animationLabel.setText("Select Animation: ");
        animationPane.getChildren().add(animationLabel);
        animationPane.getChildren().add(comboBox);
        animationPane.setMinSize(800, 100);
        animationPane.setMaxSize(800, 100);
        animationPane.setPadding(new Insets(20, 20, 20, 20));
        animationPane.setHgap(60);

        // Create Objects
        Sphere sphere = new Sphere();
        sphere.setRadius(30f);
        PhongMaterial materialS = new PhongMaterial();
        materialS.setDiffuseColor(javafx.scene.paint.Color.ORANGE);
        materialS.setSpecularColor(javafx.scene.paint.Color.ORANGERED);
        sphere.setMaterial(materialS);

        Cylinder cylinder = new Cylinder();
        cylinder.setRadius(25f);
        cylinder.setHeight(60f);
        PhongMaterial materialC = new PhongMaterial();
        materialC.setDiffuseColor(javafx.scene.paint.Color.DEEPPINK);
        materialC.setSpecularColor(javafx.scene.paint.Color.VIOLET);
        cylinder.setMaterial(materialC);

        Box box = new Box();
        box.setDepth(60f);
        box.setHeight(60f);
        box.setWidth(60f);
        PhongMaterial materialB = new PhongMaterial();
        materialB.setDiffuseColor(javafx.scene.paint.Color.LIGHTGREEN);
        materialB.setSpecularColor(javafx.scene.paint.Color.BLUEVIOLET);
        box.setMaterial(materialB);

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(200.0, 50.0, 100.0, 150.0,
                175.0, 160.0, 140.0, 150.0,
                200.0, 100.0);
        polygon.setFill(javafx.scene.paint.Color.DARKSLATEBLUE);

        // Set Transition
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1200));
        translateTransition.setFromX(0f);
        translateTransition.setToX(100f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

        // Onclick action on button
        btnSphere.setOnAction((e) -> initAnimation(sphere, comboBox));
        btnCylinder.setOnAction((e) -> initAnimation(cylinder, comboBox));
        btnBox.setOnAction((e) -> initAnimation(box, comboBox));
        btnPolygon.setOnAction((e) -> initAnimation(polygon, comboBox));

        center.add(sphere, 0, 0);
        center.add(cylinder, 1, 0);
        center.add(box, 0, 1);
        center.add(polygon, 1, 1);
        BorderPane root = new BorderPane();
        root.setCenter(center);
        root.setBottom(shapePane);
        root.setTop(animationPane);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setX(200);
        primaryStage.setY(200);
        primaryStage.setMinHeight(150);
        primaryStage.setMinWidth(200);
        primaryStage.setTitle("Automation");

        primaryStage.show();
    }

    private void initAnimation(Node object, ComboBox<String> comboBox) {

        if (comboBox.getValue() == "Rotate Transition") {
            RotateTransition rotateTransition = new RotateTransition();
            rotateTransition.setNode(object);
            rotateTransition.setDuration(Duration.millis(1200));
            rotateTransition.setByAngle(720);
            rotateTransition.setCycleCount(2);
            rotateTransition.setAutoReverse(true);
            rotateTransition.play();
        }
        if (comboBox.getValue() == "Scale Transition") {
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(object);
            scaleTransition.setDuration(Duration.millis(1200));
            scaleTransition.setByX(0.5);
            scaleTransition.setByY(0.5);
            scaleTransition.setByZ(0.5);
            scaleTransition.setCycleCount(2);
            scaleTransition.setAutoReverse(true);
            scaleTransition.play();
        }
        if (comboBox.getValue() == "Sequential Transition") {
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1200));
            translateTransition.setFromX(0);
            translateTransition.setToX(60);
            translateTransition.setCycleCount(2);
            translateTransition.setAutoReverse(true);

            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(object);
            scaleTransition.setDuration(Duration.millis(1200));
            scaleTransition.setByX(0.5);
            scaleTransition.setByY(0.5);
            scaleTransition.setByZ(0.5);
            scaleTransition.setCycleCount(2);
            scaleTransition.setAutoReverse(true);

            RotateTransition rotateTransition = new RotateTransition();
            rotateTransition.setNode(object);
            rotateTransition.setDuration(Duration.millis(1200));
            rotateTransition.setByAngle(720);
            rotateTransition.setCycleCount(2);
            rotateTransition.setAutoReverse(true);

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1200));
            translateTransition2.setFromX(0);
            translateTransition2.setToX(-60);
            translateTransition2.setFromY(0);
            translateTransition2.setToY(-20);
            translateTransition2.setCycleCount(2);
            translateTransition2.setAutoReverse(true);

            SequentialTransition sequentialTransition = new SequentialTransition(translateTransition, rotateTransition,
                    scaleTransition, translateTransition2);
            sequentialTransition.setNode(object);
            sequentialTransition.setCycleCount(1);
            sequentialTransition.play();
        }

        if ((comboBox.getValue() == "Fade Transition")) {

            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(1200));
            fadeTransition.setNode(object);
            fadeTransition.setFromValue(1.0f);
            fadeTransition.setToValue(0.1f);
            fadeTransition.setCycleCount(2);
            fadeTransition.setAutoReverse(true);
            fadeTransition.play();

        }

    }

    public static void main(String[] args) {
        Application.launch();
    }

}