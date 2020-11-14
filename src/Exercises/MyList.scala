package Exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
  def map[B](transformer: myTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: myTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: myPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
  def map[B](transformer:myTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: myTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: myPredicate[Nothing]): MyList[Nothing] = Empty
  def ++[B >: Nothing](list:MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
  def map[B](transformer: myTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  override def flatMap[B](transformer: myTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
  override def filter(predicate: myPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
}

trait myTransformer[-A, B]{
  def transform(elem: A): B
}

trait myPredicate[-T] {
  def test(elem: T): Boolean
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherlistOfIntegers: MyList[Int] = new Cons(4, new Cons(4, Empty))
  val listOfStrings: MyList[String] = new Cons("Tom", new Cons("Dick", new Cons("Harry", Empty)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.map(new myTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }))
  println(listOfIntegers.filter(new myPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)
  println(listOfIntegers ++ anotherlistOfIntegers).toString
  println(listOfIntegers.flatMap(new myTransformer[Int, MyList[Int]]{
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).toString)
}
