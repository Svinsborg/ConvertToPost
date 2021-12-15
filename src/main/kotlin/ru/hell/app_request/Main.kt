@file:JvmName("Main")
package ru.hell.app_request


import com.google.gson.Gson
import io.ktor.client.engine.cio.*

import kotlinx.coroutines.runBlocking




fun main() {

    println("START")

    runBlocking {
        val client = JsonToPost(CIO.create())
        val response = client.getPost()
        response.forEach {println(it)}
    }


   val result2 = loadData()
        println(result2)
    //Thread.sleep(5000)
    println("STOP")
}


private fun loadData(): String {
    println("Start Post to JSON")
    val data = DateResource.createDataSet()
    val json = (Gson().toJson(data))
    println("End Post to JSON")
    return json
}
