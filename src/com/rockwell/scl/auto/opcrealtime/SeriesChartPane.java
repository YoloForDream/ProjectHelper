package com.rockwell.scl.auto.opcrealtime;


import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeriesChartPane extends JPanel implements OPCMonitor {
    private OPCTagMonitorModel model;
    private XYChart chart;
    private String equipmentId;
    private String equipmentProperty;
    public SeriesChartPane(OPCTagMonitorModel model,String equipmentId,String equipmentProperty) throws AutomationException {
        this.model = model;
        this.equipmentId = equipmentId;
        this.equipmentProperty = equipmentProperty;
        chart = new XYChartBuilder().width(800).height(400).title("OPC Real Time Chart-EquipmentName:"+equipmentId).build();
        List<Double>[] realTimeData=model.getRealTimeData(equipmentId,equipmentProperty);
        chart.addSeries(equipmentProperty, realTimeData[0], realTimeData[1]);
//        chart.addSeries(equipmentProperty, realTimeData[2], realTimeData[3]);
        setLayout(new BorderLayout());
        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);
        UpdateWorker worker= new UpdateWorker(this,equipmentId,equipmentProperty);
        worker.execute();
    }
    @Override
    public void updateData(List<Double>[] data,String equipmentProperty) {
        chart.updateXYSeries(equipmentProperty, data[0], data[1], null);
        repaint();
    }



    @Override
    public OPCTagMonitorModel getModel() {
        return model;
    }
}
