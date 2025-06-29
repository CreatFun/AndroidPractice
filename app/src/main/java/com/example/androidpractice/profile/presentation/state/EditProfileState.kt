package com.example.androidpractice.profile.presentation.state

import android.net.Uri

interface EditProfileState {
    val photoUri: Uri
    val name: String
    val url: String
    val status: String
    val isNeedToShowPermission: Boolean
    val isNeedToShowSelect: Boolean
}