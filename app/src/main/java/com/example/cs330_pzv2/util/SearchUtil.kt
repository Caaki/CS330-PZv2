package com.example.cs330_pzv2.util

object SearchUtil {

    /**
     * This will check if the user enetered a valid search
     * The search should contain at least a title or tags for to be search
     * User can't use forbidden caracters and values
     *
     */
    fun validateSearch(
        title: String,
        tags:String
    ): Boolean{

        val forbidden = arrayListOf<String>("%3B",";","%22","\"")

        if (title.isBlank() && tags.isBlank()){
            return false
        }
        forbidden.map {
            text ->
            if (title.contains(text) || tags.contains(text)){
                return false
            }
        }
       return true
    }
}