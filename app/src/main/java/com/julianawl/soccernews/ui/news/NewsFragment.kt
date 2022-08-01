package com.julianawl.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.julianawl.soccernews.R
import com.julianawl.soccernews.databinding.FragmentNewsBinding
import com.julianawl.soccernews.ui.adapter.NewsAdapter
import com.julianawl.soccernews.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels()
    private val adapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        setNewsList()
        findNews()
        observeNews()
        observeStates()
        return binding.root
    }

    private fun findNews() {
        newsViewModel.findNews()
    }

    private fun setNewsList() {
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.itemAnimator = null
        binding.rvNews.adapter = adapter
    }

    private fun observeNews() {
        newsViewModel.getNews().observe(viewLifecycleOwner) { news ->
            adapter.append(news)
            adapter.onFavoriteClickListener = {
                newsViewModel.saveNews(it)
            }
        }
    }

    private fun observeStates() {
        newsViewModel.getState().observe(
            viewLifecycleOwner
        ) { state ->
            when (state!!) {
                State.DOING -> binding.srlNews.isRefreshing = true
                State.DONE -> binding.srlNews.isRefreshing = false
                State.ERROR -> {
                    binding.srlNews.isRefreshing = false
                    Snackbar.make(binding.srlNews, R.string.error_network, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.srlNews.setOnRefreshListener { newsViewModel.findNews() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}