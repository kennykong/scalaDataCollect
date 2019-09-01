/**
 * https://blog.csdn.net/qq_36330643/article/details/77866144
 *
 * https://my.oschina.net/scipio/blog/282795?p=1
 */

package com.kenny.demo

import java.io.File

import scala.io.Source

class RichFile(file: File) {
  def read = Source.fromFile(file.getPath()).mkString
}

object Context {
  implicit def file2RichFile(f: File) = new RichFile(f)
}

object ImplictDemo1 {

  def main(args: Array[String]) {
    import Context.file2RichFile
    println(new File("f:\\create.sql").read)
  }

}
