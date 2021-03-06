package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter03

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/02/13.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  */
class Chapter03Spec extends FunSpec {

  override def suiteName: String = "[FP in Scala] 第3章 関数型プログラミングのデータ構造"

  it("[EXERCISE 3.1] match式") {
    val v = MyList(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _)))          => x
      case MyNil                                 => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t)                            => h + MyList.sum(t)
      case _                                     => 101
    }

    assert(v == 3)
  }

  it("[EXERCISE 3.2] tailの実装") {
    assert(MyList.tail(MyList(1, 2, 3)) == MyList(2, 3))
    assert(MyList.tail(MyList(1, 2)) == MyList(2))
    assert(MyList.tail(MyList(1)) == MyNil)
    assert(MyList.tail(MyNil) == MyNil)
  }

  it("[EXERCISE 3.3] setHeadの実装") {
    assert(MyList.setHead(MyList(1, 2, 3), 9) == MyList(9, 2, 3))
    assert(MyList.setHead(MyList(1, 2), 9) == MyList(9, 2))
    assert(MyList.setHead(MyList(1), 9) == MyList(9))
    assert(MyList.setHead(MyNil, 9) == MyNil)
  }

  it("[EXERCISE 3.4] dropの実装") {
    assert(MyList.drop(MyList(1, 2, 3), 0) == MyList(1, 2, 3))
    assert(MyList.drop(MyList(1, 2, 3), 1) == MyList(2, 3))
    assert(MyList.drop(MyList(1, 2, 3), 2) == MyList(3))
    assert(MyList.drop(MyList(1, 2, 3), 3) == MyNil)
    assert(MyList.drop(MyNil, 3) == MyNil)
  }

  it("[EXERCISE 3.5] dropWhileの実装") {
    assert(MyList.dropWhile(MyList(1, 2, 3), (i: Int) => i <= 3) == MyNil)
    assert(MyList.dropWhile(MyList(1, 2, 3), (i: Int) => i <= 2) == MyList(3))
    assert(MyList.dropWhile(MyList(1, 2, 3), (i: Int) => i <= 1) == MyList(2, 3))
    assert(MyList.dropWhile(MyList(1, 2, 3), (i: Int) => i <= 0) == MyList(1, 2, 3))

    // カリー化することで型推論が手助けされる
    // 引数リストの左の型パラメータが決まると、右の型パラメータが固定されるため
    assert(MyList.dropWhile2(MyList(1, 2, 3))(_ <= 3) == MyNil)
    assert(MyList.dropWhile2(MyList(1, 2, 3))(_ <= 2) == MyList(3))
    assert(MyList.dropWhile2(MyList(1, 2, 3))(_ <= 1) == MyList(2, 3))
    assert(MyList.dropWhile2(MyList(1, 2, 3))(_ <= 0) == MyList(1, 2, 3))
  }

  it("[EXERCISE 3.6] initの実装") {
    assert(MyList.init(MyList(1, 2, 3)) == MyList(1, 2))
    assert(MyList.init(MyList(1, 2)) == MyList(1))
    assert(MyList.init(MyList(1)) == MyNil)
    assert(MyList.init(MyNil) == MyNil)
  }

  it("[EXERCISE 3.9] lengthの実装") {
    assert(MyList.length(MyList(1, 2, 3)) == 3)
    assert(MyList.length(MyList(1, 2)) == 2)
    assert(MyList.length(MyList(1)) == 1)
    assert(MyList.length(MyNil) == 0)
  }

  it("[EXERCISE 3.10] foldLeftの実装") {
    assert(MyList.foldLeft(MyList(1, 2, 3, 4, 5), 0)(_ + _) == 15)
    assert(MyList.foldLeft(MyList(1, 2, 3, 4, 5), 1)(_ * _) == 120)
  }

  it("[EXERCISE 3.11] foldLeftを使った、sum, product, lengthの実装") {
    assert(MyList.sum2(MyList(1, 2, 3, 4, 5)) == 15)
    assert(MyList.product2(MyList(1, 2, 3, 4, 5)) == 120)
    assert(MyList.length(MyList(1, 2, 3, 4, 5)) == 5)
  }

  it("[EXERCISE 3.12] reverseの実装") {
    assert(MyList.reverse(MyList(1, 2, 3)) == MyList(3, 2, 1))
    assert(MyList.reverse(MyNil) == MyNil)
  }

  it("[EXERCISE 3.14] foldRitghtを利用したappendの実装") {
    assert(MyList.appendViaFoldRight(MyList(1, 2), MyList(3, 4)) == MyList(1, 2, 3, 4))
  }

  it("[EXERCISE 3.15] flattenの実装") {
    val listInList = MyList(MyList(1, 2), MyList(3, 4))
    assert(MyList.flatten(listInList) == MyList(1, 2, 3, 4))
    assert(MyList.flatten2(listInList) == MyList(1, 2, 3, 4))
  }
}
