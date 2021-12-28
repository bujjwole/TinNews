package bujjwole.tinnews.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bujjwole.tinnews.R
import bujjwole.tinnews.databinding.SearchNewsItemBinding
import bujjwole.tinnews.model.Article
import com.squareup.picasso.Picasso

class SearchNewsAdapter: RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>(){

    interface ItemCallback {
        fun onOpenDetails(article: Article)
    }

    private val articles: MutableList<Article> = ArrayList<Article>()
    private lateinit var itemCallback: ItemCallback

    fun setItemCallback(thisCallback: ItemCallback){
        itemCallback = thisCallback
    }

    fun setArticles(newsList: List<Article>){
        articles.clear()
        articles.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_news_item, parent, false)
        return SearchNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchNewsViewHolder, position: Int) {
        val article = articles.get(position)
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp)
        holder.itemTitleTextView.setText(article.title)
        Picasso.get().load(article.urlToImage).into(holder.itemImageView)
        holder.itemView.setOnClickListener{itemCallback.onOpenDetails(article)}
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class SearchNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: SearchNewsItemBinding = SearchNewsItemBinding.bind(itemView)
        val favoriteImageView: ImageView = binding.searchItemFavorite
        val itemImageView: ImageView = binding.searchItemImage
        val itemTitleTextView: TextView = binding.searchItemTitle

    }
}