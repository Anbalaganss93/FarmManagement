package com.farmmanagement.roomDP;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "pond_table",
        foreignKeys = @ForeignKey(entity = UserDetail.class,
                parentColumns = "mobilenumber",
                childColumns = "mobilenumber",
                onDelete = ForeignKey.CASCADE))
public class PondDetails {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pondid")
    private long pondid=0;

    @ColumnInfo(name = "pondname")
    private String pondname;

    @ColumnInfo(name = "pondsize")
    private float pondsize;

    @NonNull
    @ColumnInfo(name = "mobilenumber")
    private String mobilenumber;

    public PondDetails(long pondid, String pondname, float pondsize, @NonNull String mobilenumber) {
        this.pondid = pondid;
        this.pondname = pondname;
        this.pondsize = pondsize;
        this.mobilenumber = mobilenumber;
    }

    public long getPondid() {
        return pondid;
    }

    public String getPondname() {
        return pondname;
    }

    public float getPondsize() {
        return pondsize;
    }

    @NonNull
    public String getMobilenumber() {
        return mobilenumber;
    }
}
