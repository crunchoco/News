package com.example.news.adapters

import android.media.AudioDescriptor
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.models.Article
import com.example.news.models.Source

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleImage: ImageView = itemView.findViewById(R.id.articleImage)
        val articleSource: TextView = itemView.findViewById(R.id.articleSource)
        val articleTitle: TextView = itemView.findViewById(R.id.articleTitle)
        val articleDescriptor: TextView = itemView.findViewById(R.id.articleDescription)
        val articleDataTime: TextView = itemView.findViewById(R.id.articleDateTime)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.apply {
            Glide.with(itemView).load(article.urlToImage).into(articleImage)
            articleSource.text = article.source?.name ?: "Unknown Source"
            articleTitle.text = article.title ?: "No Title"
            articleDescriptor.text = article.description ?: "No Description"
            articleDataTime.text = article.publishedAt ?: "No Date"

            itemView.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(article)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}