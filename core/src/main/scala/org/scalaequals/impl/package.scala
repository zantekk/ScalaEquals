/*
 * Copyright (c) 2013 Alex DiCarlo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.scalaequals

/** @author Alex DiCarlo
  * @version 1.0.1
  * @since 1.0.1
  */
package object impl {
  /** @author Alex DiCarlo
    * @version 1.0.1
    * @since 0.3.0
    */
  private[impl] object Errors {
    /**
     * ScalaEquals.canEquals must be called from within a canEqual method
     */
    val badCanEqualsCallSite: String =
      "ScalaEquals.canEquals must be called from within a canEqual method.\n" +
        "Check that your method signature matches one of \"def canEqual(other: Any): Boolean\" or " +
        "\"override def canEqual(other: Any): Boolean\"."
    /**
     * ScalaEquals.equal must be called from within an equals method
     */
    val badEqualCallSite: String =
      "ScalaEquals.equal must be called from within an equals method.\n" +
        "Check that your method signature matches \"override def equals(other: Any): Boolean\""
    /**
     * ScalaEquals.hash must be called from within a hashCode method
     */
    val badHashCallSite: String =
      "ScalaEquals.hash must be called from within a hashCode method.\n" +
        "Check that your method signature matches \"override def hashCode(): Int\""
    /**
     * No attachments found on equals. Ensure that the definition of #equals comes before #hashCode() and that you
     * are using ScalaEquals.equal or ScalaEquals.equalAllVals"
     */
    val badHashOrdering: String = "No attachments found on equals. Ensure that the definition of #equals " +
      "comes before #hashCode() and that you are using ScalaEquals.equal or ScalaEquals.equalAllVals"
    /**
     * No equals method found! Ensure you are overriding equals and that it has the correct signature.
     */
    val missingEquals: String =
      "No equals method found! Ensure you are overriding equals and that it has the correct signature."
  }
}