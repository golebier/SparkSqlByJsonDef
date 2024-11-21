package gra.tests.common

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

trait TestBeforeAndAfter extends AnyFunSuite with Matchers with BeforeAndAfter {
 System.setProperty("env", "test")
}
