package database

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.MySQLProfile",
    "com.mysql.jdbc.Driver",
    "jdbc:mysql://localhost:3306/springdb?user=mysqluser&password=password",
    "/app",
    "database",
    None,
    None,
    ignoreInvalidDefaults = true,
    outputToMultipleFiles = false
  )
}
