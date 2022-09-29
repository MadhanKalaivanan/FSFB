package com.fsfb.services


sealed class RemoteError : Exception() {
    companion object {
        val unknownError: Exception
            get() = Exception("Unknown Error")

        val unAuthorizedUser: Exception
            get() = Exception("UnAuthorized User")

        val networkUnAvailable: Exception
            get() = Exception("No Internet Connection")

    }
}