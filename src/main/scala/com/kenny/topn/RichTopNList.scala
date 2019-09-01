package com.kenny.topn

class RichTopNList[T](l: List[T])(implicit i: TopN[T]) {

  def topN(n: Int) = TopN.topN1(n, l)
}

object RichTupleTopNList {

  implicit def list2RichList(l: List[(String, String, String)]) = {
    implicit val tn = new TupleTopN
    new RichTopNList[(String, String, String)](l)
  }
}

