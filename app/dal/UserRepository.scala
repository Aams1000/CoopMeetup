package dal

/**
  * Created by Andrew on 1/31/16.
  */

import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import models.users.User

import scala.concurrent.{ExecutionContext, Future}

/**
  * A repository for Users.
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import driver.api._

  /**
    * Here we define the table. It will have a name of people
    */
  private class UserTable(tag: Tag) extends Table[User](tag, "users") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /** The name column */
    def username = column[String]("username")

    /** The age column */
    def password = column[String]("password")

    /** The email column */
    def email = column[String]("email")

    /** The numPosts column */
    def numPosts = column[Int]("numPosts")

    /** The reputation column */
    def reputation = column[Int]("reputation")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the User object.
      *
      * In this case, we are simply passing the id, name and page parameters to the User case classes
      * apply and unapply methods.
      */
    def * = (id, username, password, email, numPosts, reputation) <> ((User.apply _).tupled, User.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val users = TableQuery[UserTable]

  /**
    * Create a person with the given name and age.
    *
    * This is an asynchronous operation, it will return a future of the created person, which can be used to obtain the
    * id for that person.
    */
  def create(username: String, password: String, email: String, numPosts: Int,
             reputation: Int): Future[User] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (users.map(p => (p.username, p.password, p.email, p.numPosts, p.reputation))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning users.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((remainingParameters, id) => User(id, remainingParameters._1, remainingParameters._2, remainingParameters._3,
      remainingParameters._4, remainingParameters._5))
      // And finally, insert the person into the database
      ) += (username, password, email, numPosts, reputation)
  }

  /**
    * List all the people in the database.
    */
  def list(): Future[Seq[User]] = db.run {
    users.result
  }
}
