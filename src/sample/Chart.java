package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class Chart {
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    public final LineChart<Number,Number> lineChart =
            new LineChart<>(xAxis, yAxis);

    private XYChart.Series series = new XYChart.Series();

    public void setSeries(XYChart.Series series) {
        this.series = series;
    }

    public XYChart.Series getSeries() {
        return series;
    }
}
