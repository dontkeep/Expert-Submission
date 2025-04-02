package com.al.expertsubmission.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.al.core.domain.model.Movies
import com.al.expertsubmission.R
import com.al.expertsubmission.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val movie: Movies? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, Movies::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if (movie != null) {
            setupMovieDetails(movie)
            viewModel.setInitialFavoriteState(movie.isFavorite)
        }

        binding.fabFavorite.setOnClickListener {
            movie?.let {
                viewModel.toggleFavorite(it)
            }
        }

        viewModel.favoriteState.observe(this) { isFav ->
            updateFavoriteState(isFav)
        }
    }


    private fun setupMovieDetails(movie: Movies) {
        binding.tvTitle.text = movie.title
        binding.tvReleaseDate.text = buildString {
            append("Release Date: ")
            append(movie.releaseDate)
        }
        binding.tvRating.text = buildString {
            append("Rating: ")
            append(movie.voteAverage ?: "N/A")
        }
        binding.tvOverview.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
            .placeholder(com.al.core.R.drawable.movie_placeholder)
            .into(binding.ivMoviePoster)

        updateFavoriteState(movie.isFavorite)
    }

    private fun updateFavoriteState(isFavorite: Boolean) {
        binding.fabFavorite.setImageResource(
            if (isFavorite) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24
        )
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}