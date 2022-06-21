package pgm.poolp.leboncoin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pgm.poolp.leboncoin.data.Title
import pgm.poolp.leboncoin.data.TitleRepository
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject internal constructor(
    championRepository: TitleRepository
) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allChampions: LiveData<List<Title>> = championRepository.allTitles.asLiveData()
}