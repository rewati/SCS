package com.rraman.scs.controller.security

import java.util.Base64

import com.rraman.scs.controller.BaseController
import org.joda.time.DateTime
import play.api.mvc.{ActionBuilder, Request, Result, Session}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
object IfAuthorized extends  ActionBuilder[Request]  with BaseController {

  val map: Map[String, String] = Map()
  val seperator = "#####"

  override def invokeBlock[A](request: Request[A], block: ((Request[A])) =>
    Future[Result]): Future[Result] = {
    val a = isAuthenticated(request.session)
    a match {
      case Success(x) => {
        val s = request.session + ("x-auth-token",x)
          block(request).map(r => r.withSession(s))
      }
      case _ => Future(---)
    }
  }

  def checkToken(token: Option[String]): Option[String] = token match {
    case Some(t) => {
      try{
        val x = Authorize.decode(t)
        val m = x.split(seperator)
        val date: Long = m(1).toLong
        val now = DateTime.now()
        val diff = ((now.minusMinutes(3)).getMillis - date)
        (diff < 300000) match {
          case true => {
            val key: String = m(0).trim +seperator+now.getMillis
            Authorize.encode(key)
          }
          case _ => None
        }
      } catch {
        case e:Exception => None
      }

    }
    case _ => None
  }

  def isAuthenticated[A](session: Session): Try[String] = {
    val token = session.data.get("x-auth-token")
    checkToken(token) match {
      case Some(x) =>
        Success(x)
      case _ => new Failure(new NoSuchElementException)
    }
  }
}

object Authorize {
  val encoder = Base64.getEncoder
  val decoder = Base64.getDecoder
  def apply[A](usrPass: (String,String,Request[A])): Option[String] = {
      val (username, password, request) = usrPass
      (username, password) match {
        case ("rewati", "password12") => {
          val key: String = username + IfAuthorized.seperator + DateTime.now.getMillis
          encode(key)
        }
        case _ => None
      }
  }

  def encode(key: String): Option[String] = Some(encoder.encode(key.getBytes()).toString)
  def decode(token: String): String = decoder.decode(token.getBytes).toString
}

case class Role(name: String)



