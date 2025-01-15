package com.example.meet13.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val kelas: String,
    val alamat: String,
    val angkatan: String,
    val jenis_kelamin: String,
    val dosen1: String,
    val dosen2: String,
    val judulskripsi: String

){
    constructor(

    ): this("","","","","","", "", "","")
}
