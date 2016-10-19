package com.rraman.scs.controller

import com.rraman.scs.controller.security.{Authorize, IfAuthorized}
import play.api.mvc._

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
class AdminController extends BaseController  {
  val validUsername = "rewati"
  val validPassword = "password"

  def getLoginPage = Action { r =>
    Ok(views.html.adminLogin())
  }

  def getLogin = Action (parse.anyContent) { r =>
    val body = r.body.asFormUrlEncoded.get
    val c = r.uri
    val username = body.get("username").get(0)
    val password = body.get("password").get(0)
    Authorize.apply(username,password,r) match {
      case Some(x) => {
        Redirect("/admin/dashboard").withSession(r.session + ("x-auth-token",x))
      }
      case _ => Redirect("/admin/login")
    }

  }

  def dashBoard = IfAuthorized { r =>
    Ok("jdnvaibv")
  }

}
