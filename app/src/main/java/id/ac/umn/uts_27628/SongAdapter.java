package id.ac.umn.uts_27628;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemVideoViewHolder> {
    private LinkedList<SumberLagu> mDaftarLagu;
    private LayoutInflater mInflater;
    private Context mContext;

    public SongAdapter(Context context, LinkedList<SumberLagu> daftarLagu){
        this.mContext = context;
        this.mDaftarLagu = daftarLagu;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.song_item_layout, parent, false);
        return new ItemVideoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVideoViewHolder holder, int position) {
        SumberLagu mSumberLagu = mDaftarLagu.get(position);
        holder.tvJudul.setText(mSumberLagu.getJudul());
        holder.tvArtis.setText(mSumberLagu.getArtis());
        holder.tvUri.setText(mSumberLagu.getUri());
    }

    @Override
    public int getItemCount() {
        return mDaftarLagu.size();
    }

    public class ItemVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView kotakSong;
        private TextView tvJudul;
        private TextView tvArtis;
        private TextView tvUri;
        private SongAdapter mAdapter;
        private int mPosisi;
        private SumberLagu mSumberLagu;

        public ItemVideoViewHolder(@NonNull View itemView, SongAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            kotakSong = (ImageView) itemView.findViewById(R.id.kotakSong);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tvArtis = (TextView) itemView.findViewById(R.id.tvArtis);
            tvUri = (TextView) itemView.findViewById(R.id.tvUri);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mSumberLagu = mDaftarLagu.get(mPosisi);
            mPosisi = getLayoutPosition();
            Intent detilInten = new Intent(mContext, SongDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DetilLagu", mDaftarLagu);
            bundle.putSerializable("Position", mPosisi);
            detilInten.putExtras(bundle);
            mContext.startActivity(detilInten);
        }
    }
}

