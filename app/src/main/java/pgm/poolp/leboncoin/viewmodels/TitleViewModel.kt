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
    titleRepository: TitleRepository
) : ViewModel() {

    val allTitles: LiveData<List<Title>> = titleRepository.allTitles.asLiveData()
}