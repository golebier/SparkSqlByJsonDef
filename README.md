# SparkSqlByJsonDef

## What
- execute the SQL definition with a simple JSON definition
- use minimal effort to start the work
- fully testable locally
- you can focus on a SQL instead of a Spark settings changes!

## Why
- simplification of the Apache Spark usage on any Cloud or OnPrem
- Fast and highly automatic
- yet still with ability to set up any change via the JSON config

## For Whom
- for people that know SQL but they do not know Spark, Scala, Java, etc.

## Examples

```json
{
  "global": {
    "sparkCache": "MEMORY_ONLY",
    "mode": "batch",
    "cacheAutomatically": true
  },
  "queries": [
    {
      "alias": "1st_test_table_max", // Cannot be mnitted
      "query": "SELECT * FROM test_table WHERE load_date = '${LOAD_DATE}'", // optional if `UDF` present
      "udf": "gra.spark.sql.json.executor.udf.MaxData", // Optional if `query` present
      "config": { // combined with UDF
        "db": "db_name", // option if alias is ussed
        "table": "table_name",
        "date_format": "yyyy-MM-dd", // optional - with default presented
        "mode": "latest" // optional - default presented
      }
    },
    {
      "alias": "final_test_table",
      "query": "SELECT * FROM 1st_test_table_max",
      "udf": "gra.spark.sql.json.executor.udf.WriteDataLakeTable",
      "config": {
        "db": "${pro_bu_db}",
        "table": "final_table",
        "path": "s3a://${pro_bu_bucket}/final_table",
        "partitions": ["country", "load_date"],
        "repartition": 15
      }
    }
  ]
}
```

```cmd
 ssje -c cluster-pro-settings.yml -s pro-variables.json -e above-def.json -v "{ "LOAD_DATE": "1999-07-02"}
```

## Done:
- I'll add here things that we could finish impl.

## TODO:
- extract commons
- extract common-tests
- extract UDF
- better processing time presentation
- Better README
- JSON parser
- UTs - e2e for starters
- Core impl. of the executor
- UDFs
- Common and Common-Test - extracted to separated project

