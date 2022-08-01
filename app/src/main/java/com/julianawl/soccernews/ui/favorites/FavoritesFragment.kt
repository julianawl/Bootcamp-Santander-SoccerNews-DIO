package com.julianawl.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.julianawl.soccernews.databinding.FragmentFavoritesBinding
import com.julianawl.soccernews.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { NewsAdapter() }
    private val favoritesViewModel: FavoritesViewModel by viewModels()

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