package com.kenny.topn

class RichTopNList[T](l: List[T])(implicit i: TopN[T]) {

  def topN(n: Int) = TopN.topN(n, l)
}


