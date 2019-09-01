package com.kenny.topn1

class RichTopNList1[T](l: List[T])(implicit i: TopN1[T]) {

  def topN1With(n: Int, f: T => Double) = TopN1.topN1(n, l, f)
}

object RicTopNList1 {

  implicit def list2RichList[T](l: List[T]) = {
    implicit val tn = new TopN1Comp[T]
    new RichTopNList1[T](l)
  }
}
