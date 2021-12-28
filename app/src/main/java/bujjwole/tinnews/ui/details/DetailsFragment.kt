package bujjwole.tinnews.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bujjwole.tinnews.databinding.FragmentDetailsBinding
import bujjwole.tinnews.model.Article
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article: Article = DetailsFragmentArgs.fromBundle(requireArguments()).article
        binding.detailsTitleTextView.setText(article.title)
        binding.detailsAuthorTextView.setText(article.author)
        binding.detailsDateTextView.setText(article.publishedAt)
        binding.detailsDescriptionTextView.setText(article.description)
        binding.detailsContentTextView.setText(article.content)
        Picasso.get().load(article.urlToImage).into(binding.detailsImageView)

    }

}