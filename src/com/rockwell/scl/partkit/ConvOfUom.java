package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.*;
import com.rockwell.mes.commons.base.ifc.functional.MeasuredValueUtilities;
import java.math.BigDecimal;


/**
 * @author RWang18
 */
public class ConvOfUom {

    Sublot sublot;
    String quantity;
    String sourceUoM;
    Boolean factorTag;
    private static final String X_CONV_FACTOR = "X_ConvFactor";
    public ConvOfUom(Sublot sublot,String quantity,String sourceUoM) {
        this.sublot = sublot;
        this.quantity = quantity;
        this.sourceUoM = sourceUoM;
    }
    public boolean checkFactor(Sublot sublot) throws DatasweepException {
        Part part = sublot.getPart();
        if(part.getUDA(X_CONV_FACTOR)!= null){
            factorTag = true;
        }
        else
        {
            factorTag = false;
        }
        return factorTag;
    }
    public MeasuredValue getQuantity() throws Exception {
        //根据SubLot获取对应的物料信息
        Part part = sublot.getPart();
        //用于处理比列系数带有逗号的格式，比如1,000;
        BigDecimal factor = new BigDecimal( part.getUDA(X_CONV_FACTOR).toString().replace( ",", ""));
        BigDecimal sourceQuantity = new BigDecimal(quantity);
        //数量转换，从当前的单位转为源单位，保留三位小数，根据杨春喜的反馈，
        BigDecimal conQuantity = sourceQuantity.divide(factor,3,BigDecimal.ROUND_HALF_UP);
        //返回转换的值，类型为MeasuredValue;
        MeasuredValue result = MeasuredValueUtilities.createMV(conQuantity,sourceUoM);
        return result;
    }

}
