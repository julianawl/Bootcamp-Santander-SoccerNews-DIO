package com.julianawl.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.julianawl.soccernews.MainActivity
import com.julianawl.soccernews.databinding.FragmentFavoritesBinding
import com.julianawl.soccernews.ui.adapter.NewsAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupFavoriteList()

        return root
    }

    private fun setupFavoriteList() {
        val activity = activity as MainActivity
        val favoriteNews = activity.db!!.newsDao().loadAllByFavorite(true)
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        binding.rvFavorites.adapter = adapter
        adapter.append(favoriteNews)
        adapter.onFavoriteClickListener = {
            activity.db!!.newsDao().save(it)
            setupFavoriteList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}