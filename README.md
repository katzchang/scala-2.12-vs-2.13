# scala 2.12 vs 2.13

Scala 2.12から2.13へのアップデートを試みたときに気づいたimportの挙動の違いについての検証コードです。

`_` を用いたimportのときの挙動がかわり、おそらくより一貫性のあるものになっています。どちらにせよ動作の違いはあるので、

* いままでコンパイルが通っていたものが通らなくなった
* 同じシグネチャのメソッドを持つ別のクラスが参照されるようになる場合、実行時の動作がかわる

のような現象が想定できるので気をつけましょう。特に後者はテストがカバーされていないと気づけないので、要注意です。

## 動かし方

```bash
make test
```

## 結果

* テスト `Hoge1` はどちらでも失敗する
* テスト `Hoge2` は 2.12 だと成功するが、 2.13 だと失敗する

2.12まではimportの記述位置の違いによってどちらのクラスが参照されるか動作が異なったが、2.13では一貫性のある参照がされるようになったと言えそうです。

```
$ make test
./sbt "++ 2.12.10 -v test"
[info] Loading global plugins from /Users/k-ohtani/.sbt/1.0/plugins
[info] Loading project definition from /Users/k-ohtani/dev/scala-2.12-vs-2.13/project
[info] Loading settings for project scala-2-12-vs-2-13 from build.sbt ...
[info] Set current project to scala-2-12-vs-2-13 (in build file:/Users/k-ohtani/dev/scala-2.12-vs-2.13/)
[info] Setting Scala version to 2.12.10 on 1 projects.
[info] Switching Scala version on:
[info]   * scala-2-12-vs-2-13 (2.12.10, 2.13.1)
[info] Excluding projects:
[info] Reapplying settings...
[info] Set current project to scala-2-12-vs-2-13 (in build file:/Users/k-ohtani/dev/scala-2.12-vs-2.13/)
[info] Hello1:
[info] - Hoge *** FAILED ***
[info]   "[fugafuga]" did not equal "[world]" (Hello1.scala:11)
[info] Hello2:
[info] - Hoge
[info] Run completed in 672 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 2, aborted 0
[info] Tests: succeeded 1, failed 1, canceled 0, ignored 0, pending 0
[info] *** 1 TEST FAILED ***
[error] Failed tests:
[error]         hello.Hello1
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 5 s, completed 2019/10/11 12:09:28
make: [test] Error 1 (ignored)
./sbt "++ 2.13.1 -v test"
[info] Loading global plugins from /Users/k-ohtani/.sbt/1.0/plugins
[info] Loading project definition from /Users/k-ohtani/dev/scala-2.12-vs-2.13/project
[info] Loading settings for project scala-2-12-vs-2-13 from build.sbt ...
[info] Set current project to scala-2-12-vs-2-13 (in build file:/Users/k-ohtani/dev/scala-2.12-vs-2.13/)
[info] Setting Scala version to 2.13.1 on 1 projects.
[info] Switching Scala version on:
[info]   * scala-2-12-vs-2-13 (2.12.10, 2.13.1)
[info] Excluding projects:
[info] Reapplying settings...
[info] Set current project to scala-2-12-vs-2-13 (in build file:/Users/k-ohtani/dev/scala-2.12-vs-2.13/)
[info] Compiling 2 Scala sources to /Users/k-ohtani/dev/scala-2.12-vs-2.13/target/scala-2.13/classes ...
[info] Compiling 2 Scala sources to /Users/k-ohtani/dev/scala-2.12-vs-2.13/target/scala-2.13/test-classes ...
[info] Hello2:
[info] - Hoge *** FAILED ***
[info]   "[fugafuga]" did not equal "[world]" (Hello2.scala:11)
[info] Hello1:
[info] - Hoge *** FAILED ***
[info]   "[fugafuga]" did not equal "[world]" (Hello1.scala:11)
[info] Run completed in 1 second, 549 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 2, aborted 0
[info] Tests: succeeded 0, failed 2, canceled 0, ignored 0, pending 0
[info] *** 2 TESTS FAILED ***
[error] Failed tests:
[error]         hello.Hello1
[error]         hello.Hello2
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 16 s, completed 2019/10/11 12:10:12
make: [test] Error 1 (ignored)
```
