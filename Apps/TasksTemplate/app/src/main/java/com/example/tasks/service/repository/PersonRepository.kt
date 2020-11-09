package com.example.tasks.service.repository

import com.example.tasks.service.constants.TaskConstants.HTTP.SUCCESS
import com.example.tasks.service.listener.ApiListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.remote.PersonService
import com.example.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

    private val remote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listener: ApiListener) {
        val call: Call<HeaderModel> = remote.login(email, password)
        call.enqueue(object : Callback<HeaderModel> {
            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if (response.code() != SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }
        })
    }

}