package com.leobata.feature_photo.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leobata.feature_photo.databinding.PhotoCommentItemViewBinding
import com.leobata.feature_photo.model.Comment


class CommentListAdapter(
    private val commentList: ArrayList<Comment>
) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {

    fun updateCommentList(newCommentList: List<Comment>) {
        commentList.clear()
        commentList.addAll(newCommentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PhotoCommentItemViewBinding.inflate(inflater, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.view.comment = commentList[position]
    }

    override fun getItemCount() = commentList.size

    override fun getItemId(position: Int) = commentList[position].id

    class CommentViewHolder(var view: PhotoCommentItemViewBinding) :
        RecyclerView.ViewHolder(view.root)
}