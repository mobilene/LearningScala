package Lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  //unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(null) -- WRONG
  val result = Option(unsafeMethod()) //avoids null check
  println(result)  //s/b none

  //chained methods - if first is null then do second
  def backupMethod(): String = "valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  //if you design unsafe apis, then make methods return option of something
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  //functions on options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //unsafe - can get NPE - do not use

  //map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  //for-comprehensions

  /*
  Exercise
   */

  val config: Map[String, String] = Map(
    //fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // in reality would connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  //try to establish a connection, if so, print connect method

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /* above meaning:
    if (h != null) && (p != null)
      return Connection.apply(h, p)
    else return null
   */
  val connectionStatus = connection.map(c => c.connect)
  /* above meaning
    if (c != null)
      return c.connect
    else return null
   */
  println(connectionStatus)
  /* above meaning
    if connectionStatus = null println(None)
    else print(Some(connectionstatus.get)
   */
  connectionStatus.foreach(println)
  /* above meaning
    if (status != null) println(status)
   */

  //chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host,port))
      .map(connection => connection.connect))
  .foreach(println)

  //for-comprehensions
  val forconnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect


}
