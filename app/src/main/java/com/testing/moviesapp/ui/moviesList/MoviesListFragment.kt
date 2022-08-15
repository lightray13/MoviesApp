package com.testing.moviesapp.ui.moviesList

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.testing.moviesapp.adapter.MoviesListAdapter
import com.testing.moviesapp.adapter.OnItemCallback
import com.testing.moviesapp.databinding.FragmentMoviesBinding
import com.testing.moviesapp.util.doOnChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*

@AndroidEntryPoint
class MoviesListFragment : Fragment(), OnItemCallback {
    private lateinit var binding: FragmentMoviesBinding

    private val viewModel: MoviesListViewModel by viewModels()
    private var moviesListAdapter = MoviesListAdapter(this)

    companion object {

        fun newInstance(): MoviesListFragment {
            return MoviesListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@MoviesListFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViews()
        viewModel.loadMoviesFromApi()
    }

    private fun initializeViews() {
        moviesListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesListAdapter
        }
    }


    private fun observeViewModel() {
        viewModel.isLoading.doOnChange(this) {
            moviesListLoading.visibility = if (viewModel.isListEmpty() && it) View.VISIBLE else View.GONE

            if (it) moviesListErrorView.visibility = View.GONE
        }

        viewModel.moviesListData.doOnChange(this) {
            moviesListAdapter.updateList(it)

            moviesListErrorView.visibility = if (viewModel.isListEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onItemClick(title: String) {
        showDialog(title)
    }

    private fun showDialog(title: String) {
        val builder = AlertDialog.Builder(activity)
        with(builder) {
            setTitle("$title был нажат")
            setPositiveButton("OK") { _, _ -> }
            setNegativeButton("Cancel") { _, _ -> }
            show()
        }
    }
}