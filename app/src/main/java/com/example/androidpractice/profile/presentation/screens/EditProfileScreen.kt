package com.example.androidpractice.profile.presentation.screens

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil3.compose.AsyncImage
import com.example.androidpractice.R
import com.example.androidpractice.profile.presentation.viewModel.EditProfileViewModel
import com.example.androidpractice.profile.presentation.viewModel.ProfileViewModel
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.back
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.util.Date

@Parcelize
class EditProfileScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {

        val navigation = LocalStackNavigation.current
        val context = LocalContext.current

        val viewModel = koinViewModel<EditProfileViewModel>()
        val state = viewModel.viewState

        var imageUri by remember { mutableStateOf<Uri?>(null) }

        val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                viewModel.onImageSelected(uri)
            }

        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (!isGranted) {
                    val dialog = AlertDialog.Builder(context)
                        .setMessage("Пожалуйста, предоставьте необходимые разрешения")
                        .setCancelable(false)
                        .setPositiveButton("OK") { _, _ ->
                            navigation.back()
                        }

                    dialog.show()
                }
                viewModel.onPermissionClosed()
            }

        val mGetContent = rememberLauncherForActivityResult<Uri, Boolean>(
            ActivityResultContracts.TakePicture()
        ) { success: Boolean ->
            if (success) {
                viewModel.onImageSelected(imageUri)
            }
        }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.edit_profile))
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable { navigation.back() }
                        )
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    navigation.back()
                                    viewModel.onDoneClicked()
                                }
                        )
                    },
                    modifier = Modifier.shadow(elevation = 1.dp)
                )
            }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = state.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(128.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.onAvatarClicked() },
                    error = painterResource(R.drawable.ic_launcher_background)
                )
                TextField(
                    value = state.name,
                    onValueChange = { viewModel.onNameChanged(it) },
                    label = { Text(stringResource(R.string.name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                TextField(
                    value = state.url,
                    onValueChange = { viewModel.onUrlChanged(it) },
                    label = { Text(stringResource(R.string.link)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                TextField(
                    value = state.status,
                    onValueChange = { viewModel.onStatusChanged(it) },
                    label = { Text(stringResource(R.string.status)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }

        if (state.isNeedToShowPermission) {
            LaunchedEffect(Unit) {
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                }
            }
        }

        fun onCameraSelected() {
            val baseDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            )
            val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
            imageUri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                pictureFile
            )
            imageUri?.let { mGetContent.launch(it) }
        }

        if (state.isNeedToShowSelect) {
            Dialog(onDismissRequest = { viewModel.onSelectDismiss() }) {
                Surface(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = stringResource(R.string.camera),
                            Modifier.clickable {
                                onCameraSelected()
                                viewModel.onSelectDismiss()
                            }
                        )
                        Text(text = stringResource(R.string.gallery),
                            Modifier.clickable {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                viewModel.onSelectDismiss()
                            })
                    }
                }
            }
        }
    }

}