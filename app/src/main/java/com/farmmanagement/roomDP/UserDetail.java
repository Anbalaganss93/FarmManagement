package com.farmmanagement.roomDP;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserDetail {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "mobilenumber")
    private String mobilenumber;

    @ColumnInfo(name = "farmerName")
    private String farmerName;

    @ColumnInfo(name = "farmName")
    private String farmName;

    @ColumnInfo(name = "farmAddress")
    private String farmAddress;

    @ColumnInfo(name = "pinCode")
    private String pinCode;

    public UserDetail(@NonNull String mobilenumber, String farmerName, String farmName, String farmAddress, String pinCode) {
        this.mobilenumber = mobilenumber;
        this.farmerName = farmerName;
        this.farmName = farmName;
        this.farmAddress = farmAddress;
        this.pinCode = pinCode;
    }

    @NonNull
    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public String getPinCode() {
        return pinCode;
    }
}
