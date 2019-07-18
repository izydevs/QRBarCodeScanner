package com.amit.hexahash;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.hexahash.Adapter.AssetAdapter;
import com.amit.hexahash.Model.Asset;
import com.amit.hexahash.ViewModel.AssetViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class SearchAssetActivity extends AppCompatActivity implements View.OnClickListener {

    private AssetViewModel model;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private AssetAdapter assetAdapter;
    private RecyclerView assetRV;
    private List<String> barCodes = new ArrayList<>();
    private CheckBox singleSearch;
    private CheckBox multipleSearch;
    private boolean isSingleSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_asset);

        initBindViews();
        recyclerViewSetup();
        model = ViewModelProviders.of(SearchAssetActivity.this).get(AssetViewModel.class);
        model.getSearchAssetList().observe(SearchAssetActivity.this, new Observer<List<Asset>>() {
            @Override
            public void onChanged(@Nullable List<Asset> assets) {
               updateUI(assets);
            }
        });
    }

    private void updateUI(List<Asset> assets) {
        barCodes.clear();
        if (assets.size() > 0) {
            assetRV.setVisibility(View.VISIBLE);
            assetAdapter = new AssetAdapter(assets, getApplicationContext());
            assetRV.setAdapter(assetAdapter);
        } else {
            assetRV.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Invalid Bar code", Toast.LENGTH_SHORT).show();
        }
    }

    private void initBindViews() {
        singleSearch = findViewById(R.id.single_search);
        multipleSearch = findViewById(R.id.multi_search);
        TextView searchAsset = findViewById(R.id.search_asset);
        ImageView scanBarCode = findViewById(R.id.scan_barcode);
        searchAsset.setOnClickListener(this);
        singleSearch.setOnClickListener(this);
        multipleSearch.setOnClickListener(this);
        scanBarCode.setOnClickListener(this);
    }

    private void recyclerViewSetup() {
        assetRV = findViewById(R.id.asset_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        assetRV.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.single_search:
                searchSingleAsset();
                break;
            case R.id.multi_search:
               searchMultipleAsset();
                break;
            case R.id.search_asset:
               searchAsset();
                break;
            case R.id.scan_barcode:
                scanBarcode();
                break;
        }
    }

    private void searchAsset() {
        assetRV.setVisibility(View.GONE);
        if (barCodes.size()>0) {
            Log.d("asdf","barcodes size is "+barCodes.size());
            model.searchAsset(barCodes);
        }else {
            Toast.makeText(getApplicationContext(),"Please scan barcode",Toast.LENGTH_SHORT).show();
        }
    }

    private void searchMultipleAsset() {
        singleSearch.setChecked(false);
        multipleSearch.setChecked(true);
        barCodes.clear();
        isSingleSearch = false;
    }

    private void searchSingleAsset() {
        singleSearch.setChecked(true);
        multipleSearch.setChecked(false);
        barCodes.clear();
        isSingleSearch = true;
    }

    private void scanBarcode() {
        if (checkPermission()) {
            startBarcodeScanning();
        } else {
            requestPermission();
        }
    }

    private void startBarcodeScanning() {
        Intent intent = new Intent(getApplicationContext(), BarCodeScannerActivity.class);
        startActivityForResult(intent, 1);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case 100:
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted)
                    startBarcodeScanning();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String barCodeValue = data.getStringExtra("barCodeValue");
                if (barCodeValue != null) {
                    if (isSingleSearch){
                        barCodes.clear();
                    }else {
                        assetRV.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Do U want to scan more barcode",Toast.LENGTH_SHORT).show();
                    }
                    barCodes.add(barCodeValue);
                    Log.d("asdf","barcodes size is "+barCodes.size());
                }
            }
        }

    }
}
