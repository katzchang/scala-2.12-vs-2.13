package hello

import org.scalatest.FreeSpec


class Hello1 extends FreeSpec {
  import fuga._
  import Hoge._

  "Hoge" in {
    assert(hello == "world")
  }

}
