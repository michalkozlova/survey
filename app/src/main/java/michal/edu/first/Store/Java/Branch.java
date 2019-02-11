package michal.edu.first.Store.Java;

import java.io.Serializable;

import michal.edu.first.Store.Java.Address;

public class Branch implements Serializable {

    private String branchNameEng;
    private String branchNameHeb;
    private String branchPhone;
    private Address branchAddress;
    private String branchID;

    public Branch() {
    }

    public Branch(String branchNameEng, String branchNameHeb, String branchPhone, Address branchAddress, String branchID) {
        this.branchNameEng = branchNameEng;
        this.branchNameHeb = branchNameHeb;
        this.branchPhone = branchPhone;
        this.branchAddress = branchAddress;
        this.branchID = branchID;
    }

    public String getBranchNameEng() {
        return branchNameEng;
    }

    public void setBranchNameEng(String branchNameEng) {
        this.branchNameEng = branchNameEng;
    }

    public String getBranchNameHeb() {
        return branchNameHeb;
    }

    public void setBranchNameHeb(String branchNameHeb) {
        this.branchNameHeb = branchNameHeb;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public Address getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(Address branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchNameEng='" + branchNameEng + '\'' +
                ", branchNameHeb='" + branchNameHeb + '\'' +
                ", branchPhone='" + branchPhone + '\'' +
                ", branchAddress=" + branchAddress +
                ", branchID='" + branchID + '\'' +
                '}';
    }
}
