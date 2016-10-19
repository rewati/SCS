package com.rraman.scs.controller.security

import java.io.InputStream

import scala.collection.mutable
import scala.io.Source

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
object ScSAuthentication {

  def createSecurityConfMap = {
    val stream : InputStream = getClass.getResourceAsStream("/resources/security")
    val confLines = Source.fromInputStream(stream).getLines
    val map = mutable.Map[String,List[String]]()
    confLines.foreach(s => {
      val a = s.split("==>")
      map+=(a(0).trim->a(1).trim.split("%").toList)
    })
    map.toMap
  }

  val roleAuthMap: Map[String, List[Role]] = {
    for {
      x <- createSecurityConfMap
      a = x._2.map( x => Role(x))
    } yield (x._1,a)
  }

}
