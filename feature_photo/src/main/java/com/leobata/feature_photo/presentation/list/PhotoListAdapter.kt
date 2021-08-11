package com.leobata.feature_photo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leobata.feature_photo.databinding.PhotoItemViewBinding
import com.leobata.feature_photo.model.Photo

class PhotoListAdapter(
    private val photoList: ArrayList<Photo>,
    private val itemClickListener: PhotoItemClickListener
) : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    fun updatePhotoList(newPhotoList: List<Photo>) {
        photoList.clear()
        photoList.addAll(newPhotoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PhotoItemViewBinding.inflate(inflater)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.view.photo = photoList[position]
        holder.view.photoThumb.transitionName = photoList[position].id.toString()
        holder.view.clickListener = itemClickListener
    }

    override fun getItemCount() = photoList.size

    class PhotoViewHolder(var view: PhotoItemViewBinding) : RecyclerView.ViewHolder(view.root)
}