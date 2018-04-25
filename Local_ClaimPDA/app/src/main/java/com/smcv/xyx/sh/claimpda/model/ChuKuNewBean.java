package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by SummerChen on 2018/3/27.
 */

public class ChuKuNewBean implements Serializable{

    private String partsReturnPartCode;//配件代码
    private String partsReturnPartName;//配件名称
    private String partsOutNumber;//实际出库数量
    private String partsRemark;//零件备注
    private String partsId; //零件id

    public String getPartsReturnPartCode() {
        return partsReturnPartCode;
    }

    public void setPartsReturnPartCode(String partsReturnPartCode) {
        this.partsReturnPartCode = partsReturnPartCode;
    }

    public String getPartsReturnPartName() {
        return partsReturnPartName;
    }

    public void setPartsReturnPartName(String partsReturnPartName) {
        this.partsReturnPartName = partsReturnPartName;
    }

    public String getPartsOutNumber() {
        return partsOutNumber;
    }

    public void setPartsOutNumber(String partsOutNumber) {
        this.partsOutNumber = partsOutNumber;
    }

    public String getPartsRemark() {
        return partsRemark;
    }

    public void setPartsRemark(String partsRemark) {
        this.partsRemark = partsRemark;
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    @Override
    public String toString() {
        return "ChuKuNewBean{" +
                "partsReturnPartCode='" + partsReturnPartCode + '\'' +
                ", partsReturnPartName='" + partsReturnPartName + '\'' +
                ", partsOutNumber='" + partsOutNumber + '\'' +
                ", partsRemark='" + partsRemark + '\'' +
                ", partsId='" + partsId + '\'' +
                '}';
    }
}
