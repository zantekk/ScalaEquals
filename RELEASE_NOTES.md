Version 1.1.0:

- Add `ScalaEquals.genString` and `ScalaEquals.genString(params)` for generating `toString` similar to  a
case class.

Version 1.0.3:

- `ScalaEquals.hash` no longer needs to be called after `equals`. Any ordering of the methods in the class will work.
- Change `ScalaEquals.hash` to use `MurmurHash3` instaed of `Objects.hash`
- Add compiler warning when using `ScalaEquals.equal` with a trait

Version 1.0.2:

- Removed `other` parameter from `equal` and `canEquals` methods
- Fixed bug where `canEquals` would only work when the parameter was named `other`. (Now it does not matter what it
is named).
- From this release forward, bug-fix version numbers, ex. 1.0.x, will be source code compatible, and only introduce
bug fixes. Minor version numbers, ex. 1.x.0, will be source code compatible, but will introduce new features. Major
version numbers, ex. x.0.0, will not necessarily be source code compatible.

Version 1.0.1:

- Made method locating logic more robust, fixing a bug with overloaded methods
- If one incorrectly defines equals, a compile-time error will be thrown.