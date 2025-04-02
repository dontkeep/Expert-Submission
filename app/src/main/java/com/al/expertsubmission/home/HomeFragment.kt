package com.al.expertsubmission.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.al.core.ui.MovieAdapter
import com.al.expertsubmission.databinding.FragmentHomeBinding
import com.al.expertsubmission.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClicked = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                when(movies) {
                    is com.al.core.data.Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is com.al.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                    }
                    is com.al.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        binding.fabFavourite.setOnClickListener {
            val intent = Intent(requireContext(), Class.forName("com.al.favourite.FavouriteActivity"))
            startActivity(intent)
        }

        with(binding.rvMovies) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}