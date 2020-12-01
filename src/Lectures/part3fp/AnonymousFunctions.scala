package Lectures.part3fp

object AnonymousFunctions extends App {

  val old_doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }

  val doubler: Int => Int = (x) => x * 2 //anonymous or lambda function
  //syntax sugar, instantiates a Function1 just like old_doubler above

  //multiple parms

  val adder = (a: Int, b: Int) => a + b
  //or val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parms
  val justDoSomething = () => 3
  //or val justDoSomething: () => Int = () => 3

  println(justDoSomething) //prints function
  println(justDoSomething()) //prints result of function - actual call

  //curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  //more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 //shorthand for x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equiv to (a, b) => a + b

  /*
  1. go to MyList, repl all FunctionX calls with lambdas
  2. rewrite the "special" adder that we wrote in prev video, curried, as anonymous function.
   */

  def SuperAdd = (x: Int) => (y: Int) => x + y
  println(SuperAdd(3)(4))
}
