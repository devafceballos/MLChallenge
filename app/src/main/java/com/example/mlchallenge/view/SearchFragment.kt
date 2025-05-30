package com.example.mlchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mlchallenge.databinding.FragmentSearchBinding
import com.example.mlchallenge.viewModel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeSearchHistory()
        binding.botonBuscar.setOnClickListener {
            val query = binding.inputSearch.text.toString()
            if (query.isNotBlank()) {
                viewModel.searchArticles(query)

                val action = SearchFragmentDirections
                    .actionSearchFragmentToArticleListFragment(query)
                findNavController().navigate(action)
            }
        }
        //(requireActivity() as AppCompatActivity).supportActionBar?.title = "Resultados" Cambio de titulo toolbar
    }

    private fun observeSearchHistory() {
        viewModel.searchHistory.observe(viewLifecycleOwner) { searchHistory ->
            if(searchHistory.isEmpty()) {
                //binding.history.visibility = View.GONE
            } else {
                //binding.history.visibility = View.VISIBLE
                //adapter.submitList(historial)
            }
            //searchHistory.first().query //Nombre busqueda
        }
    }
}



