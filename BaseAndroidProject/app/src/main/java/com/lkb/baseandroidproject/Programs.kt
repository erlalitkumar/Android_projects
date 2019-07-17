package com.lkb.baseandroidproject

/* infix
fun main() {
    println("Hello world!")
    val math = MyMath()
    println(math addFive (5))
}


class MyMath {
    infix fun addFive(x: Int): Int {
        return x + 5
    }
}*/


//fun main() {
//    // println(isMultipleOf(9,3))
//    var list = arrayListOf<Int>()
//    for (number in 1..10) {
//        list.add(number)
//    }
//    val resultList1 = list.filterOnCondition { isMultipleOf(it, 2) }
//    print(resultList1)
//
//    val resultList2 = list.filterOnCondition { isMultipleOf(it, 3) }
//    print(resultList2)
//    val resultList3 = list.filterOnCondition { isMultipleOf(it, 5) }
//    print(resultList3)
//}
//
//fun isMultipleOf(number: Int, multipleOf: Int): Boolean {
//    return number % multipleOf == 0
//}
//
//fun <T> ArrayList<T>.filterOnCondition(condition: (T) -> Boolean): ArrayList<T> {
//    val result = arrayListOf<T>()
//    for (item in this) {
//        if (condition(item)) {
//            result.add(item)
//        }
//    }
//    return result
//}

//fun <T> ArrayList<T>.filterOnCondition(condition: (T) -> Boolean): ArrayList<T>{
//    val result = arrayListOf<T>()
//    for (item in this){
//        if (condition(item)){
//            result.add(item)
//        }
//    }
//    return result
//}

//fun main(){
//    myTask { middleTask() }
//    myTask { taskTwo() }
//    myTask { alsoInMiddle() }
//}
//
//fun taskOne(){
//    println("I am task One")
//}
//fun taskTwo(){
//    println("I am task two")
//}
//fun taskThree(){
//    println("I am task Three")
//}
//
//fun alsoInMiddle(){
//    println("I am also in middle")
//}
//
//fun middleTask(){
//    println("I am in the Middle")
//}
//
//inline fun myTask(block:()->Unit){
//    taskOne()
//    block()
//    taskThree()
//}

