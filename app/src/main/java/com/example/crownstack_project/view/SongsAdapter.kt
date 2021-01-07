package com.example.crownstack_project.view

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crownstack_project.R
import com.example.crownstack_project.model.Results


class SongsAdapter(private var songsList : List<Results>,private var context:Context):RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var trackName : TextView = view.findViewById(R.id.track_name)
        var collectionName : TextView = view.findViewById(R.id.collection_name)
        var artistName :TextView = view.findViewById(R.id.artist_name)
        var imageView : ImageView = view.findViewById(R.id.song_imageview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songs = songsList[position]
        holder.trackName.text = songs.trackName
        holder.collectionName.text = songs.collectionName
        holder.artistName.text = songs.artistName
        Glide.with(context).load(songs.artworkUrl100).into(holder.imageView)
        holder.itemView.setOnClickListener {
            Log.e("item clicked"," :"+songs.artistName)
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_view, null)
            var track_value = view.findViewById<TextView>(R.id.tv_track_value)
            var artist_name = view.findViewById<TextView>(R.id.tv_artist_value)
            var collection_name = view.findViewById<TextView>(R.id.tv_collection_name_value)
            var collection_price = view.findViewById<TextView>(R.id.tv_collection_price_value)
            var track_price = view.findViewById<TextView>(R.id.tv_track_price_value)
            track_value.text = songs.trackName
            artist_name.text = songs.artistName
            collection_name.text = songs.collectionName
            collection_price.text = songs.collectionPrice.toString()
            track_price.text = songs.trackPrice.toString()
            builder.setView(view)
            val alert : AlertDialog = builder.create()
            alert.show()
        }
    }
}