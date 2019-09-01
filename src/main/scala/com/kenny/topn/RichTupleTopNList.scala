package com.kenny.topn

object RichTupleTopNList {

  implicit def list2RichList(l: List[(String, String, String)]) = {
    implicit val tn = new TupleTopN
    new RichTopNList[(String, String, String)](l)
  }
}
