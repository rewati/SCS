package com.rraman.scs.controller.security

import org.scalatest.FunSuite

/**
  * Created by Rewati Raman(rewati.raman@gmail.com).
  */
class ScSAuthentication$Test extends FunSuite {

  test("Test create security conf map from conf file") {
    val confMap = ScSAuthentication.createSecurityConfMap
    val roleAuth = ScSAuthentication.roleAuthMap
    assert(confMap.size === 2)
    assert(confMap.get("/admin") === Some(List("ADMIN", "READER")))
    assert(roleAuth.get("/admin") === Some(List(Role("ADMIN"), Role("READER"))))
  }

}
