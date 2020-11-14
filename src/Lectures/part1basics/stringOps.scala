package Lectures.part1basics

object stringOps extends App{

  val str:String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toUpperCase())
  println(str.length)

  val aNumString = "2"
  val aNum = aNumString.toInt
  println('a' + aNumString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  //string interpolators
  //s-interpolator

  val name="David"
  val age=12
  println(s"Hello, my name is $name and my age is $age.")
  println(s"Hello, my name is $name and I will be turing ${age+1}.")

  //f-interpolators

  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)

  //raw interpolator
  println(raw"This is a \n newline.")
  val escaped = "This is a \\n newline."
  println(raw"$escaped")


}
