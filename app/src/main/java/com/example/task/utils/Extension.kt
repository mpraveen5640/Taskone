package com.example.task.utils

import android.content.Context
import android.widget.Toast

fun Context.displayToast(massage: String) {
    Toast.makeText(this, massage, Toast.LENGTH_SHORT).show()
}