package com.example.meet13.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet13.model.Mahasiswa
import com.example.meet13.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(
    private val mhs: MahasiswaRepository
) : ViewModel(){
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    //memperbaryi state berdasarkan inpiut pengguna
    fun updateState (mahasiswaEvent: MahasiswaEvent){
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent
        )
    }

    fun validateFields(): Boolean{
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else " NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else " Nama tidak boleh kosong",
//            kelas = if (event.kelas.isNotEmpty()) null else " Kelas tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else " Alamat tidak boleh kosong",
//            angkatan = if (event.angkatan.isNotEmpty()) null else " Angkatan tidak boleh kosong",
//            jenis_kelamin = if (event.jenis_kelamin.isNotEmpty()) null else " Jenis Kelamin tidak boleh kosong",
            dosen1 = if (event.dosen1.isNotEmpty()) null else " Jenis Kelamin tidak boleh kosong",
            dosen2 = if (event.dosen2.isNotEmpty()) null else " Jenis Kelamin tidak boleh kosong",
            judulskripsi = if (event.judulskripsi.isNotEmpty()) null else " Jenis Kelamin tidak boleh kosong",

        )

        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertMhs(){
        if (validateFields()){
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    mhs.insertMahasiswa(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data Berhasil disimpan")
                } catch (e: Exception){
                    uiState = FormState.Error(" Data Gagal Disimpan")

                }
            }
        } else
            uiState = FormState.Error("Data tidak valid")
    }
    fun resetForm(){
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }
    fun resetSnackBarMessage() {
        uiState = FormState.Idle
    }
}

sealed class FormState{
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String): FormState()
    data class Error(val message: String): FormState()
}


data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
)



data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val kelas: String? = null,
    val alamat: String? = null,
    val angkatan: String? = null,
    val jenis_kelamin: String? = null,
    val dosen1: String? = null,
    val dosen2: String? = null,
    val judulskripsi: String? = null,
){
    fun isValid(): Boolean{
        return nim == null && nama == null
                && kelas == null && alamat == null
                && angkatan == null && jenis_kelamin == null
    }
}


data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val kelas: String = "",
    val alamat: String = "",
    val angkatan: String = "",
    val jenis_kelamin: String = "",
    val dosen1: String = "",
    val dosen2: String = "",
    val judulskripsi: String = ""
)



fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
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
