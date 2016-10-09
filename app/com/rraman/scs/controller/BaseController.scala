package com.rraman.scs.controller

import play.api.libs.json.{Json, Writes}
import play.api.mvc._


/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
trait BaseController extends Controller {

  def <<<[A](input: *?*)(implicit writes: Writes[A]): Result = input match {
    case x:Good[A] => Ok(Json.toJson(x.dto))
    case x:NotFound[A] => NotFound(Json.toJson(x.dto))
    case _ => NotFound
  }

  def --- = {
    Unauthorized("{\"errors\":[{\"message\":\"Sorry, UNAUTHORIZED\",\"code\":401}]}")
  }
}

trait *?*
case class Good[A](dto: A) extends *?*
case class NotFound[A](dto: A) extends *?*





