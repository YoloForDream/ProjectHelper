package com.rockwell.scl.orderitem;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.ProcessOrderItem;
import com.datasweep.compatibility.client.ProcessOrderItemFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.services.s88.ifc.execution.IMESRtPhase;
import com.rockwell.mes.services.s88.ifc.recipe.IMESPhase;
import com.rockwell.mes.services.s88.impl.recipe.MESControlRecipe;

import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * @author RWang18
 */
public class OrderFilter  extends Thread {
    private String processOrderItem;
    private String path;
    private String phaseName;
    private String host;
    private String userName;
    private String passWord;

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public String getHost() {
        return host;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPath() {
        return path;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProcessOrderItem() {
        return processOrderItem;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setProcessOrderItem(String processOrderItem) {
        this.processOrderItem = processOrderItem;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public void run(){
        try {
            queryOrderItm();
        } catch (DatasweepException e) {
            e.printStackTrace();
        }
    }

    public OrderFilter(String host,String userName,String passWord,String phaseName){
        this.host =host;
        this.userName =userName;
        this.passWord= passWord;
        this.phaseName=phaseName;
        System.setProperty("HOST_ADDRESS", host);
        System.setProperty("com.rockwell.test.username", userName);
        System.setProperty("com.rockwell.test.password", passWord);
    }

    public static String getRecipePath(IMESPhase phase) {
        String operationPath = phase.getParent().getName();
        String upPath = phase.getParent().getParent().getName();
        String procedurePath = phase.getParent().getParent().getParent().getName();
        return procedurePath + "/" + upPath + "/" + operationPath + "/" + phase.getName();
    }
    public void  queryOrderItm() throws DatasweepException {
        ProcessOrderItemFilter processOrderItemFilter = PCContext.getFunctions().createProcessOrderItemFilter().forCurrentStateStartingWith("F");
        Vector processOrderItemList = PCContext.getFunctions().getFilteredProcessOrderItems(processOrderItemFilter);
        labelEnd:
        for (int i = 0; i < processOrderItemList.size(); i++) {

                ProcessOrderItem poi = PCContext.getFunctions().getProcessOrderByName(processOrderItemList.get(i).toString())
                        .getProcessOrderItem(processOrderItemList.get(i).toString());
                MESControlRecipe mesControlRecipe = new MESControlRecipe(poi.getControlRecipe());
                List<IMESRtPhase> allRtPhases = mesControlRecipe.getAllRtPhases();
                for (IMESRtPhase allRtPhase : allRtPhases) {
                    if (allRtPhase.getPhaseLib().getPhaseLibName().equals(phaseName)) {
                        processOrderItem = processOrderItemList.get(i).toString();
                        path = getRecipePath(allRtPhase.getPhase());
                        break labelEnd;
                    }
                }

        }
    }
}
