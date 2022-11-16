package com.rockwell.scl.auto.heartbeat;

import java.util.List;

/**
 * @author RWang18
 */
public interface HeartMonitorModel {
    public List<Double>[] getSineData();
    public List<Double> getWalkData();

}
