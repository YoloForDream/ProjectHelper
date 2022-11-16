package com.rockwell.scl.auto.opcrealtime;

import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import java.util.List;

public interface OPCTagMonitorModel {
    public List<Double>[] getRealTimeData(String equipmentId,String equipmentProperty) throws AutomationException;


//    List<Double>[] getRealTimeData() throws AutomationException;

}
