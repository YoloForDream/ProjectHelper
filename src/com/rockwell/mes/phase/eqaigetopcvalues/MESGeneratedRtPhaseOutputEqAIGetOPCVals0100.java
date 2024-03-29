package com.rockwell.mes.phase.eqaigetopcvalues;

/**
 * This file is generated by the PhaseGenerator
 *
 * Please do not modify this file manually !!
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.datasweep.compatibility.client.ATRow;
import com.datasweep.compatibility.client.Response;
import com.rockwell.mes.commons.base.ifc.utility.ObjectFactory;
import com.rockwell.mes.services.s88.ifc.library.IBuildingBlockOutputDescriptor;
import com.rockwell.mes.services.s88.ifc.processdata.MESRtPhaseOutput;


 /**
 * Generated class definition
 * <br/>Application table: RS_PhOutEqAIGetOPCVals0100
 */
public abstract class MESGeneratedRtPhaseOutputEqAIGetOPCVals0100
 extends MESRtPhaseOutput {

    /** Generated attribute definition */
    protected static final String ATDEFINITION_NAME = "RS_PhOutEqAIGetOPCVals0100";


    @Override
    public String getATDefinitionName() {
        return ATDEFINITION_NAME;
    }

    /**
     * Generated constructor
     *
     * @param key The key of the ATRow to load.
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100(long key) {
        super(key);
    }

    /**
     * Generated copy constructor
     *
     * @param source the source to copy.
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100(MESGeneratedRtPhaseOutputEqAIGetOPCVals0100 source) {
        super(source);
    }

    /**
     * Generated constructor
     *
     * @param baseATRow The ATRow to wrap.
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100(ATRow baseATRow) {
        super(baseATRow);
    }

    /**
     * Generated constructor
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100() {
        super();
    }

    @Override
    protected void synchronizeAfterATRowRefresh() {
        super.synchronizeAfterATRowRefresh();
    }    
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public byte[] getOutputValuesSerializ() {
        return (byte[]) this.dgtATRow.getValue("RS_outputValuesSerializ");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setOutputValuesSerializ(byte[] value) {
        byte[] oldValue = this.getOutputValuesSerializ();
        this.dgtATRow.setValue("RS_outputValuesSerializ", value);
        pcs.firePropertyChange("outputValuesSerializ", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Boolean getAllGetSuccessful() {
        return (Boolean) this.dgtATRow.getValue("RS_allGetSuccessful");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setAllGetSuccessful(Boolean value) {
        Boolean oldValue = this.getAllGetSuccessful();
        this.dgtATRow.setValue("RS_allGetSuccessful", value);
        pcs.firePropertyChange("allGetSuccessful", oldValue, value);
    }

    @Override
    protected Response prepareATRowForSave() {
        // Check if transient references are valid and store the corresponding keys in the ATRow:
        Response res = super.prepareATRowForSave();

        return res;
    }

    /** output descriptors */
    private static final List<IBuildingBlockOutputDescriptor> OUTPUT_DESCRIPTORS =
            new ArrayList<IBuildingBlockOutputDescriptor>();

    /**
     * Initializes the output descriptors.
     */
    static {
        IBuildingBlockOutputDescriptor descriptor;

        descriptor = ObjectFactory.getInstance().createObject(IBuildingBlockOutputDescriptor.class,
                           new Class[] { String.class, String.class, Class.class },
                           new Object[] { "outputValuesSerializ", "Output values", byte[].class }
                          );
        OUTPUT_DESCRIPTORS.add(descriptor);

        descriptor = ObjectFactory.getInstance().createObject(IBuildingBlockOutputDescriptor.class,
                           new Class[] { String.class, String.class, Class.class },
                           new Object[] { "allGetSuccessful", "Automation get successful", Boolean.class }
                          );
        OUTPUT_DESCRIPTORS.add(descriptor);
    }

    /**
     * Retrieves the output descriptors of this class.
     *  
     * @return unmodifiable list of output descriptors
     */
    public static List<IBuildingBlockOutputDescriptor> getOutputDescriptorsOfClass() {
        return Collections.unmodifiableList(OUTPUT_DESCRIPTORS);
    }
    
    @Override
    public List<IBuildingBlockOutputDescriptor> getOutputDescriptors() {
        return getOutputDescriptorsOfClass();
    }
    
}
