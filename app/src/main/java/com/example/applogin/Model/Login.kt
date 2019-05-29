package com.example.applogin.Model

import android.os.Parcel
import android.os.Parcelable

class Login(): Parcelable {
    var login: String? = null
    var senha: String? = null
    var nome: String? = null
    var id: Int? = null
    var email: String? = null

    constructor(parcel: Parcel) : this() {
        login = parcel.readString()
        senha = parcel.readString()
        nome = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        email = parcel.readString()
    }

    constructor(id: Int?, login: String?, senha: String?, nome: String?, email: String?) : this() {
        this.login = login
        this.senha = senha
        this.nome = nome
        this.id = id
        this.email = email
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(login)
        parcel.writeValue(senha)
        parcel.writeString(nome)
        parcel.writeValue(id)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Login> {
        override fun createFromParcel(parcel: Parcel): Login {
            return Login(parcel)
        }

        override fun newArray(size: Int): Array<Login?> {
            return arrayOfNulls(size)
        }
    }

}