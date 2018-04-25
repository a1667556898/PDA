package com.smcv.xyx.sh.claimpda.model;

import java.io.Serializable;

/**
 * Created by wangxin on 24/03/2018.
 */

public class KuWeiBean implements Serializable {

    /**
     * storageAddress : 南京
     * storageSeat : A1
     */

    private String storageAddress;
    private String storageSeat;

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public String getStorageSeat() {
        return storageSeat;
    }

    public void setStorageSeat(String storageSeat) {
        this.storageSeat = storageSeat;
    }
}
