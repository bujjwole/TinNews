package bujjwole.tinnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import bujjwole.tinnews.databinding.FragmentHomeBinding
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.repository.NewsRepository
import bujjwole.tinnews.repository.NewsViewModelFactory
import com.yuyakaido.android.cardstackview.*

class HomeFragment: Fragment(), CardStackListener{

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var layoutManager: CardStackLayoutManager
    private lateinit var  articles: List<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeAdapter: HomeCardSwipeAdapter = HomeCardSwipeAdapter()
        layoutManager = CardStackLayoutManager(requireContext(), this)
        layoutManager.setStackFrom(StackFrom.Top)
        binding.homeCardStackView.setLayoutManager(layoutManager)
        binding.homeCardStackView.setAdapter(swipeAdapter)

        binding.homeLikeButton.setOnClickListener { swipeCard(Direction.Right) }
        binding.homeUnlikeButton.setOnClickListener { swipeCard(Direction.Left) }

        val repository: NewsRepository = NewsRepository(getContext())
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository)).get(HomeViewModel::class.java)
        viewModel.setCountryInput("us")
        viewModel.getTopHeadlines().observe(getViewLifecycleOwner(), {
            if (it != null) {
                articles = it.articles
                swipeAdapter.setArticles(articles)
            }
        })
    }

    private fun swipeCard(direction: Direction){
        val setting: SwipeAnimationSetting = SwipeAnimationSetting.Builder()
            .setDirection(direction).setDuration(Duration.Normal.duration).build()
        layoutManager.setSwipeAnimationSetting(setting)
        binding.homeCardStackView.swipe()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        val article: Article = articles.get(layoutManager.getTopPosition() - 1)
        viewModel.setFavoriteArticleInput(article)
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

}