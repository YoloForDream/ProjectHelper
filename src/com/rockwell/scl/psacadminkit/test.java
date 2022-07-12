package com.rockwell.scl.psacadminkit;

import com.datasweep.compatibility.client.User;
import com.datasweep.compatibility.client.UserFilter;
import com.datasweep.compatibility.client.UserGroup;
import com.datasweep.compatibility.client.UserGroupFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {

        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");


//        UserGroup  userGroups = PCContext.getFunctions().getUserGroupByName("RA_SV_COMR_CP_FPDFOR_ShowRecipe");
//
//        List<String> names = new ArrayList<String>();
//        names.add("SVA00487");
//        UserFilter userFilter = PCContext.getFunctions().createUserFilter().orderByUserName(true);
//        List<User> users = PCContext.getFunctions().getFilteredUsers(userFilter);
//        List<User> userForDeleted = new ArrayList<User>();
//        for (User user : users) {
//            for (String name : names) {
//                if(user.getName().equals(name)) {
//                        userForDeleted.add(user);
//                }
//            }
//        }
//        for (User user : userForDeleted){
//            System.out.println(user.getName());
//
//        }
//        for (User user : userForDeleted) {
//            userGroups.removeUser(user);
//            userGroups.save(PCContext.getFunctions().getDBTime(), null);
//
//        }
        UserGroupKit userGroupKit = new UserGroupKit("[SVA00516,SVA07235]","[RA_SV_COMR_CP_FPDFOR_ShowRecipe]");
        userGroupKit.removeSelectedUserFromInputUserGroup();
        System.out.println("inside");



    }

}
