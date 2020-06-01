package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Embedded

@Entity(
    tableName = "user",
    indices = [
        Index(value = ["email"], unique = true)
    ]
)
data class UserDb(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val userId: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @Embedded(prefix = "address_")
    val address: AddressDb?,

    @ColumnInfo(name = "phone")
    val phone: String?,

    @ColumnInfo(name = "website")
    val webSite: String?,

    @Embedded(prefix = "company_")
    val company: CompanyDb?
)

data class CompanyDb (

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "catch_phrase")
    val catchPhrase: String?,

    @ColumnInfo(name = "bullshit")
    val bs: String?
)
