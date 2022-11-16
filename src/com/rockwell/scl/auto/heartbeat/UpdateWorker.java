package com.rockwell.scl.auto.heartbeat;

import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.util.List;

/**
 * @author RWang18
 */
public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private ChartMonitor monitor;
    private XYChart chart;

    public UpdateWorker(ChartMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100);
            publish(monitor.getModel().getSineData());
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            monitor.updateData(data);
        }
    }

}
