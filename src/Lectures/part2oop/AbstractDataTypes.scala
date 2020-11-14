package Lectures.part2oop

object AbstractDataTypes extends App {

  //abstract members - leave some fields/methods blank/unimplemented - keyword abstract
  //will leave the subclasses to define it. are uninstantiatable until then

  abstract class Animal {
    val creatureType: String = "wild"  //unimplemented - abstract
    def eat: Unit //unimplemented - abstract
  }

  class Dog extends Animal{
    override val creatureType: String = "canine"  //override is optional bc compiler knows it's on an abstract class

    override def eat: Unit = println("crunch!") //override is optional here too
  }

  // traits - ultimate abstract data type

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    def eat: Unit = "nom nom nom"
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}.")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat(dog)

  //traits v abstract classes
  //traits do not have constructor parms
  //you can only extend one class but you can mix in multiple traits
  //i.e. multiple traits can be inherited by a single class
  //we choose a trait v an abstract class when it describes a *behavior*
  //we choose an abstract class v a trait when it describes a thingMy
}
