package pgm.poolp.leboncoin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pgm.poolp.leboncoin.viewmodels.TitleViewModel
import pgm.poolp.ugdata.adapters.ChampionListAdapter
import pgm.poolp.ugdata.databinding.FragmentChampionsBinding
import pgm.poolp.ugdata.viewmodels.ChampionViewModel

@AndroidEntryPoint
class TitleListFragment : Fragment() {

    private val titleViewModel: TitleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChampionsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ChampionListAdapter()
        binding.championsList.adapter = adapter
        subscribeUi(adapter)

        //setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: ChampionListAdapter) {
        championViewModel.allChampions.observe(viewLifecycleOwner) { champions ->
            adapter.submitList(champions)
        }
    }
}
