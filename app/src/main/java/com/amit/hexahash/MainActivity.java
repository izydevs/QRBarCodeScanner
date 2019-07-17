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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amit.hexahash.Model.Asset;
import com.amit.hexahash.ViewModel.AssetViewModel;

import java.util.List;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private TextView barCodeTV;
    private EditText assesName;
    private AssetViewModel model;
    private boolean isAssetAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBindViews();
    }

    private void initBindViews() {
        model = ViewModelProviders.of(MainActivity.this).get(AssetViewModel.class);
        model.getMyAssetList().observe(MainActivity.this, new Observer<List<Asset>>() {
            @Override
            public void onChanged(@Nullable List<Asset> assets) {
                if (isAssetAdded)
                    updateUI();
            }
        });
        ImageView scanBarcode = findViewById(R.id.scan_barcode);
        barCodeTV = findViewById(R.id.bar_code_tv);
        assesName = findViewById(R.id.asset_name);
        TextView addAssetBtn = findViewById(R.id.add_asset);
        scanBarcode.setOnClickListener(this);
        addAssetBtn.setOnClickListener(this);
    }

    private void updateUI() {
        Toast.makeText(getApplicationContext(), "Asset added successfully", Toast.LENGTH_SHORT).show();
        assesName.setText("");
        barCodeTV.setText(getResources().getString(R.string.add_asset_text));
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
                    barCodeTV.setText(barCodeValue);
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan_barcode:
                scanBarcode();
                break;
            case R.id.add_asset:
                insertAssetData();
                break;
        }
    }

    private void insertAssetData() {
        if (assesName != null && assesName.getText().toString().equals("")) {
            assesName.setError("Enter asset name..!!");
        } else if (barCodeTV != null && barCodeTV.getText().toString().equals(getResources().getString(R.string.add_asset_text))) {
            Toast.makeText(getApplicationContext(), "Scan QR/Bar code", Toast.LENGTH_SHORT).show();
        } else {
            assert barCodeTV != null;
            Asset asset = new Asset("Mobile", barCodeTV.getText().toString());
            model.insertData(asset);
            isAssetAdded = true;
        }
    }

    private void scanBarcode() {
        if (checkPermission()) {
            startBarcodeScanning();
        } else {
            requestPermission();
        }
    }
}
