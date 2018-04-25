package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 23/04/2018.
 */

public class PermissionBean implements Serializable {

    /**
     * userIdentityId : 10000000000024
     * userId : 10000000000010
     * userType : 10071001
     * orgCode : CV
     * orgName : 中国销售
     * supOrg : 10000001
     * orgType : null
     * isValid : 10091001
     * positionCode : 10081001
     * createDate : 1519893485000
     * createBy : 10000000000010
     * updateDate : null
     * updateBy : null
     * orgId : 10000002
     */

    private String userIdentityId;
    private String userId;
    private int userType;
    private String orgCode;
    private String orgName;
    private int supOrg;
    private String orgType;
    private int isValid;
    private String positionCode;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;
    private int orgId;

    public String getUserIdentityId() {
        return userIdentityId;
    }

    public void setUserIdentityId(String userIdentityId) {
        this.userIdentityId = userIdentityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getSupOrg() {
        return supOrg;
    }

    public void setSupOrg(int supOrg) {
        this.supOrg = supOrg;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
