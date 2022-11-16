package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.Part;
import com.datasweep.compatibility.client.Response;
import com.datasweep.compatibility.client.Sublot;
import com.rockwell.mes.commons.base.ifc.functional.MeasuredValueUtilities;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.math.BigDecimal;

/**
 * @author RWang18
 */
public class ConsumerMaterial {
    public static void main(String[] args) throws Exception {
        System.setProperty("com.rockwell.test.username", "ww");
        System.setProperty("com.rockwell.test.password", "admin");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
        String quantity = "1000" ;
        String currentUoM="ml";
        Response response;
        Sublot sublot = PCContext.getFunctions().getSublotByKey(50796512);
        Part part = sublot.getPart();
//        System.out.println(part.getPartNumber());
//        System.out.println( part.getUDA("X_ConvSourceUoM"));
//        System.out.println( part.getUDA("X_ConvTargetUoM"));
//        System.out.println( part.getUDA("X_ConvFactor"));
        String sourceUoM = part.getUDA("X_ConvSourceUoM").toString();
        if(!currentUoM.equals(sourceUoM)){
            if(part.getUDA("X_ConvTargetUoM") != null){
                String targetUoM = part.getUDA("X_ConvTargetUoM").toString();
                if(targetUoM.equals(currentUoM)){
                    ConvOfUom convOfUom = new ConvOfUom(sublot,quantity,sourceUoM);
                     if(convOfUom.checkFactor(sublot)){
                         System.out.println(  sublot.getQuantity().getValue().toString());
                         System.out.println(  sublot.getQuantity().subtract(convOfUom.getQuantity()));
                     }
                     else{
                         System.out.println("该物料主数据缺乏比例系数");
                     }
                }
                else{
                    System.out.println("输入单位不符合该物料所支持单位");
                }
            }
            else{
                System.out.println("该物料主数据没有设置转换单位");
            }
        }
        else{
            BigDecimal convOfQuantity = new BigDecimal(quantity);
            sublot.getQuantity().subtract(MeasuredValueUtilities.createMV(convOfQuantity,currentUoM));
        }
    }
}
