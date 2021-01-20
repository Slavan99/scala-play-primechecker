package database

// AUTO-GENERATED Slick data model

class Custom {
  val profile = slick.jdbc.MySQLProfile
}

/** Stand-alone Slick data model for immediate use */
object Tables extends Custom with Tables {

}

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Algorithm.schema ++ HibernateSequence.schema ++ History.schema ++ User.schema ++ UserRole.schema

  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Algorithm
   *
   * @param id   Database column id SqlType(INT), PrimaryKey
   * @param name Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
  case class AlgorithmRow(id: Int, name: Option[String] = None)

  /** GetResult implicit for fetching AlgorithmRow objects using plain SQL queries */
  implicit def GetResultAlgorithmRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[AlgorithmRow] = GR {
    prs =>
      import prs._
      AlgorithmRow.tupled((<<[Int], <<?[String]))
  }

  /** Table description of table algorithm. Objects of this class serve as prototypes for rows in queries. */
  class Algorithm(_tableTag: Tag) extends profile.api.Table[AlgorithmRow](_tableTag, Some("springdb"), "algorithm") {
    def * = (id, name) <> (AlgorithmRow.tupled, AlgorithmRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), name)).shaped.<>({ r => import r._; _1.map(_ => AlgorithmRow.tupled((_1.get, _2))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(255, varying = true), O.Default(None))
  }

  /** Collection-like TableQuery object for table Algorithm */
  lazy val Algorithm = new TableQuery(tag => new Algorithm(tag))

  /** Entity class storing rows of table HibernateSequence
   *
   * @param nextVal Database column next_val SqlType(BIGINT), Default(None) */
  case class HibernateSequenceRow(nextVal: Option[Long] = None)

  /** GetResult implicit for fetching HibernateSequenceRow objects using plain SQL queries */
  implicit def GetResultHibernateSequenceRow(implicit e0: GR[Option[Long]]): GR[HibernateSequenceRow] = GR {
    prs =>
      import prs._
      HibernateSequenceRow(<<?[Long])
  }

  /** Table description of table hibernate_sequence. Objects of this class serve as prototypes for rows in queries. */
  class HibernateSequence(_tableTag: Tag) extends profile.api.Table[HibernateSequenceRow](_tableTag, Some("springdb"), "hibernate_sequence") {
    def * = nextVal <> (HibernateSequenceRow, HibernateSequenceRow.unapply)

    /** Database column next_val SqlType(BIGINT), Default(None) */
    val nextVal: Rep[Option[Long]] = column[Option[Long]]("next_val", O.Default(None))
  }

  /** Collection-like TableQuery object for table HibernateSequence */
  lazy val HibernateSequence = new TableQuery(tag => new HibernateSequence(tag))

  /** Entity class storing rows of table History
   *
   * @param id            Database column id SqlType(INT), PrimaryKey
   * @param checkDateTime Database column check_date_time SqlType(DATETIME), Default(None)
   * @param iterations    Database column iterations SqlType(INT)
   * @param number        Database column number SqlType(BIGINT)
   * @param result        Database column result SqlType(BIT)
   * @param algorithmId   Database column algorithm_id SqlType(INT), Default(None)
   * @param userName      Database column user_name SqlType(VARCHAR), Length(255,true), Default(None) */
  case class HistoryRow(id: Int, checkDateTime: Option[java.sql.Timestamp] = None, iterations: Int, number: Long, result: Boolean, algorithmId: Option[Int] = None, userName: Option[String] = None)

  /** GetResult implicit for fetching HistoryRow objects using plain SQL queries */
  implicit def GetResultHistoryRow(implicit e0: GR[Int], e1: GR[Option[java.sql.Timestamp]], e2: GR[Long], e3: GR[Boolean], e4: GR[Option[Int]], e5: GR[Option[String]]): GR[HistoryRow] = GR {
    prs =>
      import prs._
      HistoryRow.tupled((<<[Int], <<?[java.sql.Timestamp], <<[Int], <<[Long], <<[Boolean], <<?[Int], <<?[String]))
  }

  /** Table description of table history. Objects of this class serve as prototypes for rows in queries. */
  class History(_tableTag: Tag) extends profile.api.Table[HistoryRow](_tableTag, Some("springdb"), "history") {
    def * = (id, checkDateTime, iterations, number, result, algorithmId, userName) <> (HistoryRow.tupled, HistoryRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), checkDateTime, Rep.Some(iterations), Rep.Some(number), Rep.Some(result), algorithmId, userName)).shaped.<>({ r => import r._; _1.map(_ => HistoryRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6, _7))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column check_date_time SqlType(DATETIME), Default(None) */
    val checkDateTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("check_date_time", O.Default(None))
    /** Database column iterations SqlType(INT) */
    val iterations: Rep[Int] = column[Int]("iterations")
    /** Database column number SqlType(BIGINT) */
    val number: Rep[Long] = column[Long]("number")
    /** Database column result SqlType(BIT) */
    val result: Rep[Boolean] = column[Boolean]("result")
    /** Database column algorithm_id SqlType(INT), Default(None) */
    val algorithmId: Rep[Option[Int]] = column[Option[Int]]("algorithm_id", O.Default(None))
    /** Database column user_name SqlType(VARCHAR), Length(255,true), Default(None) */
    val userName: Rep[Option[String]] = column[Option[String]]("user_name", O.Length(255, varying = true), O.Default(None))

    /** Foreign key referencing Algorithm (database name FKi6qrh4j87egnt0ugvgqqwhyb0) */
    lazy val algorithmFk = foreignKey("FKi6qrh4j87egnt0ugvgqqwhyb0", algorithmId, Algorithm)(r => Rep.Some(r.id), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name FKml9xl7qsrb3sj27qs86htophd) */
    lazy val userFk = foreignKey("FKml9xl7qsrb3sj27qs86htophd", userName, User)(r => Rep.Some(r.name), onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table History */
  lazy val History = new TableQuery(tag => new History(tag))

  /** Entity class storing rows of table User
   *
   * @param name     Database column name SqlType(VARCHAR), PrimaryKey, Length(255,true)
   * @param active   Database column active SqlType(BIT)
   * @param age      Database column age SqlType(INT)
   * @param city     Database column city SqlType(VARCHAR), Length(255,true), Default(None)
   * @param email    Database column email SqlType(VARCHAR), Length(255,true), Default(None)
   * @param id       Database column id SqlType(INT), Default(None)
   * @param lastName Database column last_name SqlType(VARCHAR), Length(255,true), Default(None)
   * @param password Database column password SqlType(VARCHAR), Length(255,true)
   * @param phone    Database column phone SqlType(VARCHAR), Length(255,true), Default(None) */
  case class UserRow(name: String, active: Boolean, age: Int, city: Option[String] = None, email: Option[String] = None, id: Option[Int] = None, lastName: Option[String] = None, password: String, phone: Option[String] = None)

  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[String], e1: GR[Boolean], e2: GR[Int], e3: GR[Option[String]], e4: GR[Option[Int]]): GR[UserRow] = GR {
    prs =>
      import prs._
      UserRow.tupled((<<[String], <<[Boolean], <<[Int], <<?[String], <<?[String], <<?[Int], <<?[String], <<[String], <<?[String]))
  }

  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("springdb"), "user") {
    def * = (name, active, age, city, email, id, lastName, password, phone) <> (UserRow.tupled, UserRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(name), Rep.Some(active), Rep.Some(age), city, email, id, lastName, Rep.Some(password), phone)).shaped.<>({ r => import r._; _1.map(_ => UserRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8.get, _9))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column name SqlType(VARCHAR), PrimaryKey, Length(255,true) */
    val name: Rep[String] = column[String]("name", O.PrimaryKey, O.Length(255, varying = true))
    /** Database column active SqlType(BIT) */
    val active: Rep[Boolean] = column[Boolean]("active")
    /** Database column age SqlType(INT) */
    val age: Rep[Int] = column[Int]("age")
    /** Database column city SqlType(VARCHAR), Length(255,true), Default(None) */
    val city: Rep[Option[String]] = column[Option[String]]("city", O.Length(255, varying = true), O.Default(None))
    /** Database column email SqlType(VARCHAR), Length(255,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(255, varying = true), O.Default(None))
    /** Database column id SqlType(INT), Default(None) */
    val id: Rep[Option[Int]] = column[Option[Int]]("id", O.Default(None))
    /** Database column last_name SqlType(VARCHAR), Length(255,true), Default(None) */
    val lastName: Rep[Option[String]] = column[Option[String]]("last_name", O.Length(255, varying = true), O.Default(None))
    /** Database column password SqlType(VARCHAR), Length(255,true) */
    val password: Rep[String] = column[String]("password", O.Length(255, varying = true))
    /** Database column phone SqlType(VARCHAR), Length(255,true), Default(None) */
    val phone: Rep[Option[String]] = column[Option[String]]("phone", O.Length(255, varying = true), O.Default(None))
  }

  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserRole
   *
   * @param userName Database column user_name SqlType(VARCHAR), Length(255,true)
   * @param roles    Database column roles SqlType(VARCHAR), Length(255,true), Default(None) */
  case class UserRoleRow(userName: String, roles: Option[String] = None)

  /** GetResult implicit for fetching UserRoleRow objects using plain SQL queries */
  implicit def GetResultUserRoleRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[UserRoleRow] = GR {
    prs =>
      import prs._
      UserRoleRow.tupled((<<[String], <<?[String]))
  }

  /** Table description of table user_role. Objects of this class serve as prototypes for rows in queries. */
  class UserRole(_tableTag: Tag) extends profile.api.Table[UserRoleRow](_tableTag, Some("springdb"), "user_role") {
    def * = (userName, roles) <> (UserRoleRow.tupled, UserRoleRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userName), roles)).shaped.<>({ r => import r._; _1.map(_ => UserRoleRow.tupled((_1.get, _2))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_name SqlType(VARCHAR), Length(255,true) */
    val userName: Rep[String] = column[String]("user_name", O.Length(255, varying = true))
    /** Database column roles SqlType(VARCHAR), Length(255,true), Default(None) */
    val roles: Rep[Option[String]] = column[Option[String]]("roles", O.Length(255, varying = true), O.Default(None))

    /** Foreign key referencing User (database name FKlo35m1ajgm8bq9tkce6dcpvec) */
    lazy val userFk = foreignKey("FKlo35m1ajgm8bq9tkce6dcpvec", userName, User)(r => r.name, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table UserRole */
  lazy val UserRole = new TableQuery(tag => new UserRole(tag))
}
