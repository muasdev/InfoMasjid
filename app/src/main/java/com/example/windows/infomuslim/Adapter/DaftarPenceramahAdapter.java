package com.example.windows.infomuslim.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.windows.infomuslim.ItemClickSupport;
import com.example.windows.infomuslim.Model.ResponseDataPenceramah;
import com.example.windows.infomuslim.R;

import java.util.ArrayList;

import butterknife.BindView;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DaftarPenceramahAdapter extends RecyclerView.Adapter<DaftarPenceramahAdapter.ViewHolder> implements Filterable {


    private ArrayList<ResponseDataPenceramah> mData;
    private ArrayList<ResponseDataPenceramah> filterList;
    Context context;
    Dialog dialog;
    RequestOptions options;

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
                .inflate(R.layout.muballigh_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ResponseDataPenceramah movieItems = filterList.get(position);
        int lat, lng;
        final String gambarFilm = movieItems
                .getFoto();
        Log.d(TAG, "gambarFilm " + movieItems.getFoto());
//        http://muslim-info.xakti.tech/img/penceramah/penceramah_20181021090126.jpg
//        http://muslim-info.xakti.tech/api/penceramah
//        http://muslim-info.xakti.tech/img/penceramah/penceramah_20181104143153.png

//        .placeholder(R.drawable.spinner)
        options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.no_picture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load("http://muslim-info.xakti.tech/img/penceramah/" + gambarFilm)
                .apply(options)
                .into(holder.imageFilm);

        holder.imageFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(view.getContext());
//                dialog.setTitle(movieItems.getNama());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialog);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.show();
                TextView textViewNama = (TextView) dialog.findViewById(R.id.dialog_text_title);
                Button close = (Button) dialog.findViewById(R.id.dialogbutton);
                textViewNama.setText(movieItems.getNama());
                ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogimg);
                Glide.with(context)
                        .load("http://muslim-info.xakti.tech/img/penceramah/" + gambarFilm)
                        .apply(options)
                        .into(imageView);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nomorTelpon = movieItems.getKontak();
                        dialNumber(nomorTelpon);
//                        dialog.dismiss();
                    }
                });
            }
        });

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
                /*String nomorTelpon = movieItems.getKontak();
                dialNumber(nomorTelpon);*/
                dialog = new Dialog(view.getContext());
//                dialog.setTitle(movieItems.getNama());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.customdialog);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.show();
                TextView textViewNama = (TextView) dialog.findViewById(R.id.dialog_text_title);
                Button close = (Button) dialog.findViewById(R.id.dialogbutton);
                textViewNama.setText(movieItems.getNama());
                ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogimg);
                Glide.with(context)
                        .load("http://muslim-info.xakti.tech/img/penceramah/" + gambarFilm)
                        .apply(options)
                        .into(imageView);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
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

        @BindView(R.id.tv_alamat_muballigh)
        TextView tvAlamatPenceramah;
        @BindView(R.id.tv_kontak_muballigh)
        TextView tvKontakPenceramah;
        @BindView(R.id.card_view_detail_muballigh)
        CardView cardViewDetailPenceramah;

        @BindView(R.id.btn_hub_muballigh)
        Button btnHubPenceramah;


        public ViewHolder(View itemView, DaftarPenceramahAdapter masjidPareAdapter) {
            super(itemView);
            imageFilm = (ImageView) itemView.findViewById(R.id.iv_gambar_muballigh);
            judulFilm = (TextView) itemView.findViewById(R.id.tv_nama_muballigh);
            tvAlamatPenceramah = (TextView) itemView.findViewById(R.id.tv_alamat_muballigh);
            tvKontakPenceramah = (TextView) itemView.findViewById(R.id.tv_kontak_muballigh);
            cardViewDetailPenceramah = (CardView) itemView.findViewById(R.id.card_view_detail_muballigh);
            btnHubPenceramah = (Button) itemView.findViewById(R.id.btn_hub_muballigh);
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
        phoneNumber = String.format("tel: %s", phoneNumber);
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
