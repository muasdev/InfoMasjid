package com.example.windows.infomuslim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.windows.infomuslim.ItemClickSupport;
import com.example.windows.infomuslim.Model.ResponseDataPenceramah;
import com.example.windows.infomuslim.R;

import java.util.ArrayList;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class DaftarPenceramahAdapter extends RecyclerView.Adapter<DaftarPenceramahAdapter.ViewHolder> implements Filterable {


    private ArrayList<ResponseDataPenceramah> mData;
    private ArrayList<ResponseDataPenceramah> filterList;
    Context context;

    public void setPenceramahList(Context context, final ArrayList<ResponseDataPenceramah> mData) {
        this.context = context;
//        this.mData = mData;
//        this.filterList = mData;
        if (this.mData == null) {
            this.mData = mData;
            this.filterList = mData;
            notifyItemChanged(0, filterList.size());
            Log.d(TAG, "setMasjidList: datanull");
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return DaftarPenceramahAdapter.this.mData.size();
                }

                @Override
                public int getNewListSize() {
                    return mData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return DaftarPenceramahAdapter.this.mData.get(oldItemPosition).getNama() == mData.get(newItemPosition).getNama();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    ResponseDataPenceramah newResponseDataPenceramah = DaftarPenceramahAdapter.this.mData.get(oldItemPosition);

                    ResponseDataPenceramah oldResponseDataPenceramah = mData.get(newItemPosition);

                    return newResponseDataPenceramah.getNama() == oldResponseDataPenceramah.getNama();
                }
            });
            this.mData = mData;
            this.filterList = mData;
            result.dispatchUpdatesTo(this);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.penceramah_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ResponseDataPenceramah movieItems = filterList.get(position);
        int lat, lng;
        String gambarFilm = movieItems
                .getFoto();
        Log.d(TAG, "gambarFilm " + movieItems.getFoto());
//        http://muslim-info.xakti.tech/img/penceramah/penceramah_20181021090126.jpg
//        http://muslim-info.xakti.tech/api/penceramah
//        http://muslim-info.xakti.tech/img/penceramah/penceramah_20181104143153.png

        Glide.with(context)
                .load("http://muslim-info.xakti.tech/img/penceramah/" + gambarFilm)
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(holder.imageFilm);

        holder.judulFilm
                .setText(movieItems.getNama());

        holder.tvAlamatPenceramah
                .setText(movieItems.getAlamat());

        holder.tvKontakPenceramah
                .setText(movieItems.getKontak());

        holder.btnHubPenceramah.setOnClickListener(new ItemClickSupport(position, new ItemClickSupport.OnItemClick() {
            @Override
            public void onItemClicked(View view, int position) {
//                Toast.makeText(context, "ini" + movieItems.getKontak(), Toast.LENGTH_SHORT).show();
                String nomorTelpon = movieItems.getKontak();
                dialNumber(nomorTelpon);
            }
        }));

        /*holder.cardView.setOnClickListener(new ItemClickSupport(position, new ItemClickSupport.OnItemClick() {
            @Override
            public void onItemClicked(View view, int position) {
                ResponseDataPenceramah movieItems1 = getmData().get(position);
                Intent i = new Intent(context, DetailMasjidActivity.class);
                i.putExtra("nm_masjid", movieItems1.getNama());
                i.putExtra("gambar", movieItems1.getFoto());
                context.startActivity(i);
            }
        }));*/


    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return filterList.size();
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
                    filterList = mData;
                } else {
                    ArrayList<ResponseDataPenceramah> filteredList = new ArrayList<>();
                    for (ResponseDataPenceramah masjid : mData) {
                        if (masjid.getNama().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(masjid);
                        }
                    }
                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (ArrayList<ResponseDataPenceramah>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageFilm;
        TextView judulFilm;

        @BindView(R.id.tv_alamat_penceramah)
        TextView tvAlamatPenceramah;
        @BindView(R.id.tv_kontak_penceramah)
        TextView tvKontakPenceramah;
        @BindView(R.id.card_view_detail_penceramah)
        CardView cardViewDetailPenceramah;

        @BindView(R.id.btn_hub_penceramah)
        Button btnHubPenceramah;


        public ViewHolder(View itemView, DaftarPenceramahAdapter masjidPareAdapter) {
            super(itemView);
            imageFilm = (ImageView) itemView.findViewById(R.id.iv_gambar_penceramah);
            judulFilm = (TextView) itemView.findViewById(R.id.tv_nama_penceramah);
            tvAlamatPenceramah = (TextView) itemView.findViewById(R.id.tv_alamat_penceramah);
            tvKontakPenceramah = (TextView) itemView.findViewById(R.id.tv_kontak_penceramah);
            cardViewDetailPenceramah = (CardView) itemView.findViewById(R.id.card_view_detail_penceramah);
            btnHubPenceramah = (Button) itemView.findViewById(R.id.btn_hub_penceramah);
        }

    }

    public ArrayList<ResponseDataPenceramah> getmData() {
        return mData;
    }

    public void setmData(ArrayList<ResponseDataPenceramah> mData) {
        this.mData = mData;
        /*notifyDataSetChanged() berfungsi untuk mengabari adapter bahwa ada data baru yang telah diterima.
            Ketika fungsi ini dijalankan, maka Recyclerview yang didaftarkan pada adapter
        akan menampilkan data tersebut*/
        notifyDataSetChanged();
        /*ingat tambahkan selalu ini, krn kalau tidak, akan muncul pesan
            Skipped 37 frames! The application may be doing too much work on its main thread.*/
    }

    public void dialNumber(String phoneNumber) {
        // Use format with "tel:" and phone number to create phoneNumber.
        phoneNumber = String.format("tel: %s",phoneNumber);
        // Create the intent.
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        dialIntent.setData(Uri.parse(phoneNumber));
        dialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dialIntent);
        // If package resolves to an app, send intent.
        /*if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.e(TAG, "Can't resolve app for ACTION_DIAL Intent.");
        }*/
    }
}
