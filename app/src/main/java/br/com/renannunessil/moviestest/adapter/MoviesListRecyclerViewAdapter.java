package br.com.renannunessil.moviestest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.renannunessil.moviestest.R;
import br.com.renannunessil.moviestest.model.MoviesResponse;

public class MoviesListRecyclerViewAdapter extends RecyclerView.Adapter<MoviesListRecyclerViewAdapter.MoviesListViewHolder> {

    private List<MoviesResponse> data;
    private MoviesListItemClickListener clickListener;
    private Context context;

    public class MoviesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView genre;
        TextView favorite;
        ImageView poster;

        MoviesListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_movie_label);
            genre = itemView.findViewById(R.id.tv_movie_genre);
            favorite = itemView.findViewById(R.id.tv_favorite_icon);
            poster = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (data != null) {
                clickListener.onItemClick(v, data.get(getAdapterPosition()));
            }
        }
    }

    public MoviesListRecyclerViewAdapter(List<MoviesResponse> data, MoviesListItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View listItem = layoutInflater.inflate(R.layout.item_movie_list, viewGroup, false);

        return new MoviesListViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListViewHolder moviesListViewHolder, int i) {
        MoviesResponse moviesResponse = data.get(i);

        ImageView poster = moviesListViewHolder.poster;
        if (moviesResponse.getMovie().getPoster() != null){
            Picasso.get().load(moviesResponse.getMovie().getPoster().getOriginalImage())
                    .fit()
                    .into(poster);
        } else {
            Picasso.get().load(R.drawable.not_available)
                    .fit()
                    .into(poster);
        }

        TextView title = moviesListViewHolder.title;
        title.setText(moviesResponse.getMovie().getTitle());

        TextView genre = moviesListViewHolder.genre;
        genre.setText(moviesResponse.getMovie().getGenreLabel());

        TextView favorite = moviesListViewHolder.favorite;
        if (moviesResponse.getMovie().isFavorite()) {
            favorite.setTextColor(context.getColor(R.color.favoriteColor));
        } else {
            favorite.setTextColor(context.getColor(R.color.unfavoriteColor));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface MoviesListItemClickListener {
        void onItemClick(View view, MoviesResponse movieResponse);
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
    }

}
