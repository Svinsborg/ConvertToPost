import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main() {
    println("START")




    CoroutineScope(IO).launch {
        println("Start Coroutine")
        println("CONNEKTION")
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

        val result = JsonToPost.connection()
        printResult(result)

        println("End Coroutine")
    }



   val result2 = loadData()
        println(result2)

    Thread.sleep(5000)

    println("STOP")
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
    println("Start Post to JSON")
    val data = DateResource.createDataSet()
    val json = (Gson().toJson(data))
    return json
    println("End Post to JSON")
}