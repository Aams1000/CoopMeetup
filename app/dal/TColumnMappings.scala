package dal

import java.time.LocalDate

import models.genres._
import models.platforms._
import models.ratings.{M, T, E, TRating}
import slick.driver.H2Driver.api._

/**
  * Created by Andrew on 2/15/16.
  */
trait TColumnMappings {

  implicit val genreMapper = MappedColumnType.base[TGenre, String](
    //map TPlatform to String
    t => t.name,
    //map String to TPlatform
    s => s match {
      case "Action" => Action
      case "Adventure" => Adventure
      case "Fighting" => Fighting
      case "Music" => Music
      case "Platformer" => Platformer
      case "Puzzle" => Puzzle
      case "Racing" => Racing
      case "RPG" => RPG
      case "Shooter" => Shooter
      case "Sports" => Sports
      case "Strategy" => Strategy
    }
  )

  implicit val localDateMapper = MappedColumnType.base[LocalDate, String](
    //map date to String
    d => d.toString,
    //map String to date
    s => LocalDate.parse(s)
  )

  implicit val platformMapper = MappedColumnType.base[TPlatform, String](
    //map TPlatform to String
    t => t.name,
    //map String to TPlatform
    s => s match {
      case "Nintendo DS" => NDS
      case "Nintendo 3DS" => N3DS
      case "PC" => PC
      case "Playstation 3" => Playstation_3
      case "Playstation 4" => Playstation_4
      case "Xbox 360" => Xbox_360
      case "Xbox One" => Xbox_One
      case "Nintendo Wii" => Wii
      case "Nintendo Wii U" => Wii_U
    }
  )

  implicit val ratingMapper = MappedColumnType.base[TRating, String](
    //map TPlatform to String
    t => t.name,
    //map String to TPlatform
    s => s match {
      case "E" => E
      case "T" => T
      case "M" => M
    }
  )


}
