package com.rockwell.scl.updateretestdate;

/**
 * @author RWang18
 */
public class BatchEntitly {
    String BatchNumber;
    String RetestDate;

    public void setRetestDate(String retestDate) {
        RetestDate = retestDate;
    }

    public String getRetestDate() {
        return RetestDate;
    }

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        BatchNumber = batchNumber;
    }
}
