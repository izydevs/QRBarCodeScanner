package com.amit.hexahash.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.amit.hexahash.Model.Asset;
import com.amit.hexahash.Repository.AssestRepoitory;

import java.util.List;

public class AssetViewModel extends AndroidViewModel {

    private AssestRepoitory repoitory;
    private LiveData<List<Asset>> myAssetList;

    public AssetViewModel(@NonNull Application application) {
        super(application);
        repoitory = new AssestRepoitory(application);
        myAssetList = repoitory.getMyAssetDetails();
    }

    public LiveData<List<Asset>> getMyAssetList() {
        if (myAssetList == null) {
            myAssetList = new MutableLiveData<>();
        }
        return myAssetList;
    }

    public void insertData(Asset asset) {
        repoitory.insertAssetDetail(asset);
    }
}
