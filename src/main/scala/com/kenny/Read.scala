package com.kenny

/**
 * 传统写法
 */
object Read extends App {

  val st = System.currentTimeMillis()
  val content = "https://gist.githubusercontent.com/CatTail/18695526bd1adcc21219335f23ea5bea/raw/54045ceeae6a508dec86330c072c43be559c233b/movies.csv"

  import scala.io.Source

  val html = Source.fromURL(content)
  val s = html.mkString
  //  println(s)
  val mi = System.currentTimeMillis()
  println("read html spent:" + (mi - st))

  import scala.collection.mutable.ListBuffer

  var fruits = new ListBuffer[(String, String, String)]()

  for (line <- s.split("\n")) {

    //可以先把"xxx"模式匹配出来，把里面的","替换掉，再通过","split
    val cols = line.split(",").map(_.trim)
    val start = cols.filter(x => x.startsWith("\""))
    val end = cols.filter(x => x.endsWith("\""))

    val value = if (!start.isEmpty && !end.isEmpty) {
      (start(0) + "," + end(0), cols(2), cols(3))
    } else {
      (cols(0), cols(1), cols(2))
    }
    //    println(s"${cols(0)}|${cols(1)}|${cols(2)}")
    // 非函数式写法
    fruits += value

  }

  val values = fruits.filter(x => x._1 != "name").sortWith(_._3.toDouble > _._3.toDouble).take(10).toList

  val ed = System.currentTimeMillis()
  println("sort spent:" + (ed - mi))
  println(values)
  values.map(println)
}

