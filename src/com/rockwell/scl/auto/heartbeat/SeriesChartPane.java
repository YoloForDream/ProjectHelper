package com.rockwell.scl.auto.heartbeat;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author RWang18
 */
public class SeriesChartPane extends JPanel implements ChartMonitor {

    private HeartMonitorModel model;
    private XYChart chart;

    public SeriesChartPane(HeartMonitorModel model) {
        this.model = model;
        chart = new XYChartBuilder().width(600).height(300).title("Hear Rate?").build();

        List<Double> walkData = model.getWalkData();
        chart.addSeries("Heart Rate", null, walkData);

        List<Double>[] sineData = model.getSineData();
        chart.addSeries("sine", sineData[0], sineData[1]);
        setLayout(new BorderLayout());

        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);

        UpdateWorker worker = new UpdateWorker(this);
        worker.execute();
    }

    @Override
    public HeartMonitorModel getModel() {
        return model;
    }

    @Override
    public void updateData(List<Double>[] data) {
        chart.updateXYSeries("sine", data[0], data[1], null);
        repaint();
    }

}