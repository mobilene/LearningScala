package Lectures.part2oop

object Exceptions extends App {

  // this crashes with a null pointer exception
  // val x: String = null
  // println (x.length)

  //throwing and catching exceptions

  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // both crash JVM
  // Exceptions - something went wrong with the program
  // Error - something went wrong with the JVM

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No Int for you!")
    else 42

  val possibleFailure = try {
    //code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a RuntimeException"); 43
  } finally {
    //code that will get executed no matter what - optional block - does not influence return type
    println("finally")
  }

  // define your own exceptions

  class MyException extends Exception
  // val jimException = new MyException

  // throw jimException

  // 1. crash your program with an out of memory error
  // 2. crash with a stack overflow error
  // 3. define pocket calculator class PocketCalculator
  //    class add (x,y)
  //    class subtract (x,y)
  //    class multiply (x,y)
  //    class divide (x,y)
  // Throw overflow exception if add(x,y) exceeds Int.MAX_VALUE
  // Throw underflow exception if if subtract(x,y) exceeds Int.MIN_VALUE
  // Throw MathCalculationException for div/0

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  println(PocketCalculator.add(5, 4))
  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(4, 0))
}
