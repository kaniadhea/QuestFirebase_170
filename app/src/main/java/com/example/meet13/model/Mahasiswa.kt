package com.example.meet13.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val kelas: String,
    val alamat: String,
    val angkatan: String,
    val jenis_kelamin: String,

){
    constructor(

    ): this("","","","","","")
}
