package com.rockwell.scl.auto;

import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * @author RWang18
 */
public class ReadOPCHelper {
    String equipmentId;
    String equipmentTag;
    public ReadOPCHelper(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    public ReadOPCHelper(String equipmentId, String equipmentTag ) {
        this.equipmentId = equipmentId;
        this.equipmentTag = equipmentTag;
    }
    public BigDecimal getValue() throws AutomationException {
        if(!equipmentTag.equals("") ){
            ReadTag readTag = new ReadTag(equipmentId);
            Map<String, Object> res = readTag.getTagValue();
            if(res.containsKey(equipmentTag)){
                if(res.get(equipmentTag)!=null&&!res.get(equipmentTag).equals("")){
                    BigDecimal value = new BigDecimal(res.get(equipmentTag).toString());
                    return value;
                }
            }
            return BigDecimal.ZERO;
        }
        else
        {
            return BigDecimal.ZERO;
        }
    }
    public void readTag() throws AutomationException {

        ReadTag readTag = new ReadTag(equipmentId);
        Map<String, Object> res = readTag.getTagValue();
        Set<Map.Entry<String, Object>> entrySet = res.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                readTag();
            } catch (AutomationException e) {
                e.printStackTrace();
            }
        }
    };
}
