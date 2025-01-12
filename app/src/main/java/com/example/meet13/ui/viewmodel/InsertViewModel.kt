package com.example.meet13.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.meet13.model.Mahasiswa
import com.example.meet13.repository.MahasiswaRepository

class InsertViewModel(
    private val mhs: MahasiswaRepository
)


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
)

fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    kelas = kelas,
    alamat = alamat,
    angkatan = angkatan,
    jenis_kelamin = jenis_kelamin,
)
