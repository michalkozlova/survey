package michal.edu.first.Store;

import java.util.ArrayList;

public class Store {

    private int storeType;
    private String StoreNameEng;
    private String StoreNameHeb;
    private ArrayList<Branch> branches;

    public Store(int storeType, String storeNameEng, String storeNameHeb) {
        this.storeType = storeType;
        StoreNameEng = storeNameEng;
        StoreNameHeb = storeNameHeb;
    }

    public Store(int storeType, String storeNameEng, String storeNameHeb, ArrayList<Branch> branches) {
        this.storeType = storeType;
        StoreNameEng = storeNameEng;
        StoreNameHeb = storeNameHeb;
        this.branches = branches;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
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
