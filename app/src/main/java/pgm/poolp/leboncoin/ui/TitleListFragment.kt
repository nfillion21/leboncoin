package pgm.poolp.leboncoin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.leboncoin.adapters.TitleListAdapter
import pgm.poolp.leboncoin.databinding.FragmentTitlesBinding
import pgm.poolp.leboncoin.utilities.TITLE_LIST_URL
import pgm.poolp.leboncoin.viewmodels.TitleViewModel
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker.Companion.TITLES_KEY_URL

@AndroidEntryPoint
class TitleListFragment : Fragment() {

    private val titleViewModel: TitleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTitlesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = TitleListAdapter()
        binding.titlesList.adapter = adapter
        subscribeUi(adapter)

        context?.let {
            val swipeRefreshLayout = binding.swipeRefreshListLayout
            swipeRefreshLayout.setOnRefreshListener {
                val workManager = WorkManager.getInstance(it)
                val requestTitles = OneTimeWorkRequestBuilder<TitleDatabaseWorker>()
                    .setInputData(workDataOf(TITLES_KEY_URL to TITLE_LIST_URL))
                    .build()
                workManager.enqueue(requestTitles)
                swipeRefreshLayout.isRefreshing = false
            }
        }

        return binding.root
    }

    private fun subscribeUi(adapter: TitleListAdapter) {
        titleViewModel.allTitles.observe(viewLifecycleOwner) { titles ->
            adapter.submitList(titles)
        }
    }
}