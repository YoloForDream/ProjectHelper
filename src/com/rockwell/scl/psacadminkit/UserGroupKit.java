package com.rockwell.scl.psacadminkit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.datasweep.compatibility.client.Response;
import com.datasweep.compatibility.client.User;
import com.datasweep.compatibility.client.UserFilter;
import com.datasweep.compatibility.client.UserGroup;
import com.datasweep.compatibility.client.UserGroupFilter;
import com.datasweep.compatibility.ui.Time;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

public class UserGroupKit {

    List<UserGroup> userGroups;

    String[] listOfUserGroupNames;

    String[] listOfUserNames;

    List<User> users;

    List<User> selectedUsers;

    String userGroupNameList;

    String userList;

    String result;

    Time operatorTime;

    Response response;

    public UserGroupKit(String userGroupNameList) {

        UserGroupFilter userGroupfilter = PCContext.getFunctions().createUserGroupFilter().orderByUserGroupName(true);
        userGroups = PCContext.getFunctions().getFilteredUserGroups(userGroupfilter);
        UserFilter userFilter = PCContext.getFunctions().createUserFilter().orderByUserName(true);
        users = PCContext.getFunctions().getFilteredUsers(userFilter);
        this.userGroupNameList = userGroupNameList;
        listOfUserGroupNames = userGroupNameList.substring(1, userGroupNameList.length() - 1).split(",");

    }

    public UserGroupKit(String userList, String userGroupNameList) {

        UserGroupFilter userGroupfilter = PCContext.getFunctions().createUserGroupFilter().orderByUserGroupName(true);
        userGroups = PCContext.getFunctions().getFilteredUserGroups(userGroupfilter);
        UserFilter userFilter = PCContext.getFunctions().createUserFilter().orderByUserName(true);
        users = PCContext.getFunctions().getFilteredUsers(userFilter);
        this.userGroupNameList = userGroupNameList;
        this.userList = userList;
        listOfUserGroupNames = userGroupNameList.substring(1, userGroupNameList.length() - 1).split(",");
        listOfUserNames = userList.substring(1, userList.length() - 1).split(",");
        getSelectedUsers();
        for(int i = 0; i < listOfUserGroupNames.length; i++) {
            System.out.println("userGroupName" + listOfUserGroupNames[i] );
        }
        for(int i=0;i<selectedUsers.size(); i++){
            System.out.println("User " + selectedUsers.get(i).getName());
        }

    }
    public void removeSelectedUserFromInputUserGroup(){
        for(int i = 0; i < listOfUserGroupNames.length; i++) {
            UserGroup userGroup = PCContext.getFunctions().getUserGroupByName(listOfUserGroupNames[i].trim());
            for(int j = 0; j < selectedUsers.size(); j++){
                userGroup.removeUser(selectedUsers.get(j));
                userGroup.save(null,null);
            }
        }
    }
    public UserGroupKit(String userList, Boolean flag) {

        UserFilter userFilter = PCContext.getFunctions().createUserFilter().orderByUserName(true);
        users = PCContext.getFunctions().getFilteredUsers(userFilter);
        this.userList = userList;
        listOfUserNames = userList.substring(1, userList.length() - 1).split(",");
        getSelectedUsers();

    }

    public void addAllUserToGroup() {
        for (int i = 0; i < listOfUserGroupNames.length; i++) {
            for (int j = 0; j < userGroups.size(); j++) {
                if (userGroups.get(j).getName().equals(listOfUserGroupNames[i].trim())) {
                    for (int k = 0; k < users.size(); k++) {
                        users.get(k).addUserGroup(userGroups.get(j));
                        Response response = users.get(k).save(null, null);
                        this.response = response;
                        showAddResult(users.get(k).getName(), listOfUserGroupNames[i].trim());
                    }

                }
            }
        }

    }

    public void removeAllUserFromInputUserGroup() {
        for (int i = 0; i < listOfUserGroupNames.length; i++) {
            for (int j = 0; j < userGroups.size(); j++) {
                if (userGroups.get(j).getName().equals(listOfUserGroupNames[i].trim())) {
                    for (int k = 0; k < users.size(); k++) {
                        if (users.get(k).getUserGroups().contains(userGroups.get(j))) {
                            users.get(k).removeUserGroup(userGroups.get(j));
                            Response response = users.get(k).save(null, null);
                            this.response = response;
                            showDelResult(users.get(k).getName(), listOfUserGroupNames[i].trim());

                        }
                    }
                }
            }
        }
    }

    public void getSelectedUsers() {
        selectedUsers = new ArrayList<>();
        for (int i = 0; i < listOfUserNames.length; i++) {
            for (int k = 0; k < users.size(); k++) {
                if (users.get(k).getName().equals(listOfUserNames[i].trim())) {
                    selectedUsers.add(users.get(k));
                }
            }
        }
    }

    public void addSelecetedUserToGroup() {
        for (int i = 0; i < listOfUserGroupNames.length; i++) {
            for (int j = 0; j < userGroups.size(); j++) {
                if (userGroups.get(j).getName().equals(listOfUserGroupNames[i].trim())) {
                    for (int k = 0; k < selectedUsers.size(); k++) {
                        selectedUsers.get(k).addUserGroup(userGroups.get(j));
                        Response response = selectedUsers.get(k).save(null, null);
                        this.response = response;
                        showAddResult(selectedUsers.get(k).getName(), listOfUserGroupNames[i].trim());
                    }
                }
            }
        }
    }



    public void removeAllUserGroupsForSelectedUsers() {
        for (int i = 0; i < selectedUsers.size(); i++) {
            userGroups = selectedUsers.get(i).getUserGroups();
            for (int j = 0; j < userGroups.size(); j++) {
                if (!userGroups.get(j).getName().equals("MinimalAccess") && !userGroups.get(j).getName().equals("PlantOpsOperator")) {
                    selectedUsers.get(i).removeUserGroup(userGroups.get(j));
                    Response response = selectedUsers.get(i).save(null, null);
                    this.response = response;
                }
            }
        }
    }

    public void showAddResult(String userName, String userGroup) {
        operatorTime = PCContext.getFunctions().getDBTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");// 设置日期+时间格式
        result = "用户:" + userName + "加入用户组" + userGroup + "成功 " + timeFormat.format(operatorTime.getCalendar().getTime());

    }

    public String showDelResult(String userName, String userGroup) {
        operatorTime = PCContext.getFunctions().getDBTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");// 设置日期+时间格式
        String result = "用户:" + userName + "删除用户组" + userGroup + "成功 " + timeFormat.format(operatorTime.getCalendar().getTime());
        return result;
    }

    public String getResult() {

        return result;
    }

    public void setResult() {

        result = null;
    }


}
