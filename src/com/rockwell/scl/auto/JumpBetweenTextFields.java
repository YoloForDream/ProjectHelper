package com.rockwell.scl.auto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JumpBetweenTextFields {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Jump between text fields");

        JPanel p = new JPanel(new FlowLayout());

        final JTextField firstField = new JTextField(5);
        final JTextField secondField = new JTextField(5);
        installAction(firstField, secondField);
        installAction(secondField, firstField);
        p.add(firstField);
        p.add(secondField);
        secondField.setEditable(false);

        frame.getContentPane().add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }




    private static void installAction(final JTextField t1, final JTextField t2) {
        t1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        t1.setText("");
                        t1.setEditable(false);
                        t2.setEditable(true);
                        t2.requestFocus();
                    }
                });
            }
        });

    }
}