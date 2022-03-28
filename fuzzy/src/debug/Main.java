package debug;

import fuzzy.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static int numGraph = 0;
    public static ArrayList<Fuzzy> fuzzies = new ArrayList<>();
    public void start(Stage stage){
        stage.setTitle("Построение функции принодлежности!");
        // Определение осей
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        // Создание диограммы
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setMinWidth(600);
        lineChart.setMinHeight(600);
        lineChart.setCreateSymbols(false);
        // Параметры для построенние графика
        // Тип функции принодлежности
        Label typeFun = new Label("Тип функции принодлжености:");
        ObservableList<String> typeGraph = FXCollections.observableArrayList("График 1", "График 2", "График 3",
                "График 4","График 5", "График 6", "График 7","График 8");
        ComboBox<String> typeGraphComboBox = new ComboBox<String>(typeGraph);
        typeGraphComboBox.getSelectionModel().select(0);
        typeGraphComboBox.setMinWidth(180);
        // а,b,c,d параметры
        Label aLabel = new Label("a: ");
        TextField aTextField = new TextField();
        Label bLabel = new Label("b: ");
        TextField bTextField = new TextField();
        Label cLabel = new Label("c: ");
        TextField cTextField = new TextField();
        Label dLabel = new Label("d: ");
        TextField dTextField = new TextField();
        Label countALevelsLabel = new Label("Кол-во а-уровней: ");
        TextField countALevelsTextField = new TextField();
        VBox paraments = new VBox(10);
        //комбокс арефметики
        ObservableList<String> arithmetic  = FXCollections.observableArrayList("+","-","*","/");
        ComboBox<String> arithmeticComboBox = new ComboBox<String>(arithmetic);
        arithmeticComboBox.getSelectionModel().select(0);
        arithmeticComboBox.setMinWidth(180);
        // Выбор графика для арефметики
        ObservableList<String> graphs  = FXCollections.observableArrayList();
        ComboBox<String> graphComboBox1 = new ComboBox<String>(graphs);
        ComboBox<String> graphComboBox2 = new ComboBox<String>(graphs);
        graphComboBox1.setMinWidth(180);
        graphComboBox2.setMinWidth(180);
        // Кнопка для арефмитики
        Button calButton = new Button("Высчитать");
        calButton.setMinWidth(180);
        calButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (numGraph < 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Предупреждение!");
                    alert.setContentText("Должно быть 2 графика!");
                    alert.showAndWait();
                    return;
                }
                try {
                    Fuzzy fuzzy = Fuzzy.arithmetic(arithmeticComboBox.getSelectionModel().getSelectedIndex(),
                            fuzzies.get(Integer.parseInt(String.valueOf(graphComboBox1.getSelectionModel().getSelectedIndex()))),
                            fuzzies.get(Integer.parseInt(String.valueOf(graphComboBox2.getSelectionModel().getSelectedIndex())))
                    );

                    ObservableList<XYChart.Data> points = FXCollections.observableArrayList();
                    Series series = new Series();
                    for (Decomposition d : fuzzy.getDecompositions()){
                        points.add(new XYChart.Data(d.getDownA(),d.getA()));
                    }
                    for (Decomposition d : fuzzy.getDecompositions()){
                        if(d.getUpA() != 0)
                            points.add(new XYChart.Data(d.getUpA(),d.getA()));
                    }
                    series.setData(points);
                    series.setName("График " + arithmeticComboBox.getSelectionModel().getSelectedItem());
                    lineChart.getData().add(series);
                    fuzzies.add(fuzzy);
                    numGraph++;
                } catch (IllegalArgumentException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Предупреждение!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    return;
                }
            }
        });
        // Кнопка чтобы посторить граффик
        Button createGraph = new Button("Построить");
        createGraph.setMinWidth(180);
        createGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (numGraph > 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Предупреждение!");
                    alert.setContentText("Нельзя больше 2 графикиков");
                    alert.showAndWait();
                    return;
                }
                try {
                    Fuzzy fuzzy = new Fuzzy(typeGraphComboBox.getSelectionModel().getSelectedIndex(),
                            Double.parseDouble(aTextField.getText()),
                            Double.parseDouble(bTextField.getText()),
                            Double.parseDouble(cTextField.getText()),
                            Double.parseDouble(dTextField.getText()),
                            Integer.parseInt(countALevelsTextField.getText())
                    );
                    ObservableList<XYChart.Data> points = FXCollections.observableArrayList();
                    Series series = new Series();
                    for (Decomposition d : fuzzy.getDecompositions()){
                        points.add(new XYChart.Data(d.getDownA(),d.getA()));
                    }
                    for (Decomposition d : fuzzy.getDecompositions()){
                        if(d.getUpA() != 0)
                            points.add(new XYChart.Data(d.getUpA(),d.getA()));
                    }
                    numGraph++;
                    series.setData(points);
                    series.setName("График " + numGraph);
                    graphComboBox1.getItems().add(series.getName());
                    graphComboBox2.getSelectionModel().select(0);
                    graphComboBox1.getSelectionModel().select(0);
                    lineChart.getData().add(series);
                    fuzzies.add(fuzzy);
                    System.out.println(fuzzy.getInfoDecomposition());
                } catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setContentText("Вводить можно только числа!");
                    alert.showAndWait();
                }
            }
        });
        // Кнопка чтобы очистить графики
        Button clearGraph = new Button("Очистить");
        clearGraph.setMinWidth(180);
        clearGraph.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lineChart.getData().clear();
                fuzzies.clear();
                numGraph = 0;
                graphComboBox1.getItems().clear();
            }
        });
        Label arithmeticLabel = new Label("Арефметика");
        paraments.setPadding(new Insets(10,0,0,0));
        paraments.getChildren().addAll(typeFun,typeGraphComboBox,
                aLabel,aTextField,
                bLabel,bTextField,
                cLabel,cTextField,
                dLabel,dTextField,
                countALevelsLabel,countALevelsTextField,
                createGraph,
                clearGraph,
                arithmeticLabel,
                graphComboBox1,
                arithmeticComboBox,
                graphComboBox2,
                calButton);
        HBox hBox = new HBox(10,lineChart,paraments);
        VBox root = new VBox(10,hBox);
        Scene scene = new Scene(root,810,640);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
