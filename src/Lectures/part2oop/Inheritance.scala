package Lectures.part2oop

object Inheritance extends App {

  class Animal {
    val creatureType = "wild"
    def eat = println("nom nom nom") //public
    private def devour = println("slurp")
    protected def dine = println("clink clank")
  }

  class Cat extends Animal{
    def sup = {
      dine
      println("yum")
    }
  }

  val cat = new Cat

  cat.eat
  // cat.devour
  cat.sup

  //constructors in inheritance

  class Person(name:String, age: Int){
    def this(name: String) = this(name, age=0)
  }
  class Adult(name:String, age:Int, idCard:String) extends Person(name)

  //overriding

  class Dog(override val creatureType: String) extends Animal {
    override def dine = {
      super.eat  //refers to the method eat in the superclass
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")

  dog.dine
  println(dog.creatureType)

  //polymorphism - type substitution
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  //super - ref field from parent class

  //prevent overrides
  //1 use final before the def - can't override the function
  //2 use final on the class itself - can't extend the class then
  //3 use sealed on the class itself - softer - can extend classes within
  // this file only but prevents extension in other files.

}
