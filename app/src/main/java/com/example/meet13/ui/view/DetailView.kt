package com.example.meet13.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meet13.model.Mahasiswa
import com.example.meet13.ui.navigation.DestinasiNavigasi
import com.example.meet13.ui.viewmodel.DetailUiState
import com.example.meet13.ui.viewmodel.DetailViewModel
import com.example.pam11.ui.ViewModel.PenyediaViewModel


object DestinasiDetail : DestinasiNavigasi{
    override val route = "detail"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
    override val title: String = "Detail Mhs"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    nim: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (String) -> Unit = {},
    navigateBack:()->Unit,
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateBack,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Mahasiswa")
            }
        },
    ){ innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()

        BodyDetail(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState,
            retryAction = { viewModel.getDetailMahasiswa() }
        )

    }
}



@Composable
fun BodyDetail(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    retryAction: () -> Unit = {}
) {
    when (detailUiState) {
        is DetailUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is DetailUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetail(mahasiswa = detailUiState.mahasiswa)
            }
        }
        is DetailUiState.Error -> {
            OnError(
                retryAction = retryAction,
                modifier = Modifier, message = String()
            )
        }
        else -> {
            Text("Unexpected state encountered")
        }
    }

}




@Composable
fun ItemDetail(
    mahasiswa : Mahasiswa
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetail(judul = "NIM", isinya = mahasiswa.nim)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Nama", isinya = mahasiswa.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Alamat", isinya = mahasiswa.alamat)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Jenis Kelamin", isinya = mahasiswa.jenis_kelamin)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Kelas", isinya = mahasiswa.kelas)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Angkatan", isinya = mahasiswa.angkatan)
            ComponentDetail(judul = "Dosen1", isinya = mahasiswa.dosen1)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetail(judul = "Dosen2", isinya = mahasiswa.dosen2)
            ComponentDetail(judul = "JudulSkripsi", isinya = mahasiswa.judulskripsi)


        }
    }
}











@Composable
fun ComponentDetail(
    judul: String,
    isinya: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}