package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  var s1 = singletonSet(1)
  var s2 = singletonSet(2)
  var u = union(s1, s2)

  test("contains is implemented") {
    assert(contains(x => true, 100))
    assert(singletonSet(1)(1), "Singleton")
    assert(!singletonSet(1)(2), "1 != 2")
  }

  test("Union is completed") {
    assertEquals(u(1), true)
    assertEquals(u(2), true)
    assertEquals(u(3), false)
  }

  test("Intersect is completed") {
    var iS = intersect(s1, u)
    assertEquals(iS(1), true)
    assertEquals(iS(2), false)
    assertEquals(iS(3), false)
  }

  test("Diff is completed") {
    var dS = diff(u, s1)
    assertEquals(dS(1), false)
    assertEquals(dS(2), true)
    assertEquals(dS(3), false)
  }

  test("Filter is completed") {
    var fS = filter(u, x => x > 1)
    assertEquals(fS(1), false)
    assertEquals(fS(2), true)
    assertEquals(fS(3), false)
  }

  test("Forall is completed") {
    var fs1 = forall(u, x => x > 1)
    var fs2 = forall(u, x => x > 0)
    assertEquals(fs1, false)
    assertEquals(fs2, true)
  }

  test("Exist is completed") {
    var fs1 = exists(u, x => x > 1)
    var fs2 = exists(u, x => x > 3)
    assertEquals(fs1, true)
    assertEquals(fs2, false)
  }

  test("Map is completed") {
    var fs1 = map(u, x => x * 5)
    var fs2 = map(u, x => x + 1)
    assertEquals(contains(fs1, 5), true)
    assertEquals(contains(fs1, 1), false)
    assertEquals(contains(fs2, 3), true)
    assertEquals(contains(fs2, 1), false)
  }
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

  /**
   * This test is currently disabled (by using .ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
