package com.example.meet13.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet13.model.Mahasiswa
import com.example.meet13.repository.MahasiswaRepository
import com.example.meet13.ui.view.DestinasiDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}


class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
) : ViewModel() {

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])


    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    init {
        getDetailMahasiswa()
    }

    fun getDetailMahasiswa() {
        viewModelScope.launch {
            try {
                _detailUiState.value = DetailUiState.Loading
                val mahasiswa = mhs.getMahasiswabyNim(_nim)

                if (mahasiswa != null) {
                    _detailUiState.value = DetailUiState.Success(mahasiswa = Mahasiswa())
                } else {
                    _detailUiState.value = DetailUiState.Error
                }
            } catch (e: Exception) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}



fun Mahasiswa.toDetailUiEvent(): MahasiswaEvent{
    return MahasiswaEvent(
        nim = nim,
        nama = nama,
        kelas = kelas,
        alamat = alamat,
        angkatan = angkatan,
        jenis_kelamin = jenis_kelamin,
        dosen1= dosen1,
        dosen2= dosen2,
        judulskripsi = judulskripsi
    )
}