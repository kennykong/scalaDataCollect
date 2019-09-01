package com.kenny.topn

import scala.collection.mutable.ListBuffer

/**
 * TopN 要抽象成选择排序，类似有sortWith方法
 */
trait TopN[T] {

  def topNMax(n: Int, list: List[T]): List[T] = {

    val mList = new ListBuffer[T]().addAll(list)
    val list1 = for (i <- 1 to n) yield {
//      println(i)
      val max = mList.maxBy(compareWith)
      val i1 = mList.indexOf(max)
      mList.remove(i1)
    }
    list1.toList
  }

  implicit def compareWith(f: T): Double
}

object TopN {
  def topN1[A: TopN](n:Int, xs: List[A]): List[A] = {
    val topN = implicitly[TopN[A]]
    topN.topNMax(n,xs)
  }

}

object TopNMain extends TopN[Double] {

  def main(args: Array[String]): Unit = {
    implicit val list: List[Double] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topNMax(5,list))
    // res1: List[Double] = List(1.0, 1.0, 2.0, 2.0, 3.0)

    implicit val list1: List[Double] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topNMax(5,list1))
    // res2: List[Int] = List(1, 1, 2, 2, 3)
  }

  override implicit def compareWith(f: Double): Double = f
}

