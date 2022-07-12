package com.julianawl.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.julianawl.soccernews.databinding.FragmentFavoritesBinding
import com.julianawl.soccernews.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { NewsAdapter() }
    private val favoritesViewModel by viewModel<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        favoritesViewModel.loadFavoriteNews()
        loadFavoriteNews()

        return binding.root
    }

    private fun loadFavoriteNews() {
        favoritesViewModel.getNews().observe(viewLifecycleOwner) { localNews ->
            binding.rvFavorites.layoutManager = LinearLayoutManager(context)
            binding.rvFavorites.adapter = adapter
            adapter.append(localNews)
            adapter.onFavoriteClickListener = {
                favoritesViewModel.saveNews(it)
                favoritesViewModel.loadFavoriteNews()
                this.loadFavoriteNews()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}