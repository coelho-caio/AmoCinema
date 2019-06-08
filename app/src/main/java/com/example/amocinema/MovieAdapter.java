package com.example.amocinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PosterViewHolder> {


    private ArrayList<Filme> movies;
    private Context context;
    OnItemClickListener mOnItemClickLister;

    public void setmOnItemClickLister(OnItemClickListener mOnItemClickLister) {
        this.mOnItemClickLister = mOnItemClickLister;
    }




    void setMovies(ArrayList<Filme> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context, ArrayList<Filme> movies) {
        this.context = context;
        this.movies = movies;

    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_amo_filme, parent, false);
        return new PosterViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder posterViewHolder, int i) {
        Picasso.with(context).load(getPoster(i)).into(posterViewHolder.imageView);
        String tituloDoFilme = movies.get(i).getTitulo();
        posterViewHolder.titulo.setText(tituloDoFilme);
    }

    private String getPoster(int position) {
        return NetworkUtils.URL_POSTER + NetworkUtils.TAMANHO + movies.get(position).getPoster();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView titulo;

        PosterViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.iv_item_amo_filmes);
            titulo = view.findViewById(R.id.tv_item_amo_filmes);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnItemClickLister.OnItemCLick(position);

        }
    }
}