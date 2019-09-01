/**
 * https://blog.genuine.com/2018/06/generic-top-n-elements-in-scala/
 */

package com.kenny.demo

object Score {
  // Top N elements (with smallest rank number) from a list of case classes

  case class Score[T: Numeric](id: String, rank: T)

  def topN[T](n: Int, list: List[Score[T]])(implicit num: Numeric[T]): List[Score[T]] = {

    import num.mkOrderingOps

    def bigHead(l: List[Score[T]]): List[Score[T]] = list match {
      case Nil => list
      case _ =>
        l.tail.foldLeft(List(l.head))((acc, x) =>
          if (x.rank >= acc.head.rank) x :: acc else acc :+ x
        )
    }

    def update(l: List[Score[T]], e: Score[T]): List[Score[T]] = {
      if (e.rank < l.head.rank) bigHead((e :: l.tail)) else l
    }

    list.drop(n).foldLeft(bigHead(list.take(n)))(update).
      sortWith(_.rank < _.rank)
  }

  def main(args: Array[String]): Unit = {
    val list = List(
      Score("a", 3), Score("b", 6), Score("c", 8), Score("d", 1), Score("e", 11),
      Score("f", 5), Score("g", 9), Score("h", 12), Score("i", 2), Score("j", 10)
    )
    println(topN[Int](5, list))

    // res1: List[Score[Int]] = List(Score(d,1), Score(i,2), Score(a,3), Score(f,5), Score(b,6))
  }
}
