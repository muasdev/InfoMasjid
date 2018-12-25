package com.example.windows.infomuslim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.windows.infomuslim.ItemClickSupport;
import com.example.windows.infomuslim.Model.MasjidModel;
import com.example.windows.infomuslim.R;
import com.example.windows.infomuslim.ui.DetailMasjidActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

public class MasjidPareAdapter extends RecyclerView.Adapter<MasjidPareAdapter.ViewHolder> implements Filterable {


    @BindView(R.id.btn_detail)
    Button btnDetail;
    @BindView(R.id.btn_share)
    Button btnShare;
    private ArrayList<MasjidModel> mData;
    private ArrayList<MasjidModel> filterList;
    Context context;

    public void setMasjidList(Context context,final ArrayList<MasjidModel> mData) {
        this.context = context;
//        this.mData = mData;
//        this.filterList = mData;
        if(this.mData == null){
            this.mData = mData;
            this.filterList = mData;
            notifyItemChanged(0, filterList.size());
            Log.d(TAG, "setMasjidList: datanull");
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MasjidPareAdapter.this.mData.size();
                }

                @Override
                public int getNewListSize() {
                    return mData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MasjidPareAdapter.this.mData.get(oldItemPosition).getNm_masjid() == mData.get(newItemPosition).getNm_masjid();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    MasjidModel newMasjidModel = MasjidPareAdapter.this.mData.get(oldItemPosition);

                    MasjidModel oldMasjidModel = mData.get(newItemPosition);

                    return newMasjidModel.getNm_masjid() == oldMasjidModel.getNm_masjid() ;
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
                .inflate(R.layout.masjid_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.judulFilm.setText(filterList.get(position).getNm_masjid());
// http://muslim-info.xakti.tech/img/masjid/masjid%20Ali-mran.png
        int lat, lng;
        String gambarFilm = filterList.get(position)
                .getGambar();


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.mosque_icon_128)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        holder.imageFilm.layout(0,0,0,0);
        Glide.with(context)
                .load("http://muslim-info.xakti.tech/img/masjid/" + gambarFilm)
                .thumbnail(1f)
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .apply(options)
                .into(holder.imageFilm);

        holder.judulFilm
                .setText(filterList.get(position).getNm_masjid());

        holder.overview
                .setText(filterList.get(position).getAlamat());



        holder.cardView.setOnClickListener(new ItemClickSupport(position, new ItemClickSupport.OnItemClick() {
            @Override
            public void onItemClicked(View view, int position) {
//                MasjidModel movieItems1 = getmData().get(position);
                MasjidModel movieItems1 = filterList.get(position);
                Intent i = new Intent(context, DetailMasjidActivity.class);
                i.putExtra("_ID", movieItems1.getId());
                i.putExtra("nm_masjid", movieItems1.getNm_masjid());
                i.putExtra("camat", movieItems1.getKecamatan());
                Log.d(TAG, "kecamatan: " + movieItems1.getKecamatan());
                i.putExtra("alamat", movieItems1.getAlamat());
                i.putExtra("thn_berdiri", movieItems1.getThn_berdiri());
                i.putExtra("imam", movieItems1.getImam());
                i.putExtra("gambar", movieItems1.getGambar());
                i.putExtra("lat", movieItems1.getLat());
                i.putExtra("lng", movieItems1.getLng());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }));

        holder.btnShare.setOnClickListener(new ItemClickSupport(position, new ItemClickSupport.OnItemClick() {
            @Override
            public void onItemClicked(View view, int position) {
                MasjidModel movieItems1 = getmData().get(position);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, movieItems1.getNm_masjid() + movieItems1.getAlamat());
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        }));

    }


    @Override
    public int getItemCount() {
        if(mData != null){
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
                    ArrayList<MasjidModel> filteredList = new ArrayList<>();
                    for (MasjidModel masjid : mData) {
                        if (masjid.getNm_masjid().toLowerCase().contains(charString.toLowerCase())) {
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
                filterList = (ArrayList<MasjidModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageFilm;
        TextView judulFilm, overview, release_date;
        Button btnDetail, btnShare;
        CardView cardView;


        public ViewHolder(View itemView, MasjidPareAdapter masjidPareAdapter) {
            super(itemView);
            imageFilm = (ImageView) itemView.findViewById(R.id.iv_gambarMasjid);
            judulFilm = (TextView) itemView.findViewById(R.id.tv_nmMasjid);
            overview = (TextView) itemView.findViewById(R.id.tv_kecamatanMasjid);
            release_date = (TextView) itemView.findViewById(R.id.tv_alamatMasjid);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);
            cardView = (CardView) itemView.findViewById(R.id.card_view_detail);
            btnShare = (Button) itemView.findViewById(R.id.btn_share);
        }

    }

    public ArrayList<MasjidModel> getmData() {
        return mData;
    }

    public void setmData(ArrayList<MasjidModel> mData) {
        this.mData = mData;
        /*notifyDataSetChanged() berfungsi untuk mengabari adapter bahwa ada data baru yang telah diterima.
            Ketika fungsi ini dijalankan, maka Recyclerview yang didaftarkan pada adapter
        akan menampilkan data tersebut*/
        notifyDataSetChanged();
        /*ingat tambahkan selalu ini, krn kalau tidak, akan muncul pesan
            Skipped 37 frames! The application may be doing too much work on its main thread.*/
    }

    // Clean all elements of the recycler
    public void clear() {
        filterList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<MasjidModel> list) {
        filterList.addAll(list);
        notifyDataSetChanged();
    }
}
