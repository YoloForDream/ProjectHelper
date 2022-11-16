package com.rockwell.scl.phase.opcrealtimetagtrend0010.opchelper;

import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import javax.swing.*;
import java.awt.*;

public class OPCTagMonitor {

    private String equipmentId;
    private String equipmentProperty;
    private JPanel dataPanel;
    private JPanel layoutPanel;
    public OPCTagMonitor(String equipmentId, String equipmentProperty,JPanel layoutPanel,JPanel dataPanel) {
        this.equipmentId = equipmentId;
        this.equipmentProperty = equipmentProperty;
        this.layoutPanel = layoutPanel;
        this.dataPanel = dataPanel;
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
                     frame.add(layoutPanel);
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
