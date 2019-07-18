package com.amit.hexahash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amit.hexahash.Model.Asset;
import com.amit.hexahash.R;

import java.util.List;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.MyViewHolder> {
    private Context context;
    private List<Asset> myList;

    public AssetAdapter(List<Asset> myList, Context context) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.asset_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(String.format("Asset name :- %s", myList.get(i).getAssetName()));
        myViewHolder.barcode.setText(String.format("Barcode :- %s", myList.get(i).getBarCode()));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView barcode;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.asset_name);
            barcode = itemView.findViewById(R.id.asset_barcode_id);
        }
    }
}
