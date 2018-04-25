package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 17/03/2018.
 */

public class ChengYunBean implements Serializable {

    /**
     * createDate : 2018-04-10 14:37:18
     * updateDate : 2018-03-17 14:37:18
     * createBy : 6
     * updateBy : 7
     * encasementId : 6
     * encasementCode : Z201802120016x
     * encasementPartsNumber : 6
     * encasementState : 0
     * encasementBackAddress : 上海杨浦区尚凯大厦
     * encasementFacilitatorCode : 1334Qx321316
     * encasementFacilitatorName : 上海江浦路6
     * encasementSignatureId : null
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
    private String partsBatch;

    public String getPartsBatch() {
        return partsBatch;
    }

    public void setPartsBatch(String partsBatch) {
        this.partsBatch = partsBatch;
    }

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
