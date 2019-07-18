package com.amit.hexahash.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amit.hexahash.Model.Asset;
import com.amit.hexahash.Repository.AssetRepository;

import java.util.ArrayList;
import java.util.List;

public class AssetViewModel extends AndroidViewModel {

    private AssetRepository repository;
    private LiveData<List<Asset>> myAssetList;
    private MutableLiveData<List<Asset>> mySearchAssetList;

    public AssetViewModel(@NonNull Application application) {
        super(application);
        repository = new AssetRepository(application);
        myAssetList = repository.getMyAssetDetails();
    }

    public LiveData<List<Asset>> getMyAssetList() {
        if (myAssetList == null) {
            myAssetList = new MutableLiveData<>();
        }
        return myAssetList;
    }


    public LiveData<List<Asset>> getSearchAssetList() {
        if (mySearchAssetList == null) {
            mySearchAssetList = new MutableLiveData<>();
        }
        return mySearchAssetList;
    }

    public void insertData(Asset asset) {
        repository.insertAssetDetail(asset);
    }

    public void searchAsset(List<String> barCodeValues) {
        repository.getSearchAssetDetails(barCodeValues).observeForever(new Observer<List<Asset>>() {
            @Override
            public void onChanged(@Nullable List<Asset> assets) {
                if (assets != null) {
                    Log.d("asdf", "asset found " + assets);
                    mySearchAssetList.postValue(assets);
                }
            }
        });
    }
}
