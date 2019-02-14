package com.example.windows.infomuslim.Adapter;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.windows.infomuslim.Model.ModelDataPanti;
import com.example.windows.infomuslim.R;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

//import static androidx.constraintlayout.Constraints.TAG;

public class DaftarPantiAdapter extends RecyclerView.Adapter<DaftarPantiAdapter.ViewHolder> implements Filterable {


    private ArrayList<ModelDataPanti> modelDataPantis;
    private ArrayList<ModelDataPanti> modelDataPantisFilter;
    Context context;

    public void setDataPanti(Context context, final ArrayList<ModelDataPanti> modelDataPantis) {
        this.context = context;
//        this.modelDataPantis = modelDataPantis;
//        this.modelDataPantisFilter = modelDataPantis;
        if (this.modelDataPantis == null) {
            this.modelDataPantis = modelDataPantis;
            this.modelDataPantisFilter = modelDataPantis;
            notifyItemChanged(0, modelDataPantisFilter.size());
            Log.d(TAG, "setMasjidList: datanull");
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return DaftarPantiAdapter.this.modelDataPantis.size();
                }

                @Override
                public int getNewListSize() {
                    return modelDataPantis.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return DaftarPantiAdapter.this.modelDataPantis.get(oldItemPosition).getNmPanti() == modelDataPantis.get(newItemPosition).getNmPanti();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    ModelDataPanti newModelDataPanti = DaftarPantiAdapter.this.modelDataPantis.get(oldItemPosition);

                    ModelDataPanti oldModelDataPanti = modelDataPantis.get(newItemPosition);

                    return newModelDataPanti.getNmPanti() == oldModelDataPanti.getNmPanti();
                }
            });
            this.modelDataPantis = modelDataPantis;
            this.modelDataPantisFilter = modelDataPantis;
            result.dispatchUpdatesTo(this);
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.panti_items, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvItemNamaPanti
                .setText(modelDataPantisFilter.get(i).getNmPanti());

        String hasilConvertHtml;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            hasilConvertHtml = String.valueOf(Html.fromHtml(modelDataPantisFilter.get(i).getDeskripsi(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            hasilConvertHtml = String.valueOf(Html.fromHtml(modelDataPantisFilter.get(i).getDeskripsi()));

        }

        viewHolder.tvItemDeskripsiPanti
                .setText(hasilConvertHtml);

        String gambarFilm = modelDataPantisFilter.get(i)
                .getGambar();

        //http://muslim-info.xakti.tech/img/panti_asuhan/panti_asuhan_20181205080058.jpg
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.mosque_icon_64)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load("http://muslim-info.xakti.tech/img/panti_asuhan/" + gambarFilm)
                .apply(options)
                .into(viewHolder.ivItemGambarPanti);

    }

    @Override
    public int getItemCount() {
        if (modelDataPantis != null) {
            return modelDataPantisFilter.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    modelDataPantisFilter = modelDataPantis;
                } else {
                    ArrayList<ModelDataPanti> filteredList = new ArrayList<>();
                    for (ModelDataPanti masjid : modelDataPantis) {
                        if (masjid.getNmPanti().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(masjid);
                        }
                    }
                    modelDataPantisFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = modelDataPantisFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                modelDataPantisFilter = (ArrayList<ModelDataPanti>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItemGambarPanti;
        TextView tvItemNamaPanti;
        TextView tvItemDeskripsiPanti;

        public ViewHolder(@NonNull View itemView, DaftarPantiAdapter daftarPantiAdapter) {
            super(itemView);
            ivItemGambarPanti = (ImageView) itemView.findViewById(R.id.iv_item_gambar_panti);
            tvItemNamaPanti = (TextView) itemView.findViewById(R.id.tv_item_nama_panti);
            tvItemDeskripsiPanti = (TextView) itemView.findViewById(R.id.tv_item_deskripsi_panti);

        }
    }


}
