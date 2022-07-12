package com.julianawl.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.julianawl.soccernews.databinding.FragmentFavoritesBinding
import com.julianawl.soccernews.ui.MainActivity
import com.julianawl.soccernews.ui.adapter.NewsAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { NewsAdapter() }
    private var favoritesViewModel: FavoritesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        binding.rvFavorites.adapter = adapter
        loadFavoriteNews()

        return binding.root
    }

    private fun loadFavoriteNews() {
        favoritesViewModel!!.loadFavoriteNews()?.observe(viewLifecycleOwner) { news ->
            adapter.append(news)
            adapter.onFavoriteClickListener = {
                favoritesViewModel?.saveNews(it)
                loadFavoriteNews()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}