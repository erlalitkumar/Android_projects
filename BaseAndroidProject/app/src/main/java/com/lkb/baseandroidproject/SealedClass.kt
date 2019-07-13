package com.lkb.baseandroidproject

sealed class Expr
data class FloatVal(val number: Float) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
data class Sub(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr()

fun eval(expr: Expr): Float = when (expr) {
    is FloatVal -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    is Sub -> eval(expr.e1) - eval(expr.e2)
    NotANumber -> Float.NaN

}

fun main(){
    val c1 = FloatVal(67.8F)
    val c2 = FloatVal(78.9F)
    val s1 = Sum(c1,c2)
    val s2 = Sub(c1,c2)
    val res1 = eval(s1)
    val res2 = eval(s2)
    println("Result1 = $res1")
    println("Result2 = $res2")
}
