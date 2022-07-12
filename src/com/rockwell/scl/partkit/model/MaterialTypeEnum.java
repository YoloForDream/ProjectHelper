package com.rockwell.scl.partkit.model;


public enum MaterialTypeEnum {

    SEMIFINISHEDGOODS("40", "Z100"),
    FINISHEDGOODS("50", "Z200"),
    RAWMATERIAL("10", "ZA00"),
    RAWMATERIAL_1("10", "ZD00"),
    RAWMATERIAL_2("10", "ZF00"),
    PACKAGINGMATERIAL("60", "ZB00"),
    CONSUMABLES("10","ZC00"),
    DISINFECTANT("10","ZJ00"),
    EXPERIMENTALPRODUCTS("50","ZK00"),
    INTERMEDIATEGOODS("90","Z600");

    private String mesCode;
    private String sapCode;


    MaterialTypeEnum(String mesCode, String sapCode) {
        this.mesCode = mesCode;
        this.sapCode = sapCode;
    }

    public static String getMesCodeBySapCode(String sapCode) {
        for (MaterialTypeEnum eMaterialTypeEnum : MaterialTypeEnum.values()) {
            if (eMaterialTypeEnum.sapCode.equals(sapCode)) {
                return eMaterialTypeEnum.mesCode;
            }
        }
        return null;
    }
}
