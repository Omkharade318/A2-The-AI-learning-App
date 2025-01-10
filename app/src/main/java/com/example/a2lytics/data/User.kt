package com.example.a2lytics.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val mobileNumber: String,
    val email: String,
    val address: String,
    val schoolName: String,
    val educationBoard: String,
    val userClass: String
)