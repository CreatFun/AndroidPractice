package com.example.androidpractice.profile.presentation.state

import android.net.Uri

interface ProfileState {
    val name: String
    val photoUri: Uri
    val url: String
    val status: String
}