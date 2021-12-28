package bujjwole.tinnews.ui.save

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bujjwole.tinnews.R
import bujjwole.tinnews.databinding.SavedNewsItemBinding
import bujjwole.tinnews.model.Article

class SavedNewsAdapter : RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>(){

    interface ItemCallback{
        fun onOpenDetails(article: Article)

        fun onRemoveFavorite(article: Article)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view: View = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item, parent, false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val article = articles.get(position)
        holder.authorTextView.setText(article.author)
        holder.descriptionTextView.setText(article.description)
        holder.favoriteIcon.setOnClickListener{itemCallback.onRemoveFavorite(article)}
        holder.itemView.setOnClickListener{itemCallback.onOpenDetails(article)}
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class SavedNewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: SavedNewsItemBinding = SavedNewsItemBinding.bind(itemView)
        val authorTextView: TextView = binding.savedItemAuthorContent
        val descriptionTextView: TextView = binding.savedItemDescriptionContent
        val favoriteIcon: ImageView = binding.savedItemFavoriteImageView

    }
}