package com.example.crownstack_project.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crownstack_project.R
import com.example.crownstack_project.model.Results
import com.example.crownstack_project.viewmodel.SongsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    private lateinit var songsViewModel: SongsViewModel
    private lateinit var songsAdapter: SongsAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        setUpObservers()
    }
    private fun initComponents(){
        songsViewModel = ViewModelProvider(this).get(SongsViewModel::class.java)
        songsViewModel.getSongsData("songs.txt")
    }

    private fun setUpObservers(){
        songsViewModel.contentLiveData.observe(this, Observer {content->
            setRecyclerView(content.results)
        })
    }
    private fun setRecyclerView(dataList: List<Results>) {
        layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rv_songs_list.addItemDecoration(itemDecorator)
        songsAdapter = SongsAdapter(dataList,this)
        rv_songs_list.layoutManager = layoutManager
        rv_songs_list.adapter = songsAdapter


    }
}