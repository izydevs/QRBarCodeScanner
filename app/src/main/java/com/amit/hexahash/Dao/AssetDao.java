package com.amit.hexahash.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.amit.hexahash.Model.Asset;

import java.util.List;

@Dao
public interface AssetDao {

    @Query("SELECT * FROM asset")
    LiveData<List<Asset>> getAssetDetails();

    @Query("SELECT * FROM asset WHERE barcode IN (:barCodes)")
    LiveData<List<Asset>> getAssetDetail(List<String> barCodes);

    @Insert
    void insertAsset(Asset asset);

}
