package com.alexis.shop.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object AuthValidationHelper {
    private const val MIN_PHONE_LENGTH = 9
    private const val MAX_PHONE_LENGTH = 14
    private const val MIN_PASSWORD_LENGTH = 8
    private const val MAX_PASSWORD_LENGTH = 16
    private const val EMAIL = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
    private val pattern: Pattern? = null
    private var matcher: Matcher? = null

    private const val DATE_PATTERN =
        "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)"

    fun isFormValid(value: String?): Boolean {
        return !value.isNullOrEmpty()
    }

    fun isPhoneValid(phone: String?): Boolean {
        return (!phone.isNullOrEmpty()
                && phone.length >= MIN_PHONE_LENGTH
                && phone.length <= MAX_PHONE_LENGTH)
    }

    fun isPasswordValid(password: String?): Boolean {
        return (!password.isNullOrEmpty()
                && isPasswordContainLetter(password)
                && isPasswordContainNumber(password)
                && password.length >= MIN_PASSWORD_LENGTH
                && password.length <= MAX_PASSWORD_LENGTH)
    }

    private fun isPasswordContainNumber(password: String?): Boolean {
        if(!password.isNullOrEmpty()) {
            val chars: CharArray = password.toCharArray()
            for (c in chars) {
                if (Character.isDigit(c)) {
                    return true
                }
            }
            return false
        }
        return false
    }

    private fun isPasswordContainLetter(password: String?): Boolean {
        if(!password.isNullOrEmpty()) {
            if (password.contains("[a-zA-Z]+".toRegex())){
                return true
            }
            return false
        }
        return false
    }

    fun isValidDate(date: String?): Boolean {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        var testDate: Date? = null
        try {
            testDate = sdf.parse(date)
        } catch (e: ParseException) {
           // errorMessage = "the date you provided is in an invalid date" +
                    " format."
            return false
        }
        if (!sdf.format(testDate).equals(date)) {
            //errorMessage = "The date that you provided is invalid."
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrEmpty() && isEmailPatternValid(email)
    }

    private fun isEmailPatternValid(value: String): Boolean {
        return Pattern.compile(EMAIL).matcher(value).find()
    }
}