#Sextimation JSON Parser
### Requirements
- Scala v2.10.4
- Spark v1.3.1

### Submit to Spark
```sh
// Use UTF-8 Encoding (OS X uses MacRoman as Default)
$ export JAVA_TOOL_OPTIONS='-Dfile.encoding=UTF8'
// Submit JAR to Spark
$ spark-1.3.1/bin/spark-submit \
--class "SextimationParser" \
--driver-memory 10g \
--executor-memory 6g \
--jars ~/.sbt/boot/scala-2.10.4/org.scala-sbt/sbt/0.13.8/json4s-core_2.10-3.2.10.jar \
--jars ~/.sbt/boot/scala-2.10.4/org.scala-sbt/sbt/0.13.8/json4s-ast_2.10-3.2.10.jar \
--jars ~/.sbt/boot/scala-2.10.4/org.scala-sbt/sbt/0.13.8/json4s-support_2.10-0.6.0.jar \
--jars ~/.ivy2/cache/org.json4s/json4s-native_2.10/jars/json4s-native_2.10-3.2.10.jar \
target/scala-2.10/sextimation-parser_2.10-1.0.jar
```