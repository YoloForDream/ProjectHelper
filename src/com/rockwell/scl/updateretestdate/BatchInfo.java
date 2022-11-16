package com.rockwell.scl.updateretestdate;

import com.datasweep.compatibility.client.Batch;
import com.datasweep.compatibility.client.BatchFilter;
import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.ui.Time;
import com.rockwell.mes.commons.base.ifc.nameduda.MESNamedUDABatch;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 * @author RWang18
 */
public class BatchInfo {
    BatchEntitly batchEntitly;

    public boolean updateBatchProperty(BatchEntitly batchEntitly) throws ParseException {

        BatchFilter batchFilter = PCContext.getFunctions().createBatchFilter();
        Vector batchList = PCContext.getFunctions().getFilteredBatches(batchFilter.forNameEqualTo(batchEntitly.BatchNumber));
        if(batchList.size() > 0) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal  = Calendar.getInstance();
            cal.setTime(df.parse(batchEntitly.RetestDate));
            Time retestDate = new Time(cal);
            Batch batch = (Batch) batchList.get(0);
            MESNamedUDABatch.setRetestDate(batch, retestDate);
            try {
                batch.Save(new Time(), "设置复验期", null);
                batch.refresh();
            } catch (DatasweepException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
           return false;
        }
    }
}
