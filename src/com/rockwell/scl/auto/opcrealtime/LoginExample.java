package com.rockwell.scl.auto.opcrealtime;

import com.rockwell.mes.commons.base.ifc.services.ServiceFactory;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentProperty;
import com.rockwell.mes.services.s88equipment.ifc.IMESS88Equipment;
import com.rockwell.mes.services.s88equipment.ifc.IS88EquipmentService;
import com.rockwell.mes.services.s88equipment.ifc.automation.IAutomationService;
import com.rockwell.scl.auto.ReadTag;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LoginExample extends JFrame {

    public static void main(String[] args) {
        LoginExample frame = new LoginExample();
    }

    JButton button = new JButton("Submit");

    JPanel panel = new JPanel();

    JTextField textField = new JTextField(15);

    JPasswordField jPasswordField = new JPasswordField(15);

    JLabel label = new JLabel();

    JLabel label2 = new JLabel();

    LoginExample() {

        super("Autentification");
        label.setText("Username:");
        label2.setText("Password:");

        setSize(300, 200);
        setLocation(500, 280);
        panel.setLayout(null);

        label.setBounds(10, 30, 100, 20);
        label2.setBounds(10, 65, 100, 20);
        textField.setBounds(80, 30, 150, 20);
        jPasswordField.setBounds(80, 65, 150, 20);
        button.setBounds(110, 100, 80, 20);

        panel.add(label);
        panel.add(label2);
        panel.add(button);
        panel.add(textField);
        panel.add(jPasswordField);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = textField.getText();
                String pwd = jPasswordField.getText();
                if (name.equals("ww") && pwd.equals("1")) {
                    System.setProperty("com.rockwell.test.username", "ww");
                    System.setProperty("com.rockwell.test.password", "admin");
                    System.setProperty("HOST_ADDRESS", "192.168.59.20");
                    String equipmentName ="S1-03-136";
                    IAutomationService aiService = ServiceFactory.getService(IAutomationService.class);
                    IS88EquipmentService is88EquipmentService = ServiceFactory.getService(IS88EquipmentService.class);
                    IMESS88Equipment imess88Equipment = is88EquipmentService.loadEquipmentByIdentifier(equipmentName);
                    List<IMESEquipmentProperty<?>> imesEquipmentPropertyList = ReadTag.getAllAutomationProperties(imess88Equipment);
                    List<String> list = new ArrayList<String>();
                    for (int i = 0; i < imesEquipmentPropertyList.size(); i++) {
                        System.out.println(imesEquipmentPropertyList.get(i).getIdentifier());
                        list.add(imesEquipmentPropertyList.get(i).getIdentifier());
                    }

//        list.add(imesEquipmentPropertyList.get(1).getIdentifier());

                    new OPCTagMonitor(equipmentName,imesEquipmentPropertyList.get(0).getIdentifier());
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Wrong Password / Username");
                    textField.setText("");
                    jPasswordField.setText("");
                    textField.requestFocus();
                }

            }
        });
    }
}
