package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangxin on 24/03/2018.
 */

public class RuKuBean implements Serializable {

    /**
     * commonList : {"code":0,"msg":"操作成功","result":[{"value":null,"label":"回运到库索赔件数量与需回运数据不一致"},{"value":null,"label":"回运到库索赔件与需回运零件不一致"},{"value":null,"label":"回运件为非本批次回运零件"},{"value":null,"label":"其他"}]}
     * parts : {"createDate":"2018-04-11 17:23:30","updateDate":"2018-04-11 17:23:30","createBy":null,"updateBy":null,"partsId":"2343861093266","partsRoNo":"018   ","partsLineNo":2,"partsIsMainpart":"1","partsAmount":12111,"partsAscCode":"0001018","partsAscName":"EGR冷却器控制部件18","partsVin":"LSFAM11A4HA118   ","partsEngineNo":"120Q4*R915C018","partsModel":null,"partsSeries":null,"partsMileage":null,"partsClaimType":"OA18","partsTroubleDesc":"着车时发动机有异响18","partsTroubleReason":"经检查为飞轮晃动量过大导致异响，飞轮晃动量大导致离合器盘烧蚀18","partsClaimSubmitDate":"2018-04-11","partsTranswrCreateDate":null,"partsAddressId":null,"partsBackWays":null,"partsReturnPartCode":"C413500100018     ","partsReturnPartName":"远程监控模块18","partsQuantity":1,"partsBatch":"201201","partsProcessState":0,"partsSecondJudgeReason":null,"partsOtherJudgeReason":null,"partsReturnResult":null,"partsQrUrl":null,"encasementCode":null,"partsEncasementId":null,"partsOutstorageId":null,"partsPutstorageId":null,"partsTwrPartId":null,"operateFlag":null,"partsRemark":null,"partsStorage":null,"partsOutNumber":null,"putStorageSeat":null,"putStoragePartsNumber":null,"partsReleaseDate":null,"partsReasonCode":null}
     * storage : [{"storageDictionarysId":"001","storageAddressName":"无锡申联仓库"},{"storageDictionarysId":"002","storageAddressName":"G10\\D90后桥仓库"},{"storageDictionarysId":"003","storageAddressName":"T60后桥仓库"},{"storageDictionarysId":"004","storageAddressName":"RV80房车仓库"},{"storageDictionarysId":"005","storageAddressName":"G10 云南发动机仓库"},{"storageDictionarysId":"006","storageAddressName":"无锡索赔件仓库"},{"storageDictionarysId":"007","storageAddressName":"上汽乘用车发动机仓库"},{"storageDictionarysId":"008","storageAddressName":"G10沈阳三菱发动机仓库"},{"storageDictionarysId":"009","storageAddressName":"V80后桥仓库"},{"storageDictionarysId":"010","storageAddressName":"上柴发动机仓库"}]
     */

    private CommonListBean commonList;
    private PartsBean parts;
    private List<StorageBean> storage;

    public CommonListBean getCommonList() {
        return commonList;
    }

    public void setCommonList(CommonListBean commonList) {
        this.commonList = commonList;
    }

    public PartsBean getParts() {
        return parts;
    }

    public void setParts(PartsBean parts) {
        this.parts = parts;
    }

    public List<StorageBean> getStorage() {
        return storage;
    }

    public void setStorage(List<StorageBean> storage) {
        this.storage = storage;
    }

    public static class CommonListBean implements Serializable {
        /**
         * code : 0
         * msg : 操作成功
         * result : [{"value":null,"label":"回运到库索赔件数量与需回运数据不一致"},{"value":null,"label":"回运到库索赔件与需回运零件不一致"},{"value":null,"label":"回运件为非本批次回运零件"},{"value":null,"label":"其他"}]
         */

        private String code;
        private String msg;
        private List<ResultBean> result;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean implements Serializable {
            /**
             * value : null
             * label : 回运到库索赔件数量与需回运数据不一致
             */

            private String value;
            private String label;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
    }

    public static class PartsBean implements Serializable {
        /**
         * createDate : 2018-04-11 17:23:30
         * updateDate : 2018-04-11 17:23:30
         * createBy : null
         * updateBy : null
         * partsId : 2343861093266
         * partsRoNo : 018
         * partsLineNo : 2
         * partsIsMainpart : 1
         * partsAmount : 12111
         * partsAscCode : 0001018
         * partsAscName : EGR冷却器控制部件18
         * partsVin : LSFAM11A4HA118
         * partsEngineNo : 120Q4*R915C018
         * partsModel : null
         * partsSeries : null
         * partsMileage : null
         * partsClaimType : OA18
         * partsTroubleDesc : 着车时发动机有异响18
         * partsTroubleReason : 经检查为飞轮晃动量过大导致异响，飞轮晃动量大导致离合器盘烧蚀18
         * partsClaimSubmitDate : 2018-04-11
         * partsTranswrCreateDate : null
         * partsAddressId : null
         * partsBackWays : null
         * partsReturnPartCode : C413500100018
         * partsReturnPartName : 远程监控模块18
         * partsQuantity : 1
         * partsBatch : 201201
         * partsProcessState : 0
         * partsSecondJudgeReason : null
         * partsOtherJudgeReason : null
         * partsReturnResult : null
         * partsQrUrl : null
         * encasementCode : null
         * partsEncasementId : null
         * partsOutstorageId : null
         * partsPutstorageId : null
         * partsTwrPartId : null
         * operateFlag : null
         * partsRemark : null
         * partsStorage : null
         * partsOutNumber : null
         * putStorageSeat : null
         * putStoragePartsNumber : null
         * partsReleaseDate : null
         * partsReasonCode : null
         */

        private String createDate;
        private String updateDate;
        private String createBy;
        private String updateBy;
        private String partsId;
        private String partsRoNo;
        private String partsLineNo;
        private String partsIsMainpart;
        private String partsAmount;
        private String partsAscCode;
        private String partsAscName;
        private String partsVin;
        private String partsEngineNo;
        private String partsModel;
        private String partsSeries;
        private String partsMileage;
        private String partsClaimType;
        private String partsTroubleDesc;
        private String partsTroubleReason;
        private String partsClaimSubmitDate;
        private String partsTranswrCreateDate;
        private String partsAddressId;
        private String partsBackWays;
        private String partsReturnPartCode;
        private String partsReturnPartName;
        private String partsQuantity;
        private String partsBatch;
        private String partsProcessState;
        private String partsSecondJudgeReason;
        private String partsOtherJudgeReason;
        private String partsReturnResult;
        private String partsQrUrl;
        private String encasementCode;
        private String partsEncasementId;
        private String partsOutstorageId;
        private String partsPutstorageId;
        private String partsTwrPartId;
        private String operateFlag;
        private String partsRemark;
        private String partsStorage;
        private String partsOutNumber;
        private String putStorageSeat;
        private String putStoragePartsNumber;
        private String partsReleaseDate;
        private String partsReasonCode;

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

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getPartsId() {
            return partsId;
        }

        public void setPartsId(String partsId) {
            this.partsId = partsId;
        }

        public String getPartsRoNo() {
            return partsRoNo;
        }

        public void setPartsRoNo(String partsRoNo) {
            this.partsRoNo = partsRoNo;
        }

        public String getPartsLineNo() {
            return partsLineNo;
        }

        public void setPartsLineNo(String partsLineNo) {
            this.partsLineNo = partsLineNo;
        }

        public String getPartsIsMainpart() {
            return partsIsMainpart;
        }

        public void setPartsIsMainpart(String partsIsMainpart) {
            this.partsIsMainpart = partsIsMainpart;
        }

        public String getPartsAmount() {
            return partsAmount;
        }

        public void setPartsAmount(String partsAmount) {
            this.partsAmount = partsAmount;
        }

        public String getPartsAscCode() {
            return partsAscCode;
        }

        public void setPartsAscCode(String partsAscCode) {
            this.partsAscCode = partsAscCode;
        }

        public String getPartsAscName() {
            return partsAscName;
        }

        public void setPartsAscName(String partsAscName) {
            this.partsAscName = partsAscName;
        }

        public String getPartsVin() {
            return partsVin;
        }

        public void setPartsVin(String partsVin) {
            this.partsVin = partsVin;
        }

        public String getPartsEngineNo() {
            return partsEngineNo;
        }

        public void setPartsEngineNo(String partsEngineNo) {
            this.partsEngineNo = partsEngineNo;
        }

        public String getPartsModel() {
            return partsModel;
        }

        public void setPartsModel(String partsModel) {
            this.partsModel = partsModel;
        }

        public String getPartsSeries() {
            return partsSeries;
        }

        public void setPartsSeries(String partsSeries) {
            this.partsSeries = partsSeries;
        }

        public String getPartsMileage() {
            return partsMileage;
        }

        public void setPartsMileage(String partsMileage) {
            this.partsMileage = partsMileage;
        }

        public String getPartsClaimType() {
            return partsClaimType;
        }

        public void setPartsClaimType(String partsClaimType) {
            this.partsClaimType = partsClaimType;
        }

        public String getPartsTroubleDesc() {
            return partsTroubleDesc;
        }

        public void setPartsTroubleDesc(String partsTroubleDesc) {
            this.partsTroubleDesc = partsTroubleDesc;
        }

        public String getPartsTroubleReason() {
            return partsTroubleReason;
        }

        public void setPartsTroubleReason(String partsTroubleReason) {
            this.partsTroubleReason = partsTroubleReason;
        }

        public String getPartsClaimSubmitDate() {
            return partsClaimSubmitDate;
        }

        public void setPartsClaimSubmitDate(String partsClaimSubmitDate) {
            this.partsClaimSubmitDate = partsClaimSubmitDate;
        }

        public String getPartsTranswrCreateDate() {
            return partsTranswrCreateDate;
        }

        public void setPartsTranswrCreateDate(String partsTranswrCreateDate) {
            this.partsTranswrCreateDate = partsTranswrCreateDate;
        }

        public String getPartsAddressId() {
            return partsAddressId;
        }

        public void setPartsAddressId(String partsAddressId) {
            this.partsAddressId = partsAddressId;
        }

        public String getPartsBackWays() {
            return partsBackWays;
        }

        public void setPartsBackWays(String partsBackWays) {
            this.partsBackWays = partsBackWays;
        }

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

        public String getPartsQuantity() {
            return partsQuantity;
        }

        public void setPartsQuantity(String partsQuantity) {
            this.partsQuantity = partsQuantity;
        }

        public String getPartsBatch() {
            return partsBatch;
        }

        public void setPartsBatch(String partsBatch) {
            this.partsBatch = partsBatch;
        }

        public String getPartsProcessState() {
            return partsProcessState;
        }

        public void setPartsProcessState(String partsProcessState) {
            this.partsProcessState = partsProcessState;
        }

        public String getPartsSecondJudgeReason() {
            return partsSecondJudgeReason;
        }

        public void setPartsSecondJudgeReason(String partsSecondJudgeReason) {
            this.partsSecondJudgeReason = partsSecondJudgeReason;
        }

        public String getPartsOtherJudgeReason() {
            return partsOtherJudgeReason;
        }

        public void setPartsOtherJudgeReason(String partsOtherJudgeReason) {
            this.partsOtherJudgeReason = partsOtherJudgeReason;
        }

        public String getPartsReturnResult() {
            return partsReturnResult;
        }

        public void setPartsReturnResult(String partsReturnResult) {
            this.partsReturnResult = partsReturnResult;
        }

        public String getPartsQrUrl() {
            return partsQrUrl;
        }

        public void setPartsQrUrl(String partsQrUrl) {
            this.partsQrUrl = partsQrUrl;
        }

        public String getEncasementCode() {
            return encasementCode;
        }

        public void setEncasementCode(String encasementCode) {
            this.encasementCode = encasementCode;
        }

        public String getPartsEncasementId() {
            return partsEncasementId;
        }

        public void setPartsEncasementId(String partsEncasementId) {
            this.partsEncasementId = partsEncasementId;
        }

        public String getPartsOutstorageId() {
            return partsOutstorageId;
        }

        public void setPartsOutstorageId(String partsOutstorageId) {
            this.partsOutstorageId = partsOutstorageId;
        }

        public String getPartsPutstorageId() {
            return partsPutstorageId;
        }

        public void setPartsPutstorageId(String partsPutstorageId) {
            this.partsPutstorageId = partsPutstorageId;
        }

        public String getPartsTwrPartId() {
            return partsTwrPartId;
        }

        public void setPartsTwrPartId(String partsTwrPartId) {
            this.partsTwrPartId = partsTwrPartId;
        }

        public String getOperateFlag() {
            return operateFlag;
        }

        public void setOperateFlag(String operateFlag) {
            this.operateFlag = operateFlag;
        }

        public String getPartsRemark() {
            return partsRemark;
        }

        public void setPartsRemark(String partsRemark) {
            this.partsRemark = partsRemark;
        }

        public String getPartsStorage() {
            return partsStorage;
        }

        public void setPartsStorage(String partsStorage) {
            this.partsStorage = partsStorage;
        }

        public String getPartsOutNumber() {
            return partsOutNumber;
        }

        public void setPartsOutNumber(String partsOutNumber) {
            this.partsOutNumber = partsOutNumber;
        }

        public String getPutStorageSeat() {
            return putStorageSeat;
        }

        public void setPutStorageSeat(String putStorageSeat) {
            this.putStorageSeat = putStorageSeat;
        }

        public String getPutStoragePartsNumber() {
            return putStoragePartsNumber;
        }

        public void setPutStoragePartsNumber(String putStoragePartsNumber) {
            this.putStoragePartsNumber = putStoragePartsNumber;
        }

        public String getPartsReleaseDate() {
            return partsReleaseDate;
        }

        public void setPartsReleaseDate(String partsReleaseDate) {
            this.partsReleaseDate = partsReleaseDate;
        }

        public String getPartsReasonCode() {
            return partsReasonCode;
        }

        public void setPartsReasonCode(String partsReasonCode) {
            this.partsReasonCode = partsReasonCode;
        }
    }

    public static class StorageBean implements Serializable {
        /**
         * storageDictionarysId : 001
         * storageAddressName : 无锡申联仓库
         */

        private String storageDictionarysId;
        private String storageAddressName;

        public String getStorageDictionarysId() {
            return storageDictionarysId;
        }

        public void setStorageDictionarysId(String storageDictionarysId) {
            this.storageDictionarysId = storageDictionarysId;
        }

        public String getStorageAddressName() {
            return storageAddressName;
        }

        public void setStorageAddressName(String storageAddressName) {
            this.storageAddressName = storageAddressName;
        }
    }
}
