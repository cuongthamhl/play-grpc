/*
 * Copyright (C) 2018-2019 Lightbend Inc. <https://www.lightbend.com>
 */
package play.grpc.gen.scaladsl

import akka.grpc.gen.scaladsl.Service
import org.scalatest.Matchers
import org.scalatest.WordSpec

class PlayScalaClientCodeGeneratorSpec extends WordSpec with Matchers {

  "The PlayScalaClientCodeGenerator" must {

    "choose the single package name" in {
      PlayScalaClientCodeGenerator
        .packageForSharedModuleFile(Seq(Service("a.b", "MyService", "???", Nil, false, false))) should ===("a.b")
    }

    "choose the longest common package name" in {
      PlayScalaClientCodeGenerator
        .packageForSharedModuleFile(
          Seq(
            Service("a.b.c", "MyService", "???", Nil, false, false),
            Service("a.b.e", "OtherService", "???", Nil, false, false),
          ),
        ) should ===("a.b")
    }

    "choose the root package if no common packages" in {
      PlayScalaClientCodeGenerator
        .packageForSharedModuleFile(
          Seq(
            Service("a.b.c", "MyService", "???", Nil, false, false),
            Service("c.d.e", "OtherService", "???", Nil, false, false),
          ),
        ) should ===("")
    }
  }

}
