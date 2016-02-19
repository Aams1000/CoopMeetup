package dal

/**
  * Created by Andrew on 2/13/16.
  */

import javax.inject.{Inject, Singleton}

import models.games.DBGame
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
  * A repository for Users.
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class DBGameRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import driver.api._


  /**
    * Here we define the table. It will have a name of people
    */

  private class GameTable(tag: Tag) extends Table[DBGame](tag, "games") with TColumnMappings{

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    /** The developer column */
    def developer = column[Option[String]]("developer")

    /** The publisher column */
    def publisher = column[Option[String]]("publisher")

//    /** The platform column */
//    def platform = column[TPlatform]("platform")
//
//    /** The genre column */
//    def genre = column[TGenre]("genre")

    /** The minPlayers column */
    def minPlayers = column[Option[Int]]("minPlayers")

    def maxPlayers = column[Option[Int]]("maxPlayers")

    def localCoop = column[Boolean]("localCoop")

    def onlineCoop = column[Boolean]("onlineCoop")

    def localCompetitive = column[Boolean]("localCompetitive")

    def onlineCompetitive = column[Boolean]("onlineCompetitive")

//    def releaseDate = column[Option[LocalDate]]("releaseDate")
//
//    def rating = column[Option[TRating]]("rating")

    def description = column[Option[String]]("description")

    def photoLocation = column[Option[String]]("photoLocation")

    implicit val mapper = platformMapper

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the User object.
      *
      * In this case, we are simply passing the id, name and page parameters to the User case classes
      * apply and unapply methods.
      */
    def * = (id, name, developer, publisher, minPlayers, maxPlayers, localCoop, onlineCoop,
      localCompetitive, onlineCompetitive, description, photoLocation) <> ((DBGame.apply _).tupled, DBGame.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val games = TableQuery[GameTable]

  /**
    * Create a person with the given name and age.
    *
    * This is an asynchronous operation, it will return a future of the created person, which can be used to obtain the
    * id for that person.
    */
  def create(name: String,
             developer: Option[String],
             publisher: Option[String],
             minPlayers: Option[Int],
             maxPlayers: Option[Int],
             localCoop: Boolean,
             onlineCoop: Boolean,
             localCompetitive: Boolean,
             onlineCompetitive: Boolean,
             description: Option[String],
             photoLocation: Option[String]): Future[DBGame] = db.run {
    // We create a projection of non-id columns, since we're not inserting a value for the id column
    (games.map(g => (g.name, g.developer, g.publisher, g.minPlayers, g.maxPlayers, g.localCoop,
      g.onlineCoop, g.localCompetitive, g.onlineCompetitive, g.description, g.photoLocation))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning games.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((p, id) => DBGame(id, p._1, p._2, p._3, p._4, p._5, p._6, p._7, p._8, p._9, p._10, p._11))
      // And finally, insert the person into the database
      ) += (name, developer, publisher, minPlayers, maxPlayers, localCoop, onlineCompetitive,
      localCompetitive, onlineCompetitive, description, photoLocation)
  }

  /**
    * List all the games in the database.
    */
  def list(): Future[Seq[DBGame]] = db.run {
    games.result
  }
}
