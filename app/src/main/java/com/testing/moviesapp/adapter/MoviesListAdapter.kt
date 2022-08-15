package com.testing.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testing.moviesapp.R
import com.testing.moviesapp.data.local.database.MoviesListEntity
import com.testing.moviesapp.util.emptyIfNull
import com.testing.moviesapp.util.releaseYearString
import com.testing.moviesapp.util.trimParanthesis
import kotlinx.android.synthetic.main.item_movies_list.view.*

interface OnItemCallback {

    fun onItemClick(title: String)
}

class MoviesListAdapter(private val onItemClickCallback: OnItemCallback): RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {
    private val moviesList = mutableListOf<MoviesListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(moviesList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateList(list: List<MoviesListEntity>) {
        this.moviesList.clear()
        this.moviesList.addAll(list)
        notifyDataSetChanged()
    }

    class MoviesListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: MoviesListEntity, onItemClickCallback: OnItemCallback) {
            itemView.moviesItemTitleTextView.text = model.title
            itemView.moviesReleaseYearTextView.text = model.releaseYear.releaseYearString()
            itemView.moviesItemDirectorNameTextView.text = model.directorName.emptyIfNull()
            itemView.moviesItemActorsTextView.text = model.actors.emptyIfNull().trimParanthesis()

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(model.title)
            }
        }
    }
}