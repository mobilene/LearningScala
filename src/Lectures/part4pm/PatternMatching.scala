package Lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" //wild card, matches anything
  }

  println (x)
  println (description)

  // Can decompose values.

  case class Person(name: String, age: Int)
  val Bob = Person("Bob", 20)

  val greeting = Bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US."
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "I don't know who I am."
  }

  println(greeting)

  // PM on sealed hierarchies - just stick with case classes
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Rottweiler")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  //match everything -- overkill
  val isEven: Unit = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  val isEvenCond = if (x % 2 == 0) true else false //?
  val isEvenNormal = x % 2 == 0

  //exercise

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  //with this hierarchy write simple function using PM taking Expr => human readable form
  //Sum(Number(2), Number(3)) => 2 + 3 (string)
  //Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
  //Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
  //Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def showParens(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      showParens(e1) + " * " + showParens(e2)
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

}
