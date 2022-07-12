package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.DatasweepException;
import com.rockwell.scl.partkit.model.PartEntity;

/**
 * @author RWang18
 */
public class DelPartTest {

    public static void main(String[] args) throws DatasweepException {
        System.setProperty("com.rockwell.test.username", "ken");
        System.setProperty("com.rockwell.test.password", "1");
        System.setProperty("HOST_ADDRESS", "192.168.108.209");
        PartEntity partEntity = new PartEntity("1000072");
        partEntity.deletePart();
    }
}
