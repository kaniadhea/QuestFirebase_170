package com.example.meet13.di

import com.example.meet13.repository.MahasiswaRepository
import com.example.meet13.repository.NetworkMahasiswaRepository
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val mahasiswaRepository: MahasiswaRepository
}

class MahasiswaContainer : AppContainer {

    private val firebase: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val  mahasiswaRepository: MahasiswaRepository by lazy {
        NetworkMahasiswaRepository(firebase)
    }

}
