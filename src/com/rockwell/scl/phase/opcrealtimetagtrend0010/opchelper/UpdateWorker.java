package com.rockwell.scl.phase.opcrealtimetagtrend0010.opchelper;

import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.util.List;

/**
 * @author RWang18
 */
public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private OPCMonitor monitor;
    private XYChart chart;
    private String equipmentId;
    private String equipmentProperty;

    public UpdateWorker(OPCMonitor monitor, String equipmentId, String equipmentProperty) {

        this.monitor = monitor;
        this.equipmentId = equipmentId;
        this.equipmentProperty = equipmentProperty;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100);
            publish(monitor.getModel().getRealTimeData(equipmentId,equipmentProperty));
        }
    }

    @Override
    protected void process(List<List<Double>[]> chunks) {
        for (List<Double>[] data : chunks) {
            monitor.updateData(data,equipmentProperty);
        }
    }

}
