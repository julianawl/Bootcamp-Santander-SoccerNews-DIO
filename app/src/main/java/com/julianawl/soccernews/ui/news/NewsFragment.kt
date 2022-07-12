package com.julianawl.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.julianawl.soccernews.R
import com.julianawl.soccernews.databinding.FragmentNewsBinding
import com.julianawl.soccernews.ui.adapter.NewsAdapter
import com.julianawl.soccernews.utils.State


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private var newsViewModel: NewsViewModel? = null

    private val adapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        newsViewModel?.findNews()
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvNews.layoutManager = LinearLayoutManager(context)

        observeNews()
        observeStates()
        return root
    }

    private fun observeNews() {
        newsViewModel!!.getNews().observe(viewLifecycleOwner) { news ->
            binding.rvNews.adapter = adapter
            adapter.append(news)
            adapter.onFavoriteClickListener = {
                newsViewModel?.saveNews(it)
            }
        }
    }

    private fun observeStates() {
        newsViewModel!!.getState().observe(
            viewLifecycleOwner
        ) { state: State? ->
            when (state) {
                State.DOING -> binding.srlNews.isRefreshing = true
                State.DONE -> binding.srlNews.isRefreshing = false
                State.ERROR -> {
                    binding.srlNews.isRefreshing = false
                    Snackbar.make(binding.srlNews, R.string.error_network, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
        binding.srlNews.setOnRefreshListener { newsViewModel?.findNews() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}