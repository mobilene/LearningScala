package Lectures.part2oop

object ooBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Jim", "Grey", 1967)
  println(author.fullName)

  val book = new Novel("Down the Road", 2007, author)
  println(book.authorAge)
  println(book.isWrittenBy(author))
  val secondEd = book.copy(2010)
  println(secondEd.authorAge)
}

class Person(name: String, val age:Int) {

  def greet(name: String): Unit = println(s"${this.name} says, Hi, $name")

  def greet(): Unit = println(s"Hi, I am $name") //overloading!

  //multiple constructors
  def this(name : String) = this(name, 0)
}

/*
Novel and a Writer
Writer: first name, surname, year
- method fullname
Novel: name, year of release, author (instance of Writer)
- method authorAge (age of author at year of release)
- method isWrittenBy
- method copy (new year of release) = new instance of novel with new year of release
 */

class Writer(firstName: String, lastName: String, val birthYear: Int) {
  def fullName: String = firstName + " " + lastName
}

class Novel(title: String, releaseYear: Int, author: Writer) {
  def authorAge = releaseYear - author.birthYear
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(title, newYear, author)
}

/*
Counter class
- receives an int value
- method current count
- method to increment/decrement => new counter
- overload inc/dec to receive an amount
 */

class Counterr(val x: Int){
  def inc = new Counterr(x + 1)
  def dec = new Counterr(x - 1)
}