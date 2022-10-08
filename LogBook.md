# Whiskers Kotlin-Native project
<ins>20221008</ins>

https://github.com/JetBrains/kotlin-native/issues/1212

External jars will apparently never be supported by Kotlin/Native and that is because of the nature of the Kotlin/JVM AOT compiler (Ahead of Time).

The only way to support this is to make own libraries to support special functionalities.

<ins>20220927</ins>

There is a typical [log generated](./docs/20220927.log) when running the build for the first time.

1. Downloads the right builds and binaries to be able to build the application natively.
2. Compiles the Kotlin code into native code.

It is important to notice that the first time the build runs, because everything is download, the compilation is very slow.
In general, compilation takes a bit longer than just generating Java Byte Codes.
