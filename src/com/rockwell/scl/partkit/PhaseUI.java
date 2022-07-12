package com.rockwell.scl.partkit;

import javax.swing.*;

public class PhaseUI extends JFrame{
    private JPanel mainPanel;
    private JTextField txtHost;
    private JTextField QueueName;
    private JLabel labelHost;
    private JLabel labelQueueName;
    private JButton btnInsert;
    public PhaseUI(){
        add(mainPanel);
        setVisible(true);
    }
}
