package com.example.androidpractice.profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.profile.domain.IProfileRepository
import com.example.androidpractice.profile.presentation.state.ProfileState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: IProfileRepository
): ViewModel() {

    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState

    init {
        viewModelScope.launch {
            repository.observeProfile().collect {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.url = it.url
                mutableState.status = it.status

            }
        }
    }

    private class MutableProfileState: ProfileState {
        override var name: String by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var url: String by mutableStateOf("")
        override var status: String by mutableStateOf("")
    }

}