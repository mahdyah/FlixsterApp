package com.example.flixsterone

import android.media.Image
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var overview: String? = null

    @JvmField
    @SerializedName("poster_path")
    var poster_path: String? = null

    @JvmField
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null



}