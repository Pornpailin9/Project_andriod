package com.example.facebook_authen

class CookieR(var name: String, var score: Float) {

    companion object {
        fun getSampleCookieRData(size: Int): ArrayList<CookieR> {
            val student: ArrayList<CookieR> = ArrayList()
            for (i in 1 until size) {
                student.add(CookieR("CookieRun $i", Math.random().toFloat() * 100))
            }
            return student
        }
    }
}