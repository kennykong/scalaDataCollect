package com.kenny.topn

class TupleTopN extends TopN[(String, String, String)] {

  override implicit def compareWith(tup: (String, String, String)): Double = {
    tup._3.toDouble
  }
}
