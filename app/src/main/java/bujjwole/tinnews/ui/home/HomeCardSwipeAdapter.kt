package bujjwole.tinnews.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bujjwole.tinnews.R
import bujjwole.tinnews.databinding.SwipeNewsCardBinding
import bujjwole.tinnews.model.Article
import com.squareup.picasso.Picasso

class HomeCardSwipeAdapter: RecyclerView.Adapter<HomeCardSwipeAdapter.CardSwipeViewHolder>() {

    private val articles: MutableList<Article> = ArrayList<Article>()

    fun setArticles(newsList: List<Article>){
        articles.clear()
        articles.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardSwipeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.swipe_news_card, parent, false)
        return CardSwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardSwipeViewHolder, position: Int) {
        val article = articles.get(position)
        holder.titleTextView.setText(article.title)
        holder.descriptionTextView.setText(article.description)
        Picasso.get().load(article.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class CardSwipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: SwipeNewsCardBinding = SwipeNewsCardBinding.bind(itemView)
        val imageView: ImageView = binding.swipeCardImageView
        val titleTextView: TextView = binding.swipeCardTitle
        val descriptionTextView: TextView = binding.swipeCardDescription

    }
}