package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.ProcessOrderItem;
import com.datasweep.compatibility.client.ProcessOrderItemFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.services.s88.ifc.execution.IMESRtPhase;
import com.rockwell.mes.services.s88.ifc.recipe.IMESPhase;
import com.rockwell.mes.services.s88.impl.recipe.MESControlRecipe;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author RWang18
 */
public class t1 {

    public static String getRecipePath(IMESPhase phase) {
        String operationPath = phase.getParent().getName();
        String upPath = phase.getParent().getParent().getName();
        String procedurePath = phase.getParent().getParent().getParent().getName();
        return procedurePath + "/" + upPath + "/" + operationPath + "/" + phase.getName();
    }

    public static void main(String[] args) throws DatasweepException {
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.5");
        ProcessOrderItemFilter processOrderItemFilter = PCContext.getFunctions().createProcessOrderItemFilter().forCurrentStateStartingWith("F");
        Vector processOrderItemList = PCContext.getFunctions().getFilteredProcessOrderItems(processOrderItemFilter);
        labelEnd:
        for (int i = 0; i < processOrderItemList.size(); i++) {
            if (Pattern.matches("[a-zA-Z]", processOrderItemList.get(i).toString())==false) {
                ProcessOrderItem poi = PCContext.getFunctions().getProcessOrderByName(processOrderItemList.get(i).toString())
                        .getProcessOrderItem(processOrderItemList.get(i).toString());
                MESControlRecipe mesControlRecipe = new MESControlRecipe(poi.getControlRecipe());
                List<IMESRtPhase> allRtPhases = mesControlRecipe.getAllRtPhases();
                for (IMESRtPhase allRtPhase : allRtPhases) {
                    if (allRtPhase.getPhaseLib().getPhaseLibName().equals("Record Pause Interval(SCL) [1.0]")) {
                        System.out.println(processOrderItemList.get(i));
                        String Path = getRecipePath(allRtPhase.getPhase());
                        System.out.println(Path);
                        break labelEnd;
                    }
                }
            }
        }
    }
}