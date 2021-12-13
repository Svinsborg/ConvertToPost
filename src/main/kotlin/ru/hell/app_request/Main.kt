@file:JvmName("Main")
package ru.hell.app_request

import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

suspend fun main() {
/*    val  getResp = ru.hell.app_request.JsonToPost.connection()
    val  mainScope = MainScope()*/


    println("START")

/*    mainScope.launch {
        ru.hell.app_request.printText(getResp)

    }*/

    CoroutineScope(IO).launch {
        println("Start Coroutine")
        println("CONNEKTION")

        getDateResponse()

        delay(500)
        print("*")
        delay(500)
        print(" *")
        delay(500)
        print(" *")
        delay(500)
        print(" *")
        delay(500)
        println(" *")

        println("End Coroutine")
    }

   val result2 = loadData()
        println(result2)
    Thread.sleep(5000)
    println("STOP")
}

suspend fun getDateResponse(){
    val result = JsonToPost.connection()
    printResult(result)
}

fun printText(input: List<Post>){
    println(input)
}

private suspend fun printResult(input: List<Post>){
    withContext(Main){
            printText(input)
    }
}

private fun loadData(): String {
    println("Start ru.hell.app_request.Post to JSON")
    val data = DateResource.createDataSet()
    val json = (Gson().toJson(data))
    println("End ru.hell.app_request.Post to JSON")
    return json
}