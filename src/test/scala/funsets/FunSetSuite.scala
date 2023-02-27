package funsets

/** This class is a test suite for the methods in object FunSets.
  *
  * To run this test suite, start "sbt" then run the "test" command.
  */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val u = union(s1, s2)

  test("contains is implemented") {
    assert(contains(x => true, 100))
    assert(singletonSet(1)(1), "Singleton")
    assert(!singletonSet(1)(2), "1 != 2")
  }

  test("Union is completed") {
    assert(contains(u, 1))
    assert(contains(u, 2))
    assert(!contains(u, 3))
  }

  test("Intersect is completed") {
    val iS = intersect(s1, u)
    assert(contains(iS, 1))
    assert(!contains(iS, 2))
    assert(!contains(iS, 3))
  }

  test("Diff is completed") {
    val dS = diff(u, s1)
    assert(!contains(dS, 1))
    assert(contains(dS, 2))
    assert(!contains(dS, 3))
  }

  test("Filter is completed") {
    val fS = filter(u, x => x > 1)
    assert(!contains(fS, 1))
    assert(contains(fS, 2))
    assert(!contains(fS, 3))
  }

  test("Forall is completed") {
    assert(!forall(u, x => x > 1))
    assert(forall(u, x => x > 0))
  }

  test("Exist is completed") {
    assert(exists(u, x => x > 1))
    assert(!exists(u, x => x > 3))
  }

  test("Map is completed") {
    val fs1 = map(u, x => x * 5)
    val fs2 = map(u, x => x + 1)
    assert(contains(fs1, 5))
    assert(!contains(fs1, 1))
    assert(contains(fs2, 3))
    assert(!contains(fs2, 1))
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
