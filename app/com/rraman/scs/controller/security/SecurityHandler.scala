package com.rraman.scs.controller.security

import com.rraman.scs.controller.BaseController
import play.api.mvc.{ActionBuilder, Request, Result}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
object Authentication extends BaseController {
  def checkToken(token: String, secret: String): Boolean = (token,secret) match {
    case ("rewati","password") => true
    case _ => false
  }

  def isAuthenticated[A](request: Request[A]): Boolean = {
    val token = request.headers.get("x-auth-token").getOrElse("")
    val secret = request.headers.get("x-auth-secret").getOrElse("")
    checkToken(token, secret)
  }
}

object IfAuthorized extends  ActionBuilder[Request]  with BaseController {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) =>
    Future[Result]): Future[Result] = Authentication.isAuthenticated(request) match {
    case false => Future(---)
    case _ => block(request)
  }
}

