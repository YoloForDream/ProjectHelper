package com.rockwell.scl.psacadminkit;


import java.util.List;
import java.util.Vector;

import com.datasweep.compatibility.client.AccessPrivilege;
import com.datasweep.compatibility.client.AccessPrivilegeFilter;
import com.datasweep.compatibility.client.Response;
import com.datasweep.compatibility.client.UserGroup;
import com.datasweep.compatibility.client.UserGroupFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.datasweep.compatibility.ui.Time;
import java.text.SimpleDateFormat;
public class AccessPrivilegesHelper {

    List<UserGroup> userGroups;

    List<String> userGroupNames;

    List<AccessPrivilege> listOfAccessPrivileges;

    String userGroupName;

    String[] listOfAccessPrivielegeKeys;

    String accessPrivilegeKey;

    String result;

    Response response;

    Time operatorTime;



    public String getResult() {

        return result;
    }

    public Response getResponse() {

        return response;
    }

    public String getTime() {
        operatorTime = PCContext.getFunctions().getDBTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");// 设置日期+时间格式
        return timeFormat.format(operatorTime.getCalendar().getTime());

    }

   
    public AccessPrivilegesHelper() {

        AccessPrivilegeFilter accessPrivilegeFilter = PCContext.getFunctions().createAccessPrivilegeFilter().orderByAccessPrivilegeName(true);
        listOfAccessPrivileges = PCContext.getFunctions().getFilteredAccessPrivileges(accessPrivilegeFilter);

    }
    public AccessPrivilegesHelper(String userGroupName, String accessPrivilegeKey) {
        UserGroupFilter userGroupfilter = PCContext.getFunctions().createUserGroupFilter().orderByUserGroupName(true);
        userGroups = PCContext.getFunctions().getFilteredUserGroups(userGroupfilter);
        AccessPrivilegeFilter accessPrivilegeFilter = PCContext.getFunctions().createAccessPrivilegeFilter().orderByAccessPrivilegeName(true);
        listOfAccessPrivileges = PCContext.getFunctions().getFilteredAccessPrivileges(accessPrivilegeFilter);
        this.userGroupName = userGroupName;
        this.accessPrivilegeKey = accessPrivilegeKey;
        listOfAccessPrivielegeKeys = accessPrivilegeKey.substring(1, accessPrivilegeKey.length() - 1).split(",");
    }

    public void addPerGroup() {
        for (int i = 0; i < listOfAccessPrivielegeKeys.length; i++) {
            for (int j = 0; j < listOfAccessPrivileges.size(); j++) {
                if (listOfAccessPrivileges.get(j).getAccessPrivilegeKey() == Long.valueOf(listOfAccessPrivielegeKeys[i].trim())) {
                   
                    for (int k = 0; k < userGroups.size(); k++) {
                        if (userGroups.get(k).getName().equals(userGroupName)) {
                            listOfAccessPrivileges.get(j).addPerformerGroup(userGroups.get(k));
                            response = listOfAccessPrivileges.get(j).save(null, null);
                            response.getResult();

                        }
                    }
                }

            }
        }
        result = "用户组:" + userGroupName + " 分配权限成功 " + getTime();
    }

    public void removePerGroup() {
        for (int i = 0; i < listOfAccessPrivielegeKeys.length; i++) {
            for (int j = 0; j < listOfAccessPrivileges.size(); j++) {
                if (listOfAccessPrivileges.get(j).getAccessPrivilegeKey() == Long.valueOf(listOfAccessPrivielegeKeys[i].trim())) {
                    for (int k = 0; k < userGroups.size(); k++) {
                        if (userGroups.get(k).getName().equals(userGroupName)) {
                            listOfAccessPrivileges.get(j).removePerformerGroup(userGroups.get(k));
                            response = listOfAccessPrivileges.get(j).save(null, null);

                            response.getResult();

                        }
                    }
                }

            }
        }
        result = "用户组:" + userGroupName + " 删除权限成功 " + getTime();
    }
    public void addVerfGroup() {
        for (int i = 0; i < listOfAccessPrivielegeKeys.length; i++) {
            for (int j = 0; j < listOfAccessPrivileges.size(); j++) {
                if (listOfAccessPrivileges.get(j).getAccessPrivilegeKey() == Long.valueOf(listOfAccessPrivielegeKeys[i].trim())) {
                    for (int k = 0; k < userGroups.size(); k++) {
                        if (userGroups.get(k).getName().equals(userGroupName)) {
                            listOfAccessPrivileges.get(j).addVerifierGroup(userGroups.get(k));
                            response = listOfAccessPrivileges.get(j).save(null, null);
                            response.getResult();
                        }
                    }
                }
            }
        }
        result = "用户组:" + userGroupName + " 分配权限成功 " + getTime();
    }

    public void removeVerfGroup() {
        for (int i = 0; i < listOfAccessPrivielegeKeys.length; i++) {
            for (int j = 0; j < listOfAccessPrivileges.size(); j++) {
                if (listOfAccessPrivileges.get(j).getAccessPrivilegeKey() == Long.valueOf(listOfAccessPrivielegeKeys[i].trim())) {
                    for (int k = 0; k < userGroups.size(); k++) {
                        if (userGroups.get(k).getName().equals(userGroupName)) {
                            listOfAccessPrivileges.get(j).removeVerifierGroup(userGroups.get(k));
                          
                            response = listOfAccessPrivileges.get(j).save(null, null);
                            response.getResult();

                        }
                    }
                }

            }
        }
        result = "用户组:" + userGroupName + " 删除权限成功 " + getTime();
    }
    
    public void calibrationPrivilege() {
        if (listOfAccessPrivileges.size() > 0) {
            for (int j = 0; j < listOfAccessPrivileges.size(); j++) {
                String prex_1 = listOfAccessPrivileges.get(j).getName().substring(0, 3);

                if (prex_1.equals("SV_CPD")) {
                    Vector<UserGroup> vectorPerUg = listOfAccessPrivileges.get(j).getPerformerGroups();
                    System.out.println(vectorPerUg);
                    Vector<UserGroup> vectorVerUg = listOfAccessPrivileges.get(j).getVerifierGroups();
                    System.out.println(vectorVerUg);
                    String prex_2 = listOfAccessPrivileges.get(j).getName().substring(0, 6);
                    System.out.println(prex_2);
                    if (vectorPerUg.size()>0) {
                        for (UserGroup userGroup : vectorPerUg) {
                            String prex_3 = userGroup.getName().substring(0, 6);
                            System.out.println(prex_3);
                            if (!prex_2.equals(prex_3)) {

                                listOfAccessPrivileges.get(j).removePerformerGroup(userGroup);
                                response = listOfAccessPrivileges.get(j).save(null, null);
                            }
                        }
                    }
                    if (vectorVerUg.size() > 0) {
                        for (UserGroup userGroup : vectorVerUg) {
                            String prex_3 = userGroup.getName().substring(0, 6);
                            if (!prex_2.equals(prex_3)) {
                                listOfAccessPrivileges.get(j).removeVerifierGroup(userGroup);
                                response = listOfAccessPrivileges.get(j).save(null, null);
                            }
                        }
                    }
                }
            }
        }

     }

}
