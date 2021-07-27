package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Chart {
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    public final LineChart<Number,Number> lineChart =
            new LineChart<>(xAxis, yAxis);

}
