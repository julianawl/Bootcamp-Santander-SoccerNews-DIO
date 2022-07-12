package com.julianawl.soccernews.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.julianawl.soccernews.databinding.NewsItemBinding
import com.julianawl.soccernews.domain.News
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val news: MutableList<News> = mutableListOf()
    var onFavoriteClickListener: (news: News) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = this.news[position]
        holder.binding.tvNewsTitle.text = item.title
        holder.binding.tvNewsDescription.text = item.description
        Picasso.get().load(item.image)
            .fit()
            .into(holder.binding.ivThumbnail)
        holder.binding.btnNewsOpen.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.link)
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.imgBtnNewsShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, item.link)
            holder.itemView.context.startActivity(Intent.createChooser(intent,"Share"))
        }
        holder.binding.toggleNewsFavorite.setOnClickListener {
            item.favorite = !item.favorite
            onFavoriteClickListener(item)
            notifyItemChanged(position)
        }
        holder.binding.toggleNewsFavorite.isChecked = item.favorite
    }

    override fun getItemCount(): Int = news.size

    fun append(news: List<News>){
        this.news.clear()
        this.news.addAll(news)
        notifyItemRangeInserted(0, news.size)
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NewsItemBinding.bind(view)
    }
}