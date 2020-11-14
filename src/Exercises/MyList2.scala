package Exercises

object TestList {
  abstract class MyList2 {
    /*
        Implement a singly linked list of Integers
        methods:
        head = returns first element of the list
        tail = remainder of the list
        isEmpty = is this list empty
        add(int) => new list with this element add to the head
        toString => a string representation of the list
     */
    def head: Int
    def tail: MyList2
    def isEmpty: Boolean
    def add(x: Int): MyList2
    def printElements: String
    override def toString: String = s"[$printElements]"
  }
  object Empty extends MyList2 {
    def head: Int = throw new NoSuchElementException("head of empty list")
    def tail: MyList2 = throw new UnsupportedOperationException("tail of empty list")
    val isEmpty: Boolean = true
    def add(x: Int): MyList2 = new Cons(x)
    def printElements: String = "Nil"
  }
  class Cons(h: Int, t: MyList2 = Empty) extends MyList2 {
    def head: Int = h
    def tail: MyList2 = t
    val isEmpty: Boolean = false
    def add(x: Int): MyList2 = new Cons(x, this)
    def printElements: String = {
      @scala.annotation.tailrec
      def helper(n: MyList2, acc: String): String =
        if (n.isEmpty) acc
        else helper(n.tail, acc = s"$acc ${n.head}")
    helper(this, "")
    }
  }
}
object runner extends App {
  import TestList._
  val l = Empty.add(1).add(2).add(3)
  println(l.toString)
  val l3 = new Cons(1, Empty)
  println(l3.toString)
  val l4 = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(l4.toString)
}