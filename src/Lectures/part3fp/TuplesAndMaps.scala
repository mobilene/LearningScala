package Lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  //tuples = finite ordered sort-of-lists
  val aTuple = new Tuple2(2, "hello, Scala") //type is Tuple2[Int, String] = (Int, String)
  //the new is optional thx to apply methods
  // val aTuple = (2, "hello, Scala") is equivalent
  // no more than tuples of 22 elements

  println(aTuple._1) //print first element
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) //swaps elements

  //Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), ("Daniel", 789)).withDefaultValue(-1) // a map of two tuples
  //equivalent: val phonebook = Map (Jim -> 555, Daniel -> 789)
  println(phonebook)

  println(phonebook.contains("Jim")) // returns boolean
  println(phonebook("Jim")) // tries to return value assoc w this key
  println(phonebook("Mary")) //throws exception if you don't have .withDefaultValue(-1) on map

  //add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing //maps are immutable
  println(newPhonebook)

  //can map, flatMap, and filter maps
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) //lowercasing the keys

  //filterKeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  //mapValues
  println(phonebook.view.mapValues(number => number + "-2020").toMap)

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Agnes", "Daniel", "Joe")
  println(names.groupBy(name => name.charAt(0)))

  /*
  Exercises
  1. What happens if in the toLowerCase map above if there were two original entries
     like "Jim" -> 555 and "JIM" -> 900?

  2. Overly simplified social network based on maps.
     Person = String
     Map to list of friends, a list of Strings.
     Add a Person to the network
     Remove a Person from the network
     Friend (mutually) two Persons
     Unfriend (mutually) two Persons
     Show number of friends of given Person
     WHo has the most friends?
     How many people have no friends?
     If there is a social connection between two people, direct or not
      (if I know you and you know someone else, there's a social connection between me and someone else)
   */

  val anotherPhonebook = Map(("Jim", 555), ("JIM", 789)).withDefaultValue(-1)
  println(anotherPhonebook.map(pair => pair._1.toLowerCase -> pair._2)) //lowercasing the keys

  //social network

  type SocialNetwork = Map[String, Set[String]]

  def Add(SN: SocialNetwork, Person: String): SocialNetwork =
    SN + (Person -> Set())

  def Friend(SN: SocialNetwork, a: String, b: String): SocialNetwork = {
    val friendsA = SN(a)
    val friendsB = SN(b)
    SN + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def Unfriend(SN: SocialNetwork, a: String, b: String): SocialNetwork = {
    val friendsA = SN(a)
    val friendsB = SN(b)
    SN + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def Remove(SN: SocialNetwork, Person: String): SocialNetwork = {
    def removeAux(friends: Set[String], networkAcc: SocialNetwork): SocialNetwork =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, Unfriend(networkAcc, Person, friends.head))

    val unfriended = removeAux(SN(Person), SN)
    unfriended - Person
  }

  val empty: SocialNetwork = Map()
  val network = Add(Add(empty, "Bob"), "Mary")
  println(network)
  println(Friend(network, "Bob", "Mary"))
  println(Remove(Unfriend(network, "Bob", "Mary"), "Bob"))

  val newNet = Add(Add(Add(Add(empty, "Bob"), "Mary"), "Jim"), "Jane")
  val jimBob = Friend(newNet, "Bob", "Jim")
  val testNet = Friend(jimBob, "Bob", "Mary")
  println(testNet)

  //get number of friends for a person
  def numberOfFriends(SN: SocialNetwork, Person: String): Int = SN(Person).size

  println(numberOfFriends(testNet, "Bob"))
  println(numberOfFriends(testNet, "Jane"))

  //get person with most friends
  def mostFriends(SN: SocialNetwork): String = SN.maxBy(pair => pair._2.size)._1

  println(mostFriends(newNet))

  //get number of people with no friends
  def numberOfLonely(SN: SocialNetwork): Int = SN.count(_._2.isEmpty)

  println(numberOfLonely(testNet))

  def socialConnection(SN: SocialNetwork, p1: String, p2: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val Person = discoveredPeople.head
        if (Person == target) true
        else if (consideredPeople.contains(Person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + Person, discoveredPeople.tail ++ network(Person))
      }
    }
    bfs(p2, Set(), testNet(p1) + p1)
  }

  println(socialConnection(testNet, "Mary", "Jim"))


}
