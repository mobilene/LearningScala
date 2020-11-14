package Lectures.part2oop

import java.awt.im.InputMethodRequests

object Notations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}."

    def unary_! : String = s"$name, what the heck?"

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I love $favoriteMovie"

    def apply(times:Int): String = s"Hi, my name is $name and I watched $favoriteMovie $times times."

    def +(nick: String): Person = new Person(s"$name $nick", favoriteMovie)

    def learns(thing: String): String = s"$name learns $thing"

    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") //infix notation - allowed on methods with one parm

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2)) //because all operators are methods in scala

  //prefix notation
  val x = -1 //the - is a unary operator and thus a method, unary works only with + - ! ~
  val y = 1.unary_- //equivalent to ^
  println(!mary)
  println(mary.unary_!)

  //postfix
  println(mary.isAlive)
  println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary())

  /*
  1. Overload the + operator. New + receives a string and returns a person with a nickname
      mary + "the rockstar" = "Mary the rockstar"
  */

  println((mary + "the Rockstar")())

  /*
  2. Add an age to the person class with default zero
     Add a unary + operator that increments the age and returns a new person with age + 1
     +mary => mary with age incremented
  */

  println((+mary).age)

  /*
  3. Add learns method to person class => "Mary learns <learn>"
     Add a learnsScala method to person class that calls learns method with Scala as parm
     Use it in postfix notation

 */

  println(mary.learnsScala)
  println(mary learnsScala)

  /*
  4. Overload the apply method to receive a number and return as a string
     Mary.apply(2) => Mary watched her favorite movie Inception 2 times.
   */

  println(mary(2))
}
