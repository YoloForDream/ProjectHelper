package com.rockwell.scl.psacadminkit;

import com.datasweep.compatibility.client.AccessPrivilege;
import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.UserGroup;
import com.datasweep.compatibility.client.UserGroupFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.util.*;


public class getAccessPrivileges {
    public static void main(String[] args) throws DatasweepException {
        List<UserGroup> userGroups;
        List<UserGroup> userGroups1;
        Vector<AccessPrivilege>accessPrivilegeList;
        Vector<AccessPrivilege>accessPrivilegeList1;
        List<String>differenceList;
        System.setProperty("com.rockwell.test.username", "ww");
        System.setProperty("com.rockwell.test.password", "admin");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
        UserGroupFilter userGroupfilter = PCContext.getFunctions().createUserGroupFilter().orderByUserGroupName(true);
        userGroups = PCContext.getFunctions().getFilteredUserGroups(userGroupfilter.forNameEqualTo("RA_PEC_EditMode"));
        userGroups1 = PCContext.getFunctions().getFilteredUserGroups(userGroupfilter.forNameEqualTo("RA_PEC_CanNotChangeSublotStatus"));

        accessPrivilegeList =  userGroups.get(0).getPerformerAccessPrivileges();
        accessPrivilegeList1 = userGroups1.get(0).getPerformerAccessPrivileges();
        differenceList= new ArrayList<>();

        for(int i =0;i<accessPrivilegeList.size();i++){
            for(int j =0;j<accessPrivilegeList1.size();j++){
                if(!accessPrivilegeList.get(i).getName().equals(accessPrivilegeList1.get(j).getName())){
                    differenceList.add(accessPrivilegeList.get(i).getName());
                }
            }
        }
        differenceList = removeDuplicate(differenceList);
        for(String string: differenceList){
            System.out.println(string);
        }
    }
    public static List removeDuplicate(List list){

        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}
