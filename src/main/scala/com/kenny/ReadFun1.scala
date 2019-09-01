package com.kenny

/**
 * 函数式写法，用到了通用方式处理逗号，和topN(用TypeClass实现)
 */
object ReadFun1 extends App {

  val st = System.currentTimeMillis()
  val content = "https://gist.githubusercontent.com/CatTail/18695526bd1adcc21219335f23ea5bea/raw/54045ceeae6a508dec86330c072c43be559c233b/movies.csv"

  import scala.io.Source

  val html = Source.fromURL(content)
  val s = html.mkString
  //  println(s)
  val mi = System.currentTimeMillis()
  println("read html spent:" + (mi - st))

  val res = for (line <- s.split("\n")) yield {
    //可以先把"xxx"模式匹配出来，把里面的","替换掉，再通过","split

    val line1 = Common.replaceCommaToSafe(line)
    val cols = line1.split(",").map(_.trim)

    val value = (cols(0), cols(1), cols(2))
    //    println(s"${cols(0)}|${cols(1)}|${cols(2)}")
    value
  }
  //  val values = res.toList.filter(x => x._1 != "name").sortWith(_._3.toDouble > _._3.toDouble).take(10)
  //  type class 实现了 topN

  //  import com.kenny.topn.RichTupleTopNList._

  //  val values = res.toList.filter(x => x._1 != "name").topN(10)

  // 实现了类型推断

  import com.kenny.topn1.RicTopNList1._

  val values = res.toList.filter(x => x._1 != "name").topN1With(10, _._3.toDouble)

  val ed = System.currentTimeMillis()
  println("sort spent:" + (ed - mi))
  println(values)
}




