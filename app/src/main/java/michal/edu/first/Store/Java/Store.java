package michal.edu.first.Store.Java;

import java.io.Serializable;
import java.util.ArrayList;

import michal.edu.first.Store.Java.Branch;

public class Store implements Serializable {

    private Integer storeType;
    private String StoreNameEng;
    private String StoreNameHeb;
    private ArrayList<Branch> branches;

    public Store() {
    }

    public Store(Integer storeType, String storeNameEng, String storeNameHeb) {
        this.storeType = storeType;
        StoreNameEng = storeNameEng;
        StoreNameHeb = storeNameHeb;
    }

    public Store(Integer storeType, String storeNameEng, String storeNameHeb, ArrayList<Branch> branches) {
        this.storeType = storeType;
        StoreNameEng = storeNameEng;
        StoreNameHeb = storeNameHeb;
        this.branches = branches;
    }

    public Integer getStoreType() {
        return storeType;
    }



    public String getStoreNameEng() {
        return StoreNameEng;
    }

    public void setStoreNameEng(String storeNameEng) {
        StoreNameEng = storeNameEng;
    }

    public String getStoreNameHeb() {
        return StoreNameHeb;
    }

    public void setStoreNameHeb(String storeNameHeb) {
        StoreNameHeb = storeNameHeb;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeType=" + storeType +
                ", StoreNameEng='" + StoreNameEng + '\'' +
                ", StoreNameHeb='" + StoreNameHeb + '\'' +
                ", branches=" + branches +
                '}';
    }
}
