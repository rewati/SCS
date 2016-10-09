package com.rraman.scs.controller

import com.rraman.scs.controller.security.IfAuthorized
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

  def getLogin = IfAuthorized (parse.anyContent) { r =>
    val body = r.body.asFormUrlEncoded.get
    val username = body.get("username").get(0)
    val password = body.get("password").get(0)
    (username,password) match {
      case (validUsername,validPassword)  => {
        Redirect("/admin/dashboard")
      }
      case _ => {
        Redirect("/admin/login")
      }
    }
  }

}
