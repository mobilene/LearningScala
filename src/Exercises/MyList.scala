package Exercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  //polymorphic call
  override def toString: String = "[" + printElements + "]"
  //some HOFs
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  //concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
  //more HOFs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing=> Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list:MyList[B]): MyList[B] = list
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists not same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
  def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(h), t.map(transformer))
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)
  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }
  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons (sortedList.head, insert(x, sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists not same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}

/* trait myTransformer[-A, B]{
  def transform(elem: A): B
}

trait myPredicate[-T] {
  def test(elem: T): Boolean
} */

case object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val anotherlistOfIntegers: MyList[Int] = Cons(4, Cons(4, Empty))
  val listOfStrings: MyList[String] = Cons("Tom", Cons("Dick", Cons("Harry", Empty)))
  println(listOfIntegers)
  println(listOfStrings)
  println(listOfIntegers.map(elem => elem * 2))   //can also say _ * 2
  println(listOfIntegers.filter(elem => elem % 2 == 0).toString)  // can also say _ % 2 == 0
  println(listOfIntegers ++ anotherlistOfIntegers)
  println(listOfIntegers.flatMap(elem => Cons(elem, Cons(elem + 1, Empty))).toString)

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x, y) => y - x))
  println(listOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combos = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combos)
}
