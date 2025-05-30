package com.example.mlchallenge.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mlchallenge.R
import com.example.mlchallenge.databinding.FragmentArticleListBinding
import com.example.mlchallenge.viewModel.ArticleListViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleListFragment : Fragment() {

    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleListViewModel by viewModel()
    private val args: ArticleListFragmentArgs by navArgs()

    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = ArticleAdapter { article ->
            val action = ArticleListFragmentDirections
                .actionArticleListFragmentToDetailFragment(article)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            Log.d("ArticleListFragment", "submitList con ${articles.size} artículos")
            adapter.submitList(articles)
        }

        viewModel.fallbackTriggered.observe(viewLifecycleOwner) { fallback ->
            if (fallback) {
                Snackbar.make(
                    binding.root,
                    "La busqueda fallo. Intenta nuevamente",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Reintentar") {
                    viewModel.retryLastSearch()
                }.show()
            }
        }
        viewModel.searchArticles(args.query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
