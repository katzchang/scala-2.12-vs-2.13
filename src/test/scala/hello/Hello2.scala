package hello

import org.scalatest.FreeSpec

import fuga._

class Hello2 extends FreeSpec {
  import Hoge._

  "Hoge" in {
    assert(hello == "world")
  }

}
