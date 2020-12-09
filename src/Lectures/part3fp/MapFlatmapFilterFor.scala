package Lectures.part3fp

object MapFlatmapFilterFor extends App {

  //standard library implementation of a list
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatMap
  val toPair = (x: Int) => (List(x, x + 1))
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val characters = List("a", "b", "c", "d")
  val colors = List("red", "blue", "green")
  // List("a1", "a2", "a3"..."d4")

  //iterating
  val combinations = numbers.flatMap(n => characters.flatMap(c => colors.map(r => c + n + "-" + r)))
  val numcolor = numbers.flatMap(n => colors.map(r => n + "-" + r))
  println(combinations)
  println(numcolor)

  val myName = List("Jim", "Grey")
  val ricksName = List("Rick", "Grey")
  val flattened = myName.flatMap(j => ricksName.map(r => j + r))
  println(flattened)

  //foreach
  list.foreach(println)

  //shorthand: for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- characters
    r <- colors
  } yield c + n + "-" + r
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // one more little syntax overload
  list.map { x =>
    x * 2
  }

  /* exercises
  1. MyList supports for comprehensions?
  2. A small collection of at most one element - Maybe[+T]
     - map, flatMap, filter for this collection.
   */




}
