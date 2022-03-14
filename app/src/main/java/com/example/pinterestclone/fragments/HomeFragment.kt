package com.example.pinterestclone.fragments

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestclone.R
import com.example.pinterestclone.adapter.PhotosAdapter
import com.example.pinterestclone.model.Welcome
import com.example.pinterestclone.model.WelcomeElement
import com.example.pinterestclone.network.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var adapter: PhotosAdapter
    private var currentPage = 1
    private var perPage = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        initView(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiPhotoList()
    }

    private fun initView(view: View) {
        val rvHome = view.findViewById<RecyclerView>(R.id.rv_home)
        rvHome.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        refreshAdapter(rvHome)
        rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!rvHome.canScrollVertically(1)) {
                    apiPhotoList()
                }
            }
        })
    }


    private fun refreshAdapter(recyclerView: RecyclerView) {
        adapter = PhotosAdapter(requireContext())
        recyclerView.adapter = adapter
    }

    private fun apiPhotoList() {
        RetrofitHttp.photoService.getPhotos(++currentPage, perPage)
            .enqueue(object : Callback<Welcome> {
                override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                    adapter.addPhotos(response.body()!!)
                }

                override fun onFailure(call: Call<Welcome>, t: Throwable) {
                    Log.e("@@@", t.message.toString())
                    Log.e("@@@", t.toString())
                }
            })
    }




}