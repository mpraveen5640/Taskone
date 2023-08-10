package com.example.task.model

import java.io.Serializable

data class User(
    var name: String? = null,
    var mobile: String? = null,
    var email: String? = null,
    var gener: String? = null,
    var state: String? = null,
    var city: String? = null,
    var password: String? = null
) : Serializable