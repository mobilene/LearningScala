package Lectures.part2oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)
  val Jim = new Person("Jim", 34)
  println (Jim.name)
  println (Jim.toString)
  println (Jim) //automatically assumes toString

  val Jim2 = new Person ("Jim", 34)
  println (Jim == Jim2)  //case classes have equals, regular classes don't - this would be false
  val Jim3 = Jim.copy() // case classes have this ability to copy
  val Jim4 = Jim.copy(age=45)
  println (Jim3, Jim4)

  val thePerson = Person //ccs have companion objects
  val Mary = Person("Mary", 23) //companion objects have built in apply method
  println (Mary)

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}
