package com.amit.hexahash.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "asset")
public class Asset {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "asset_name")
    private String assetName;

    @ColumnInfo(name = "barcode")
    private String barCode;

    public Asset(String assetName, String barCode) {
        this.assetName = assetName;
        this.barCode = barCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
