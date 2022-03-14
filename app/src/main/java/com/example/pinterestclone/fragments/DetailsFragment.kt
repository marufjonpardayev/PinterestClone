package com.example.pinterestclone.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterestclone.R
import com.example.pinterestclone.activity.DetailsPhotoActivity
import com.example.pinterestclone.adapter.RelatedPhotosAdapter
import com.example.pinterestclone.model.WelcomeElement
import com.example.pinterestclone.network.RetrofitHttp
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsFragment(var photoItem: WelcomeElement): Fragment() {
    private lateinit var adapter: RelatedPhotosAdapter
    private lateinit var tvRelated: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RelatedPhotosAdapter(requireContext() as DetailsPhotoActivity)
        apiRelatedPhotos(photoItem.id)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.detail_photo, container, false)
        initViews(view)
        return view
    }


    @SuppressLint("SetTextI18n")
    private fun initViews(view: View) {
        val ivBtnBack = view.findViewById<ImageView>(R.id.iv_btn_back)
        val ivBtnMore = view.findViewById<ImageView>(R.id.iv_btn_more)
        val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
        val ivProfile = view.findViewById<ImageView>(R.id.iv_profile)
        val tvFullName = view.findViewById<TextView>(R.id.tv_fullName)
        val tvFollowers = view.findViewById<TextView>(R.id.tv_followers)
        tvRelated = view.findViewById(R.id.tv_related)

        val rvDetails = view.findViewById<RecyclerView>(R.id.rv_details)
        rvDetails.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvDetails.adapter = adapter

        Picasso.get().load(photoItem.urls.small)
            .placeholder(ColorDrawable(Color.parseColor(photoItem.color))).into(ivPhoto)


        tvFullName.text = photoItem.user.name
        tvFollowers.text = "${photoItem.user.totalPhotos} Followers"


        ivBtnBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun apiRelatedPhotos(id: String) {
        RetrofitHttp.photoService.getRelatedPhotos(id).enqueue(object : Callback<RelatedPhotos> {
            override fun onResponse(call: Call<RelatedPhotos>, response: Response<RelatedPhotos>) {
                val photoList: ArrayList<WelcomeElement> = response.body()!!.results!!
                if (photoList.size > 0) {
                    adapter.addPhotos(photoList)
                } else {
                    tvRelated.text = "Related images has not found..."
                }
            }

            override fun onFailure(call: Call<RelatedPhotos>, t: Throwable) {
                Log.e("@@@", t.message.toString())
            }
        })
    }


}