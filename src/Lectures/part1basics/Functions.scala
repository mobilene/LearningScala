package Lectures.part1basics

object Functions extends App {

  def aFunction(a: String,b: Int): String =
    a + " " + b

  println (aFunction("hello",3))

  def aParameterlessFunction (): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello ", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1)
  }

  //A greeting function. Name and age, hi my name is $name and I am $age years old.

  def aGreetingFunction(aName: String, anAge: Int): String =
    "Hi, my name is " + aName + " and I am " + anAge + " years old."

  println(aGreetingFunction("Jim", 53))

  //Factorial function. Pass in parm, compute factorial, recursive.

  def aFactorialFunction(n: Int): Int =
    if (n <= 0) 1
    else n * aFactorialFunction(n - 1)

  println(aFactorialFunction(10))

  //Fibonacci function. Stream of numbers f(1) = 1, f(2) = 1, f(n) = f(n-1) + f(n-2)

  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)

  println(fibonacci(8))

  //Test if a number is prime.

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0  && isPrimeUntil(t-1)
    isPrimeUntil(n/2)
  }

  println(isPrime(2003))
}
