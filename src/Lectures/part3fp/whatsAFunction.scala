package Lectures.part3fp

object whatsAFunction extends App{

  //DREAM: use functions as first class elements
  //PROBLEM: oop - jvm built for oop in java

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4) // will return 7

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  println(adder(4,5))

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  println(concatenator("Hello ", "Bob"))

  //exercise - def function that takes int and returns function that takes int and returns int
  // - what's the type of this function?
  // - how to do it?

  def overwroughtAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = overwroughtAdder(3)
  println (adder3(5))

  //is equivalent to
  println (overwroughtAdder(3)(5)) //curried function

  //functional programming is all about passing functions as parameters and using functions as values



}
