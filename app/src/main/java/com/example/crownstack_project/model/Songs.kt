package com.example.crownstack_project.model

import com.google.gson.annotations.SerializedName


data class Songs(
    @SerializedName("resultCount") val resultCount : Int,
    @SerializedName("results") val results : List<Results>
)