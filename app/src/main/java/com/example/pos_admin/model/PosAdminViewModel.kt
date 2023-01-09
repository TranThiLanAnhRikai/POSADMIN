package com.example.pos_admin.model

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class PosAdminViewModel: ViewModel() {



    private val calendar: Calendar = Calendar.getInstance()
    private val currentDateTime: Date = calendar.time

    private val dateFormat = SimpleDateFormat("EEEE, yyyy/MM/dd")
    val formattedDateTime: String = dateFormat.format(currentDateTime)

    fun isFirstLoginValid(): Boolean {
        return true
    }

    fun isSecondLoginValid(): Boolean {
        return true
    }


}
