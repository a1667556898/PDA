package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 23/03/2018.
 */

public class QianShouBean implements Serializable{

    /**
     * createDate : 2018-03-23 17:21:04
     * updateDate : 2018-03-23 17:23:56
     * createBy : 2345
     * updateBy : 12345
     * encasementId : 1
     * encasementCode : dssfsad
     * encasementPartsNumber : 2
     * encasementState : 1
     * encasementBackAddress : rtyughj
     * encasementFacilitatorCode : tklghj
     * encasementFacilitatorName : tyughj
     * encasementSignatureId : eedd304f41f242398445377376c0501f
     */

    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;
    private String encasementId;
    private String encasementCode;
    private String encasementPartsNumber;
    private String encasementState;
    private String encasementBackAddress;
    private String encasementFacilitatorCode;
    private String encasementFacilitatorName;
    private String encasementSignatureId;

    public String getPartsBatch() {
        return partsBatch;
    }

    public void setPartsBatch(String partsBatch) {
        this.partsBatch = partsBatch;
    }

    private String partsBatch;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getEncasementId() {
        return encasementId;
    }

    public void setEncasementId(String encasementId) {
        this.encasementId = encasementId;
    }

    public String getEncasementCode() {
        return encasementCode;
    }

    public void setEncasementCode(String encasementCode) {
        this.encasementCode = encasementCode;
    }

    public String getEncasementPartsNumber() {
        return encasementPartsNumber;
    }

    public void setEncasementPartsNumber(String encasementPartsNumber) {
        this.encasementPartsNumber = encasementPartsNumber;
    }

    public String getEncasementState() {
        return encasementState;
    }

    public void setEncasementState(String encasementState) {
        this.encasementState = encasementState;
    }

    public String getEncasementBackAddress() {
        return encasementBackAddress;
    }

    public void setEncasementBackAddress(String encasementBackAddress) {
        this.encasementBackAddress = encasementBackAddress;
    }

    public String getEncasementFacilitatorCode() {
        return encasementFacilitatorCode;
    }

    public void setEncasementFacilitatorCode(String encasementFacilitatorCode) {
        this.encasementFacilitatorCode = encasementFacilitatorCode;
    }

    public String getEncasementFacilitatorName() {
        return encasementFacilitatorName;
    }

    public void setEncasementFacilitatorName(String encasementFacilitatorName) {
        this.encasementFacilitatorName = encasementFacilitatorName;
    }

    public String getEncasementSignatureId() {
        return encasementSignatureId;
    }

    public void setEncasementSignatureId(String encasementSignatureId) {
        this.encasementSignatureId = encasementSignatureId;
    }
}
