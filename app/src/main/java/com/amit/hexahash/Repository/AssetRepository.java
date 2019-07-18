package com.amit.hexahash.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.amit.hexahash.Dao.AssetDao;
import com.amit.hexahash.Database.AppDatabase;
import com.amit.hexahash.Model.Asset;

import java.util.List;

public class AssetRepository {
    private AssetDao assetDao;
    private LiveData<List<Asset>> myAssetDetails;
    private LiveData<List<Asset>> myAssetDetail;

    public AssetRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        assetDao = appDatabase.assetDao();
        myAssetDetails = assetDao.getAssetDetails();
    }

    public LiveData<List<Asset>> getMyAssetDetails(){
        return myAssetDetails;
    }

    public LiveData<List<Asset>> getSearchAssetDetails(List<String> barCodeValues) {
        myAssetDetail = assetDao.getAssetDetail(barCodeValues);
        return myAssetDetail;
    }

    public void insertAssetDetail(Asset asset){
        new insertAsyncTask(assetDao).execute(asset);
    }

    private class insertAsyncTask extends AsyncTask<Asset,Void,Void> {
        private AssetDao assetDao;
        private insertAsyncTask(AssetDao assetDao) {
            this.assetDao = assetDao;        }

        @Override
        protected Void doInBackground(Asset... games) {
            assetDao.insertAsset(games[0]);
            return null;
        }

    }
}
