package com.rraman.scs.controller

import play.api.mvc._

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
class FrontController extends BaseController {

  def getHome = Action { r =>
    Ok(views.html.index2())
  }

  def getCategory(id: String) = Action { r =>
    Ok(views.html.index2())
  }

  def getArticle(yy: Int,mm: Int,dd: Int,title: String) = Action { r =>
    Ok(views.html.frontendArticle())
  }
}
