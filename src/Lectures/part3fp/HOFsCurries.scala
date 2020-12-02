package Lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF) functions having functions as parms
  // map, flatMap, filter, in myList, are HOFs

  // define function that applies a function n times over given value x
  // nTimes(f, n, x) f = func, n = num times, x = val to act on
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = (f(f(...(x))) = nTimes(f, n-1, f(x)) - recursion

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x:Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // nTimesBetter(f,n) = x => f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne(plusOne...(x)))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10)) //equiv to prev line

  //functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
  1. expand MyList

      -add foreach method A=>Unit, apply to every element of MyList
       [1,2,3].foreach(x => println(x))
       prints 1 2 3 on separate lines

      -add sort function ((A, A) => Int) => MyList
       [1,2,3].sort((x, y) => y - x) => [3,2,1]

      -add zipWith function (list, (A, A) => B) => MyList(B)
       [1,2,3].zipWith([4,5,6], x * y) => [1*3,2*5,3*6]

      -add curried fold function
       fold(start)(function) => a value
       [1,2,3].fold(0)(x+y) = 6
       add first element to zero, add result to second element, add result
       to third element, ..., until there are no more elements

   2. define method toCurry(f: (Int, Int) => Int) => (Int => Int -> Int)
      fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

   3. compose(f,g) => x => f(g(x))
      andthen(f,g) => x => g(f(x))
   */


}
