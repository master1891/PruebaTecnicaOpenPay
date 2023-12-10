package com.nels.master.pruebaopenpay.features.uploadfeature

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nels.master.pruebaopenpay.features.uploadfeature.viewmodels.UploafFileViewModel

@Composable
fun UploadScreen(uploafFileViewModel: UploafFileViewModel) {

    var selectedImagesUri by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }

    if (uploafFileViewModel.statusUpload.status == UploafFileViewModel.StatusUpload.Uploaded){
        Toast.makeText(LocalContext.current,"Archivos subidos con éxito",Toast.LENGTH_LONG).show()
        selectedImagesUri = emptyList()
    }

    val photoPickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = {
                selectedImagesUri = it
            }
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Text(
                text = "Escoje una foto",
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Header(
                callbackImageSelected = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
                sendImagesCallback = {
                    if (selectedImagesUri.isNotEmpty()){
                        uploafFileViewModel.uploadImages(selectedImagesUri)
                    }
                }
            )
            LazyColumn {
                items(selectedImagesUri) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp),
                        model = it,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }

        }
    }
}

@Composable
fun Header(callbackImageSelected: () -> Unit, sendImagesCallback: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                callbackImageSelected()
            }) {
            Text(text = "Seleccione")
        }
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                sendImagesCallback()
            }
        ) {
            Text(text = "Subor fotos")
        }
    }
}




