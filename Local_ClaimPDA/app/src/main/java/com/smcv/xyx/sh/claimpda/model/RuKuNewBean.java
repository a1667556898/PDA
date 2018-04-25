package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 16/04/2018.
 */

public class RuKuNewBean implements Serializable {
    private String partsId;
    private String reasonType;
    private String reasonDesc;

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }
}
