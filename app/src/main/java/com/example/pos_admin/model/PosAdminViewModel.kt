package com.example.pos_admin.model

import androidx.lifecycle.ViewModel


class PosAdminViewModel: ViewModel() {
    fun isFirstLoginValid(): Boolean {
        return true
    }
    fun isSecondLoginValid(): Boolean {
        return true
    }
    fun getCurrentDateTime() {

    }
}