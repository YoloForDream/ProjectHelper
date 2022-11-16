package com.rockwell.scl.auto;

import com.datasweep.compatibility.client.MeasuredValue;
import com.datasweep.compatibility.client.UnitOfMeasure;
import com.datasweep.compatibility.manager.UnitOfMeasureManager;
import com.datasweep.plantops.common.measuredvalue.IUnitOfMeasure;
import com.rockwell.mes.commons.base.ifc.functional.MeasuredValueUtilities;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScaleTest {

    public static MeasuredValue covertStringToMeasuredValue(String str) throws Exception {
        UnitOfMeasureManager uomFilter = PCContext.getServerImpl().getUnitOfMeasureManager();
        IUnitOfMeasure uom;
        str = str.replaceAll("\\s+","").replace("\u00a0","");
        String value = str.replaceAll("[^0-9.]","");
        BigDecimal valueBigDecimal = new BigDecimal(value.trim());
        String uomName =  str.replaceAll("[0-9\\s]","").replace(".","");
        uom = uomFilter.getUnitOfMeasureBySymbol(uomName.trim());
        UnitOfMeasure localUnitOfMeasure = (UnitOfMeasure) uom;
        MeasuredValue measuredValue;
        measuredValue = MeasuredValueUtilities.createMV(valueBigDecimal, localUnitOfMeasure);
        return measuredValue;
    }
    public static void main(String[] args) throws Exception {

        String s ="P[3]";
        String regex = "\\[(.*?)]";
        Pattern pattern = Pattern.compile(regex);
        Integer precision= null;
        if (StringUtils.isNotEmpty(s)){
            Matcher match_1 = pattern.matcher(s);
            while (match_1.find()) {
                System.out.println("1:"+match_1.group(1));
                String value = (match_1.group(1).replaceAll("[^0-9.]",""));
                precision = Integer.valueOf(value);
                System.out.println("2:"+precision);
            }
        }
    }
}
