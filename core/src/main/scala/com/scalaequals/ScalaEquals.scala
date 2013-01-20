package com.scalaequals

import scala.language.experimental.macros

/** Main entry point for ScalaEquals
  *
  * Examples:
  * Test equality with only vals in constructor:
  * {{{
  *   class Test(x: Int, val y: Int, private val z: Int, var a: Int) {
  *     val w: Int = x * z      // Ignored by equalAllVals
  *     override def equals(other: Any) = ScalaEquals.equal(other)
  *     override def hashCode() = ScalaEquals.hash
  *     def canEqual(other: Any) = ScalaEquals.canEqual(other)
  *   }
  *
  *   new Test(0, 1, 2, 3) == new Test(1, 1, 2, 4) // true -> y == y and z == z
  *   new Test(0, 2, 2, 3) == new Test(1, 1, 2, 4) // false -> y != y and z == z
  * }}}
  *
  *
  * Test equality with vals in constructor AND body:
  * {{{
  *   class Test(x: Int, val y: Int, private val z: Int, var a: Int) {
  *     val w: Int = x * z
  *     def q: Int = x        // Ignored by equalAllVals
  *     override def equals(other: Any) = ScalaEquals.equalAllVals(other)
  *     override def hashCode() = ScalaEquals.hash
  *     def canEqual(other: Any) = ScalaEquals.canEqual(other)
  *   }
  *
  *   new Test(0, 1, 2, 3) == new Test(1, 1, 2, 4) // false -> y == y and z == z and w != w
  *   new Test(0, 2, 2, 3) == new Test(1, 1, 2, 4) // false -> y != y and z == z and w != w
  *   new Test(1, 1, 2, 3) == new Test(1, 1, 2, 4) // true -> y == y and z == z and w == w
  * }}}
  *
  * Test equality with selected parameters:
  * {{{
  *   class Test(x: Int, val y: Int, private val z: Int, var a: Int) {
  *     def w: Int = x * z
  *     override def equals(other: Any) = ScalaEquals.equal(other, w, z)
  *     override def hashCode() = ScalaEquals.hash
  *     def canEqual(other: Any) = ScalaEquals.canEqual(other)
  *   }
  *
  *   new Test(1, 2, 2, 3) == new Test(1, 1, 2, 4) // false -> w == w and a != a
  *   new Test(1, 2, 2, 4) == new Test(1, 1, 2, 4) // true -> w == w and a == a
  * }}}
  *
  */
object ScalaEquals {
  /**
   * Equality check using all private/protected/public/lazy vals of the class
   * defined in the constructor
   *
   * @param other the instance to compare to
   * @return true if instance.equals(other)
   */
  def equal(other: Any): Boolean = macro EqualsImpl.equalCImpl

  /**
   * Equality check using all private/protected/public/lazy vals of the class
   * defined in the constructor AND the body of the class
   *
   * @param other the instance to compare to
   * @return true if instance.equals(other)
   */
  def equalAllVals(other: Any): Boolean = macro EqualsImpl.equalImpl

  /**
   * Equality check using only parameters passed in to test for equality. Example:
   *
   * {{{
   * final class Test(val x: Int, var y: Int) {
   *   def z: Int
   *   override def equals(other: Any): Boolean = ScalaEquals.equal(other, y, z)
   * }
   * }}}
   *
   * `Test`'s `equals` method will check only `y` and `z`.
   *
   * Acceptable arguments include private/protected/public vals, vars, lazy vals,
   * and defs with no arguments
   *
   * @param other the instance to test for equality
   * @param param first param to test with
   * @param params rest of the params
   * @return true if instance.equals(other)
   */
  def equal(other: Any, param: Any, params: Any*): Boolean = macro EqualsImpl.equalParamImpl

  /**
   * Looks up the elements tested in `equals` (including `super.equals`) and uses them
   * in `java.util.Objects.hash(elements)`. Works with all 3 forms of `equal`. Does not
   * work with custom `equals` implementations, one of `ScalaEquals.equal(other)`,
   * `ScalaEquals.equal(other, params)`, or `ScalaEquals.equal(other)` must be used
   *
   * MUST BE CALLED AFTER `ScalaEquals.equal` IN THE CLASS DEFINITION.
   *
   * @return hashCode generated from fields used in `equals`
   */
  def hash: Int = macro HashImpl.hash

  /**
   * Simple macro that expands to the following:
   * {{{
   *   other.isInstanceOf[Class]
   * }}}
   *
   * @param other the instance to test for equality
   * @return true if instance.canEqual(other)
   */
  def canEqual(other: Any): Boolean = macro CanEqualImpl.canEqual
}