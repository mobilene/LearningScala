package Lectures.part2oop

object Objects extends App {

  object Person{
    //static/class-level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    //factory method - sole purpose is to build Persons given parms
    def apply(mother:Person, father: Person): Person = new Person("Bobby")
  }

  //companion to follow -- object and class reside in same scope and have same name

  class Person(val name: String){
    //instance-level functionality
  }

  println(Person.N_EYES)
  println(Person.canFly)

  //Scala object is a singleton instance, i.e., I'm defining the object's type AND its only instance.

  val Mary = new Person("Mary")
  val John = new Person("John")

  println (Mary == John)

  val Bobby = Person(Mary, John)

  //Scala applications are a scala object with:
  //def main(args: Array(String)): Unit
  //this is equivalent to the "extends App" in the main object def above

}
