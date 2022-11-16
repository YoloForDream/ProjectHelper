package com.rockwell.scl.psacadminkit;

import com.datasweep.compatibility.client.UserGroup;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.util.Vector;

public class test {
    public static void main(String[] args) throws Exception {

        System.setProperty("com.rockwell.test.username", "ww");
        System.setProperty("com.rockwell.test.password", "admin");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
        UserGroup  userGroups_1 = PCContext.getFunctions().getUserGroupByName("RA_Workflow_Edit");
        Vector vector_1 =userGroups_1.getPerformerAccessPrivileges();
        for (int i = 0; i < vector_1.size(); i++) {
            System.out.println(vector_1.get(i));
        }
        System.out.println("--------------------------------");
        UserGroup  userGroups_2 = PCContext.getFunctions().getUserGroupByName("RA_Workflow_EditMode");
        Vector vector_2 =userGroups_2.getPerformerAccessPrivileges();
        for (int i = 0; i < vector_2.size(); i++) {
            System.out.println(vector_2.get(i));
        }
        System.out.println("--------------------------------");
        UserGroup  userGroups_3 = PCContext.getFunctions().getUserGroupByName("RA_Workflow_Valid");
        Vector vector_3 =userGroups_3.getPerformerAccessPrivileges();
        for (int i = 0; i < vector_3.size(); i++) {
            System.out.println(vector_3.get(i));
        }
        System.out.println("--------------------------------");
        UserGroup  userGroups_4 = PCContext.getFunctions().getUserGroupByName("RA_Workflow_Verification");
        Vector vector_4 =userGroups_4.getPerformerAccessPrivileges();
        for (int i = 0; i < vector_4.size(); i++) {
            System.out.println(vector_4.get(i));
        }
//        System.out.println("--------------------------------");
//        UserGroup  userGroups_5 = PCContext.getFunctions().getUserGroupByName("RA_Workflow_Edit");
//        Vector vector_5 =userGroups_5.getPerformerAccessPrivileges();
//        for (int i = 0; i < vector_5.size(); i++) {
//            System.out.println(vector_5.get(i));
//        }



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
//        UserGroupKit userGroupKit = new UserGroupKit("[SVA00516,SVA07235]","[RA_SV_COMR_CP_FPDFOR_ShowRecipe]");
//        userGroupKit.removeSelectedUserFromInputUserGroup();
//        System.out.println("inside");

//          String str1 = "[65866\t,\n" +
//                  "65868\t,\n" +
//                  "65870\t,\n" +
//                  "65872\t,]";
//          str1= str1.replaceAll("\\s+","");
//          System.out.println(str1);
//          String[] listOfAccessPrivielegeKeys  = str1.substring(1, str1.length() - 1).split(",");
//          System.out.println(Arrays.toString(listOfAccessPrivielegeKeys));
//          System.out.println(listOfAccessPrivielegeKeys[0].trim());

    }

}
