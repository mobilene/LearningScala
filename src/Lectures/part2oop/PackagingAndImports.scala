package Lectures.part2oop

import Playground.{Cinderella => Princess, PrinceCharming}
// can also do import Playground._ to bring in everything - but do it only if you really need it
// can also alias, see => above, if you're importing from different packages with the same names in it

import java.util.Date
import java.sql.{Date => SqlDate} // avoids the confusion


object PackagingAndImports extends App{

  //writer is in the package, in ooBasics, so can just use it
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  //import the package - compiler can find it for you - see autogenned import statement above
  val princess = new Princess

  //or can use fully qualified name, but compiler will fix it for you and add the import statement
  val anotherPrincess = new Playground.Cinderella

  //packages are in hierarchy - see Lectures.part2oop above? hierarchy, separated by dots.

  //package object - right click on the package and create new package object
  sayHello
  println(SPEED_OF_LIGHT)

  //imports
  val prince = new PrinceCharming

  val d = new Date // uses util date because it's imported first
  //use fully qualified name, but it's deprecated
  // val sqlDate = new java.sql.Date(2018, 5, 4)
  // val sqlDate = new SqlDate(2018, 5, 4)
}
