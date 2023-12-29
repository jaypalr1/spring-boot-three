package org.jay.random;

import io.micrometer.core.instrument.MultiGauge.Row;

public class TestTwo {

  public static void main(String[] args) {
    SparkSession spark = SparkSession
        .builder()
        .appName("RW-with-partition")
        .config("spark.master", "local")
        .getOrCreate();
    // File at: https://github.com/apache/spark/blob/a92ef00145b264013e11de12f2c7cee62c28198d/examples/src/main/resources/users.parquet
    Dataset<Row> usersDF = spark.read().load("src/main/resources/parquet/users.parquet");
    usersDF.printSchema();
    /*
    root
     |-- name: string (nullable = true)
     |-- favorite_color: string (nullable = true)
     |-- favorite_numbers: array (nullable = true)
     |    |-- element: integer (containsNull = true)
     */
    usersDF.show();
    /*
    +------+--------------+----------------+
    |  name|favorite_color|favorite_numbers|
    +------+--------------+----------------+
    |Alyssa|          null|  [3, 9, 15, 20]|
    |   Ben|           red|              []|
    +------+--------------+----------------+
     */
    usersDF
        .write()
        .partitionBy("favorite_color")
        .format("parquet")
        .save("src/main/resources/parquet/partbycolo/names.parquet");
  }
}
