package Exercises
/*
abstract class MyList3[+A] {

  def head: A
  def tail: MyList3[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList3[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def map[B](transformer: myTransformer[A, B]): MyList3[B]
  def flatMap[B](transformer: myTransformer[A, MyList3[B]]): MyList3[B]
  def filter(predicate: myPredicate[A]): MyList3[A]

}

object Empty extends MyList3[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList3[B] = new Cons(element, Empty)
  def printElements: String = ""
  def map[B](transformer: myTransformer[Nothing, B]): MyList3[B] = Empty
  def flatMap[B](transformer: myTransformer[Nothing, MyList3[B]]): MyList3[B] = Empty
  def filter(predicate: myPredicate[Nothing]): MyList3[Nothing] = Empty
}

class Cons[+A](h: A, t: MyList3[A]) extends MyList3[A] {
  def head: A = h
  def tail: MyList3[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList3[B] = new Cons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
  def filter(predicate: myPredicate[A]): MyList3[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  def map[B](transformer: myTransformer[A, B]): MyList3[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  def flatMap[B](transformer: myTransformer[A, MyList3[B]]): MyList3[B] = ???
}

trait myPredicate[-T] {
  def test(elem: T): Boolean
}

trait myTransformer[-A, B] {
  def transform(elem: A): B
}

object ListTest extends App {
  val listOfIntegers: MyList3[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList3[String] = new Cons("Manny", new Cons("Moe", new Cons("Jack", Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new myTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)
}

 */