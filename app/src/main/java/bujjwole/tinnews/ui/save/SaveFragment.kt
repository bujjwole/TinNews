package bujjwole.tinnews.ui.save

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import bujjwole.tinnews.databinding.FragmentSaveBinding
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.repository.NewsRepository
import bujjwole.tinnews.repository.NewsViewModelFactory

class SaveFragment : Fragment() {

    private lateinit var binding: FragmentSaveBinding
    private lateinit var viewModel: SaveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSaveBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val savedNewsAdapter: SavedNewsAdapter = SavedNewsAdapter()
        savedNewsAdapter.setItemCallback(object: SavedNewsAdapter.ItemCallback{
            override fun onOpenDetails(article: Article) {
                val direction: SaveFragmentDirections.ActionNavigationSaveToNavigationDetails = SaveFragmentDirections.actionNavigationSaveToNavigationDetails(article)
                NavHostFragment.findNavController(this@SaveFragment).navigate(direction)
            }

            override fun onRemoveFavorite(article: Article) {
                viewModel.deleteSavedArticle(article)
            }
        })
        binding.newsSavedRecyclerView.setAdapter(savedNewsAdapter)
        binding.newsSavedRecyclerView.setLayoutManager(LinearLayoutManager(requireContext()))

        val repository: NewsRepository = NewsRepository(getContext())
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository)).get(SaveViewModel::class.java)
        viewModel.getAllSavedArticles().observe(getViewLifecycleOwner(), {
            if (it != null) {
                savedNewsAdapter.setArticles(it)
            }
        })
    }

}