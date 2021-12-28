package bujjwole.tinnews.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import bujjwole.tinnews.databinding.FragmentSearchBinding
import bujjwole.tinnews.model.Article
import bujjwole.tinnews.repository.NewsRepository
import bujjwole.tinnews.repository.NewsViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsAdapter: SearchNewsAdapter = SearchNewsAdapter()
        newsAdapter.setItemCallback(object: SearchNewsAdapter.ItemCallback{
            override fun onOpenDetails(article: Article) {
                val direction: SearchFragmentDirections.ActionNavigationSearchToNavigationDetails = SearchFragmentDirections.actionNavigationSearchToNavigationDetails(article)
                NavHostFragment.findNavController(this@SearchFragment).navigate(direction)
            }

        })
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager)
        binding.newsResultsRecyclerView.setAdapter(newsAdapter)
        binding.newsSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.setSearchInput(query)
                }
                binding.newsSearchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        val repository: NewsRepository = NewsRepository(getContext())
        viewModel = ViewModelProvider(this, NewsViewModelFactory(repository)).get(SearchViewModel::class.java)

        viewModel.searchNews().observe(getViewLifecycleOwner(), {
            if (it != null){
                newsAdapter.setArticles(it.articles)
            }
        })
    }

}