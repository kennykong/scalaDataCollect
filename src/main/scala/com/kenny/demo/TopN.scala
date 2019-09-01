/**
 * https://blog.genuine.com/2018/06/generic-top-n-elements-in-scala/
 */

package com.kenny.demo

object TopN {


  // Top N (smallest) elements from a generic numeric list

  def topN[T](n: Int, list: List[T])(implicit num: Numeric[T]): List[T] = {
    import num.mkOrderingOps
    def bigHead(l: List[T]): List[T] = list match {
      case Nil => list
      case _ =>
        l.tail.foldLeft( List(l.head) )( (acc, x) =>
          if (x >= acc.head) x :: acc else acc :+ x
        )
    }
    def update(l: List[T], e: T): List[T] = {
      if (e < l.head) bigHead(e :: l.tail) else l
    }
    list.drop(n).foldLeft( bigHead(list.take(n)) )( update ).sortWith(_ < _)
  }



  def main(args: Array[String]): Unit = {
    val list: List[Double] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topN[Double](5, list))
    // res1: List[Double] = List(1.0, 1.0, 2.0, 2.0, 3.0)

    val list1: List[Int] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topN[Int](5, list1))
    // res2: List[Int] = List(1, 1, 2, 2, 3)
  }
}
