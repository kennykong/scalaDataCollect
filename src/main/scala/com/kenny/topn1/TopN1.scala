package com.kenny.topn1

import scala.collection.mutable.ListBuffer

/**
 * TopN 要抽象成选择排序，类似有sortWith方法
 */
trait TopN1[T] {

  def topNMax(n: Int, list: List[T], f: T => Double): List[T] = {

    val mList = new ListBuffer[T]().addAll(list)
    val list1 = for (i <- 1 to n) yield {
      //      println(i)
      val max = mList.maxBy(f)
      val i1 = mList.indexOf(max)
      mList.remove(i1)
    }
    list1.toList
  }

}

object TopN1 {
  def topN1[A: TopN1](n: Int, xs: List[A], f: A => Double): List[A] = {
    val topN = implicitly[TopN1[A]]
    topN.topNMax(n, xs, f)
  }

}

object TopN1Main extends TopN1[Double] {

  def main(args: Array[String]): Unit = {
    implicit val list: List[Double] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topNMax(5, list, _))
    // res1: List[Double] = List(1.0, 1.0, 2.0, 2.0, 3.0)

    implicit val list1: List[Double] = List(3, 2, 8, 2, 9, 1, 5, 5, 9, 1, 7, 3, 4)
    println(topNMax(5, list1, _))
    // res2: List[Int] = List(1, 1, 2, 2, 3)
  }

}

