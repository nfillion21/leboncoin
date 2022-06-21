package pgm.poolp.leboncoin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.leboncoin.adapters.TitleListAdapter
import pgm.poolp.leboncoin.databinding.FragmentTitlesBinding
import pgm.poolp.leboncoin.viewmodels.TitleViewModel

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
        binding.championsList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: TitleListAdapter) {
        titleViewModel.allTitles.observe(viewLifecycleOwner) { champions ->
            adapter.submitList(champions)
        }
    }
}