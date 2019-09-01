/**
 * https://www.cnblogs.com/1zhk/p/4736356.html
 */

package com.kenny.demo

object ImplictDemo {

  class RichString(val s: String) {
    def read = (s + "_fun").mkString
  }

  object Context {
    implicit val impStr: String = "It is implicit"

    implicit def fun(s: String) = new RichString(s)

    implicit class myString(val s: String) {
      def read2 = (s + "_class").mkString
    }

  }


  object Param {
    def print(content: String)(implicit prefix: String) {
      println(prefix + ":" + content)
    }
  }

  def main(args: Array[String]) {
    Param.print("A")("It is not implicit")

    import Context.{fun, _}
    Param.print("B")
    println(new String("").read)
    println(new String("").read2)
  }

}
