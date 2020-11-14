package Lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    //use the type A inside the class definition - it's generic - also works for traits
    def add[B >: A](element: B): MyList[B] = ???
    /* if A = Cat and B = Dog = Animal - if I add an Animal to a list of Cats, then the list turns
    into a list of Animals.
     */
  }

  class MyMap[Key, Value]

  val listofintegers = new MyList[Int]
  val listofstrings = new MyList[String]

  //generic methods

  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  //the dreaded variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, list[Cat] extends list[Animal] = covariance
  class CovariantList[+A]
  val animal:Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  //animalList.add(new Dog) Is this valid? Hard question. Returns a list of animals.

  //2. no, list[Cat] and list[Animal] are separate things = invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. hell no, contravariance.
  class Trainer[-A] //the minus means contravariant
  val contravariantList:Trainer[Cat] = new Trainer[Animal] //repl list of cats w list of animals

  //bounded types - can use generic classes for sub/superclasses of a different type
  class Cage[A <: Animal](animal: A) //upward bounded
  val cage = new Cage(new Dog)
  //can also supertype with >:

  //expand MyList to be generic


}
