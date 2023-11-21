package com.example.uvshop.DataBase.SignIn

import com.example.uvshop.Data.Shop

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)
data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
    var shop: Shop? = null
)

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)

class UserDataHolder private constructor() {
    private var userData: UserData? = null

    companion object {
        private var instance: UserDataHolder? = null

        fun getInstance(): UserDataHolder {
            if (instance == null) {
                instance = UserDataHolder()
            }
            return instance!!
        }
    }

    fun setUserData(userData: UserData?) {
        this.userData = userData
    }

    fun setUserShop(shop: Shop?){
        this.userData?.shop = shop
    }

    fun getUserData(): UserData? {
        return userData
    }
}
