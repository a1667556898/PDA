package com.smcv.xyx.sh.claimpda.model;

import java.util.List;

/**
 * Created by wangxin on 17/04/2018.
 */

public class AAA {

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

    public static class CommonListBean {
        /**
         * code : 0
         * msg : 操作成功
         * result : [{"value":null,"label":"回运到库索赔件数量与需回运数据不一致"},{"value":null,"label":"回运到库索赔件与需回运零件不一致"},{"value":null,"label":"回运件为非本批次回运零件"},{"value":null,"label":"其他"}]
         */

        private int code;
        private String msg;
        private List<ResultBean> result;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
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

        public static class ResultBean {
            /**
             * value : null
             * label : 回运到库索赔件数量与需回运数据不一致
             */

            private Object value;
            private String label;

            public Object getValue() {
                return value;
            }

            public void setValue(Object value) {
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

    public static class PartsBean {
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
        private Object createBy;
        private Object updateBy;
        private String partsId;
        private String partsRoNo;
        private int partsLineNo;
        private String partsIsMainpart;
        private int partsAmount;
        private String partsAscCode;
        private String partsAscName;
        private String partsVin;
        private String partsEngineNo;
        private Object partsModel;
        private Object partsSeries;
        private Object partsMileage;
        private String partsClaimType;
        private String partsTroubleDesc;
        private String partsTroubleReason;
        private String partsClaimSubmitDate;
        private Object partsTranswrCreateDate;
        private Object partsAddressId;
        private Object partsBackWays;
        private String partsReturnPartCode;
        private String partsReturnPartName;
        private int partsQuantity;
        private String partsBatch;
        private int partsProcessState;
        private Object partsSecondJudgeReason;
        private Object partsOtherJudgeReason;
        private Object partsReturnResult;
        private Object partsQrUrl;
        private Object encasementCode;
        private Object partsEncasementId;
        private Object partsOutstorageId;
        private Object partsPutstorageId;
        private Object partsTwrPartId;
        private Object operateFlag;
        private Object partsRemark;
        private Object partsStorage;
        private Object partsOutNumber;
        private Object putStorageSeat;
        private Object putStoragePartsNumber;
        private Object partsReleaseDate;
        private Object partsReasonCode;

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

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
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

        public int getPartsLineNo() {
            return partsLineNo;
        }

        public void setPartsLineNo(int partsLineNo) {
            this.partsLineNo = partsLineNo;
        }

        public String getPartsIsMainpart() {
            return partsIsMainpart;
        }

        public void setPartsIsMainpart(String partsIsMainpart) {
            this.partsIsMainpart = partsIsMainpart;
        }

        public int getPartsAmount() {
            return partsAmount;
        }

        public void setPartsAmount(int partsAmount) {
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

        public Object getPartsModel() {
            return partsModel;
        }

        public void setPartsModel(Object partsModel) {
            this.partsModel = partsModel;
        }

        public Object getPartsSeries() {
            return partsSeries;
        }

        public void setPartsSeries(Object partsSeries) {
            this.partsSeries = partsSeries;
        }

        public Object getPartsMileage() {
            return partsMileage;
        }

        public void setPartsMileage(Object partsMileage) {
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

        public Object getPartsTranswrCreateDate() {
            return partsTranswrCreateDate;
        }

        public void setPartsTranswrCreateDate(Object partsTranswrCreateDate) {
            this.partsTranswrCreateDate = partsTranswrCreateDate;
        }

        public Object getPartsAddressId() {
            return partsAddressId;
        }

        public void setPartsAddressId(Object partsAddressId) {
            this.partsAddressId = partsAddressId;
        }

        public Object getPartsBackWays() {
            return partsBackWays;
        }

        public void setPartsBackWays(Object partsBackWays) {
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

        public int getPartsQuantity() {
            return partsQuantity;
        }

        public void setPartsQuantity(int partsQuantity) {
            this.partsQuantity = partsQuantity;
        }

        public String getPartsBatch() {
            return partsBatch;
        }

        public void setPartsBatch(String partsBatch) {
            this.partsBatch = partsBatch;
        }

        public int getPartsProcessState() {
            return partsProcessState;
        }

        public void setPartsProcessState(int partsProcessState) {
            this.partsProcessState = partsProcessState;
        }

        public Object getPartsSecondJudgeReason() {
            return partsSecondJudgeReason;
        }

        public void setPartsSecondJudgeReason(Object partsSecondJudgeReason) {
            this.partsSecondJudgeReason = partsSecondJudgeReason;
        }

        public Object getPartsOtherJudgeReason() {
            return partsOtherJudgeReason;
        }

        public void setPartsOtherJudgeReason(Object partsOtherJudgeReason) {
            this.partsOtherJudgeReason = partsOtherJudgeReason;
        }

        public Object getPartsReturnResult() {
            return partsReturnResult;
        }

        public void setPartsReturnResult(Object partsReturnResult) {
            this.partsReturnResult = partsReturnResult;
        }

        public Object getPartsQrUrl() {
            return partsQrUrl;
        }

        public void setPartsQrUrl(Object partsQrUrl) {
            this.partsQrUrl = partsQrUrl;
        }

        public Object getEncasementCode() {
            return encasementCode;
        }

        public void setEncasementCode(Object encasementCode) {
            this.encasementCode = encasementCode;
        }

        public Object getPartsEncasementId() {
            return partsEncasementId;
        }

        public void setPartsEncasementId(Object partsEncasementId) {
            this.partsEncasementId = partsEncasementId;
        }

        public Object getPartsOutstorageId() {
            return partsOutstorageId;
        }

        public void setPartsOutstorageId(Object partsOutstorageId) {
            this.partsOutstorageId = partsOutstorageId;
        }

        public Object getPartsPutstorageId() {
            return partsPutstorageId;
        }

        public void setPartsPutstorageId(Object partsPutstorageId) {
            this.partsPutstorageId = partsPutstorageId;
        }

        public Object getPartsTwrPartId() {
            return partsTwrPartId;
        }

        public void setPartsTwrPartId(Object partsTwrPartId) {
            this.partsTwrPartId = partsTwrPartId;
        }

        public Object getOperateFlag() {
            return operateFlag;
        }

        public void setOperateFlag(Object operateFlag) {
            this.operateFlag = operateFlag;
        }

        public Object getPartsRemark() {
            return partsRemark;
        }

        public void setPartsRemark(Object partsRemark) {
            this.partsRemark = partsRemark;
        }

        public Object getPartsStorage() {
            return partsStorage;
        }

        public void setPartsStorage(Object partsStorage) {
            this.partsStorage = partsStorage;
        }

        public Object getPartsOutNumber() {
            return partsOutNumber;
        }

        public void setPartsOutNumber(Object partsOutNumber) {
            this.partsOutNumber = partsOutNumber;
        }

        public Object getPutStorageSeat() {
            return putStorageSeat;
        }

        public void setPutStorageSeat(Object putStorageSeat) {
            this.putStorageSeat = putStorageSeat;
        }

        public Object getPutStoragePartsNumber() {
            return putStoragePartsNumber;
        }

        public void setPutStoragePartsNumber(Object putStoragePartsNumber) {
            this.putStoragePartsNumber = putStoragePartsNumber;
        }

        public Object getPartsReleaseDate() {
            return partsReleaseDate;
        }

        public void setPartsReleaseDate(Object partsReleaseDate) {
            this.partsReleaseDate = partsReleaseDate;
        }

        public Object getPartsReasonCode() {
            return partsReasonCode;
        }

        public void setPartsReasonCode(Object partsReasonCode) {
            this.partsReasonCode = partsReasonCode;
        }
    }

    public static class StorageBean {
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
