package com.example.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemArticleListBinding
import com.example.news.models.Article

class ArticlesAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val articleList: ArrayList<Article> = arrayListOf()

    var callback: (() -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ArticleViewHolder(
                ItemArticleListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            (holder as ArticleViewHolder).bind(articleList[position])
    }

    fun setItems(items: List<Article>, callback: (() -> Unit)? = null) {
        this.callback = callback
        articleList.clear()
        articleList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(var itemBinding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {

            itemBinding.tvTitle.text = article.title
            itemBinding.tvDescription.text = article.description
        }
    }


}