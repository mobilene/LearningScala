package Lectures.part4pm

import Exercises.{Cons, Empty, MyList}

object allThePatterns extends App {

  //constants -- switch on steroids

  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case allThePatterns=> "A singleton object"
  }

  //match anything
  // -wild card

  val matchAnything = x match {
    case _ =>
  }

  // -variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  //tuples

  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
    //PMs can be nested!

  //case classes (biggest use case) -- constructor pattern
  // can be nested with case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  //List Patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) =>  //extractor
    case List(1, _*) =>  // list of arbitrary length
    case 1 :: List(_) =>  //infix pattern - lots of underhood magic
    case List(1, 2, 3) :+ 42 => //also infix pattern
  }

  //Type Specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] =>  //explicit type specifier
    case _ =>
  }

  //Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //names the pattern - name binding
    case Cons(1, rest @ Cons(2, _)) => //name binding inside nested patterns
  }

  //Multi patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) =>   //compound pattern or multi pattern
  }

  //if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => //filters elements div by 2
  }

  // this will resolve to "a list of strings" -- a jvm trick question
  // jvm and therefore scala oblivious to generic types

  val numbers = List (1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }

  println(numbersMatch)
}
