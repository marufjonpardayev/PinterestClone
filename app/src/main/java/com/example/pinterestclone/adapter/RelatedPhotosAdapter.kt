package com.example.pinterestclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterestclone.R
import com.example.pinterestclone.activity.DetailsPhotoActivity
import com.example.pinterestclone.model.WelcomeElement
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.util.logging.Handler

class RelatedPhotosAdapter(private var context: DetailsPhotoActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoList = ArrayList<WelcomeElement>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPhotos(photoList: ArrayList<WelcomeElement>) {
        this.photoList.addAll(photoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photos_view, parent, false)
        return PhotoViewHolder(view)
    }

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
        val ivMore: ImageView = view.findViewById(R.id.iv_btn_more)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val photoItem = photoList[position]
        val photoColor = photoItem.color
        val photoUrl = photoItem.urls.thumb

        if (holder is PhotoViewHolder) {
            holder.tvDescription.text = photoItem.user.bio
            Picasso.get().load(photoUrl).placeholder(ColorDrawable(Color.parseColor(photoColor)))
                .into(holder.ivPhoto)

            holder.ivPhoto.setOnClickListener {
                callDetails(position)
                context.finish()
            }
        }

    }

    private fun callDetails(position: Int) {
        val intent = Intent(context, DetailsPhotoActivity::class.java)
        val json = Gson().toJson(photoList)
        intent.putExtra("photoList", json)
        intent.putExtra("position", position)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}