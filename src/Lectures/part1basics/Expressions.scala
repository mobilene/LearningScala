package Lectures.part1basics

object Expressions extends App {

  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  println(1 != x)

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  //Instructions (do) vs Expressions (value and/or type)

  // IF expression

  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  //Loops, but don't use themn

  var i = 0
  while (i < 10) {
    i += 1
    println (i)
  }

  val aWeirdValue = (aVariable = 3) // Unit type == void in other languages
  println(aWeirdValue)

  //Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }
  println(aCodeBlock)


}
