package com.rockwell.scl.auto.opcrealtime;

import com.rockwell.mes.apps.ebr.ifc.swing.PhaseSwingHelper;
import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import javax.swing.*;
import java.awt.*;
import  java.util.List;

public class OPCTagMonitor {

    private String equipmentId;
    private String equipmentProperty;
    private List<String> equipmentProperties;

    private JPanel dataPanel= PhaseSwingHelper.createPanel();
    private JPanel dataPanel1= PhaseSwingHelper.createPanel();
    public OPCTagMonitor(String equipmentId, String equipmentProperty){
        this.equipmentId = equipmentId;
        this.equipmentProperty = equipmentProperty;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                OPCTagMonitorModel model = new DefaultOPCTagMonitorModel(equipmentId,equipmentProperty);
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////                JPanel p = new JPanel();
//                try {
//                    p.add(new SeriesChartPane(model,equipmentId,equipmentProperty));
//                    frame.add(p);
//                } catch (AutomationException e) {
//                    e.printStackTrace();
//                }
                try {
                     dataPanel.add(new SeriesChartPane(model,equipmentId,equipmentProperty));
                     frame.add(dataPanel,BorderLayout.CENTER);
                } catch (AutomationException e) {
                    e.printStackTrace();
                }

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
//                p.setVisible(true);

            }
        });
    }
    public OPCTagMonitor(String equipmentId, List<String> equipmentProperties){
        this.equipmentId = equipmentId;
        this.equipmentProperties = equipmentProperties;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
//                OPCTagMonitorModel model = new DefaultOPCTagMonitorModel(equipmentId,equipmentProperties.get(0));
//                OPCTagMonitorModel model1 = new DefaultOPCTagMonitorModel(equipmentId,equipmentProperties.get(1));
                JFrame frame = new JFrame("OPC RealTime Tested");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////                JPanel p = new JPanel();
//                try {
//                    p.add(new SeriesChartPane(model,equipmentId,equipmentProperty));
//                    frame.add(p);
//                } catch (AutomationException e) {
//                    e.printStackTrace();
//                }
                try {
                    for(int i=0;i<equipmentProperties.size();i++) {
                        OPCTagMonitorModel model = new DefaultOPCTagMonitorModel(equipmentId,equipmentProperties.get(i));
                        dataPanel.add(new SeriesChartPane(model,equipmentId,equipmentProperties.get(i)));
                        frame.add(dataPanel);
                    }
//                    dataPanel.add(new SeriesChartPane(model,equipmentId,equipmentProperties.get(0)));
//                    dataPanel1.add(new SeriesChartPane(model1,equipmentId,equipmentProperties.get(1)));
//                    frame.add(dataPanel,BorderLayout.NORTH);
//                    frame.add(dataPanel1,BorderLayout.CENTER);
                } catch (AutomationException e) {
                    e.printStackTrace();
                }

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
//                p.setVisible(true);

            }
        });
    }
}
