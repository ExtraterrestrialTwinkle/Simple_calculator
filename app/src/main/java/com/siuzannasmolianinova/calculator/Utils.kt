package com.siuzannasmolianinova.calculator

fun String.map(): List<String>{
        val delimiter = ' '
        return this.split(delimiter).map{it.trim()}
    }
