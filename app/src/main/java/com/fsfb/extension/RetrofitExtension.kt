package com.fsfb.extension

import retrofit2.Retrofit

inline fun <reified T> Retrofit.create(): T {
    return this.create(T::class.java)
}