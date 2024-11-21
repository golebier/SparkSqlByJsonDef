package gra.spark.sql.json.executor

import gra.tests.common.TestSparkSessionWrapper

class E2eMainTest extends TestSparkSessionWrapper {
  test ("if the code compiles and doesn't fail on impl.") {
    Main.run(Array(
      "-c", "cluster-pro-settings.yml" // TODO impl. usage
      , "-s", "src/test/resources/clusterVariablesTest.json"
      , "-e", "json-sql-def.json"
      , "-v", """{ "LOAD_DATE": "1999-07-02"}"""
    ))
  }
}
