## Okay, IDK what the fuck requires `Kotlinx Serialization` and I must handle this shit
## [Issue](https://github.com/JetBrains/compose-multiplatform/issues/2720)

-dontwarn kotlinx.datetime.**

-keep class com.sun.jna.* { *; }
-keep class * implements com.sun.jna.* { *; }