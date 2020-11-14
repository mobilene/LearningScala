package Lectures.part1basics

object defaultArgs extends App {

  def trF(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trF(n-1, n*acc)
  }

  val fact10 = trF(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("whatever")
  savePicture(width = 800)

}
