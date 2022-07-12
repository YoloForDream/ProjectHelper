package com.rockwell.scl.auto;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CountdownPane {

    private JButton getOkButton( Container cont ) {
        Component[] components = cont.getComponents();
        for( int k = 0; k < components.length; k++ ) {
            Component comp = components[k];
            System.out.println( comp.getClass() );
            if( comp instanceof JButton ) {
                JButton button = (JButton)comp;
                System.out.println(button.getText());
                if( button.getText() == "OK" )
                    return button;
            } else if( comp instanceof Container ) {
                JButton button = getOkButton( (Container)comp );
                if( button != null ) return button;
            }
        }

        return null;
    }

    public CountdownPane() {
        Object[] options = { "OK", "CANCEL" };
        JOptionPane pane = new JOptionPane("Please wait a moment",
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION,
                null, options
        );
        JButton okButton = getOkButton( pane );
        okButton.setEnabled(false);

        JDialog dialog = pane.createDialog("CountdownPane");

        Timer t = new Timer(0, null);
        t.setDelay(1000);
        t.setRepeats(true);
        CountdownListener countdownListener = new CountdownListener( okButton, t );
        t.addActionListener( countdownListener );
        t.start();

        dialog.setVisible(true);

        System.exit(0);
    }

    private class CountdownListener implements ActionListener {
        private JButton _button;
        private Timer _timer;
        private int _ticks;
        public CountdownListener( JButton button, Timer timer ) {
            _button = button;
            _timer = timer;
            _ticks = 5;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if( _ticks > 0 ) {
                _button.setText( "OK ("+_ticks+")" );
                _button.setEnabled(false);
            } else {
                _button.setText( "OK" );
                _button.setEnabled(true);
                _timer.stop();
            }
            --_ticks;
        }
    }

    public static void main(String[] args) {
        new CountdownPane();
    }
}
