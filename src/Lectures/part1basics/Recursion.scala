package Lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + ". First need factorial of " + (n - 1) + ".")
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x < 1) accumulator
      else factHelper(x - 1, x * accumulator) //tail recursion - use recursive call as last expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))

  //concatenate string

  @tailrec def stringConcat(str: String, n: Int, accum: String): String =
    if (n < 1) accum
    else stringConcat(str, n - 1, str + accum)

  println(stringConcat("hello ", 5, ""))

  // prime using tail recursion

  val t = 3
  println(t / 2)

  def Prime(n: Int): Boolean = {
    @tailrec def isPrime(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrime(t - 1, n % t != 0 && isStillPrime)

    isPrime(n / 2, true)

  }

  println(Prime(2003))
  println(Prime(630))


  // fibonacci, tail recursive

  def fib(n: Int): Int = {
    def fibTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i > n) last
      else fibTailRec(i + 1, last + nextToLast, last)

    if (n < 2) 1
    else fibTailRec(2, 1, 1)
  }

  println(fib(8))

}