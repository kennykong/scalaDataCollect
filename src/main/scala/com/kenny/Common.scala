package com.kenny

object Common {
  def matchString(content: String, reg: String): List[String] = {
    val p = reg.r
    p.findAllIn(content).toList
  }

  def replaceWith(content: String, sourceString: String, targetString: String): String = {
    content.replace(sourceString, targetString)
  }

  def replaceCommaToSafe(line: String): String = {
    //析出引号内的文本
    val l = matchString(line, "\"(.+?)\"")
    //引号内的,替换成_
    val rl = l.map(x => (x, x.replace(",", "_")))
    //整行处理成安全的字符，引号内的逗号和引号外的逗号区别开来
    var resString = line
    for (t1 <- rl) {
      resString = replaceWith(resString, t1._1, t1._2)
    }
    resString
  }

  def main(args: Array[String]): Unit = {
    val s = "\"27, most popular\",127.6053254325764,6.03051434574081"
    val ss = "\"27, most popular\",127.6053254325764,6.03051434574081,\"27, most popular1\""
    val l = matchString(ss, "\"(.+?)\"")
    println("l:" + l)
    val rl = l.map(x => (x, x.replace(",", "_")))
    println("rl:" + rl)

    /*
    val res = for {
      t1 <- rl
    } yield {
      replaceWith(ss, t1._1, t1._2)
    }

    val res1 = rl.map(x => replaceWith(ss, x._1, x._2))

     */


    var resString = ss
    val res2 = for (t1 <- rl) {
      resString = replaceWith(resString, t1._1, t1._2)
    }

    println("res:" + resString)
  }
}
