package chainReaction.utils

import java.io.File
import java.util.*
import kotlin.collections.HashMap

class BoardGenerator {
    class BreakException: RuntimeException()

    companion object {

        fun createBoard(
                path: String,
                rows: Int,
                cols: Int,
                maxShapes: Int,
                maxColors: Int,
                percentageFilled: Double = 0.7,
                seed: Long = Random().nextLong()) {

            val size = rows*cols
            val tokens = (size * percentageFilled).floor()
            val pool = MutableList(size) {it}
            pool.shuffle()


            val set = HashMap<Pair<Int,Int>, Pair<Int,Int>?>()
            (0 until rows).forEach { i ->
                (0 until cols).forEach { j ->
                    set.put(Pair(i,j), null)
                }
            }

            val rand = Random(seed)

            try {
                var lastPosition = Pair(0, 0)
                var lastColor = 0
                var lastShape = 0

                (0 until tokens).forEach {

                    println(lastPosition)

                    val posibilities = set.filter {
                        (it.key.first == lastPosition.first
                                || it.key.second == lastPosition.second)
                                && it.value == null
                    }

                    if (posibilities.isEmpty()) {
                        throw BreakException()
                    }

                    val next = posibilities.entries.shuffled().first()
                    set[lastPosition] = Pair(lastShape, lastColor)
                    lastPosition = next.key

                    if (rand.nextBoolean()) {
                        lastShape = rand.nextInt(maxShapes)
                    } else {
                        lastColor = rand.nextInt(maxColors)
                    }
                }
            }catch (e: BreakException) {
                println("Reached a closed alley")
            }

            val file = File(path)

            file.writeText("${rows},${cols}\n")
            file.appendText("${maxShapes},${maxColors}\n")
            file.appendText("${0},${0}\n")
            (0 until rows).forEach { i ->
                (0 until cols).forEach { j ->
                    val pos = set[Pair(i,j)]
                    if(pos != null) {
                        file.appendText("${pos.first + 1} ${pos.second + 1}")
                    }
                    if(j < cols-1) {
                        file.appendText(",")
                    } else {
                        file.appendText("\n")
                    }
                }
            }



        }



    }
}

fun Double.floor() = Math.floor(this).toInt()
fun Double.ceil() = Math.ceil(this).toInt()

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val size = 4
            BoardGenerator.createBoard("./test/resources/here20x20",size,size,3,3)
        }
    }
}