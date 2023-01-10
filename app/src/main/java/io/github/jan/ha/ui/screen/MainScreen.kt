package io.github.jan.ha.ui.screen

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import io.github.jan.ha.MainViewModel
import io.github.jan.ha.ui.components.MLKitTextRecognition
import io.github.jan.ha.ui.dialog.ResponseDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val permission = rememberPermissionState(Manifest.permission.CAMERA)
    val response by viewModel.response.collectAsState()
    val loading by viewModel.loading.collectAsState()
    if(permission.status.isGranted) {
        MLKitTextRecognition(loading) {
            viewModel.retrieveCompletion(it)
        }
        if(response != null) {
            ResponseDialog(response!!) {
                viewModel.response.value = null
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Button(onClick = { permission.launchPermissionRequest()}) {
                Text("Request permission")
            }
        }
    }
}