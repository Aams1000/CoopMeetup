package models.games

import java.time.{LocalDate, ZonedDateTime}

import com.sun.istack.internal.{Nullable, NotNull}
import models.genres.TGenre
import models.platforms.TPlatform
import models.ratings.TRating

/**
  * Created by Andrew on 2/6/16.
  */
trait TGame {

  /**
    * Every incarnation of TGame will have the following values
    */

  //basic identification information
  def id: Long

  @NotNull
  def name: String

  @NotNull
//  def platforms: Vector[TPlatform]
  def platform: TPlatform

  @NotNull
//  def genres: Vector[TGenre]
  def genre: TGenre

  @Nullable
  def developer: Option[String]

  @Nullable
  def publisher: Option[String]

  @Nullable
  def releaseDate: Option[LocalDate]

  @NotNull
  def rating: Option[TRating]

  //play information
  def minPlayers: Option[Int]
  def maxPlayers: Option[Int]
  def localCoop: Boolean
  def localCompetitive: Boolean
  def onlineCoop: Boolean
  def onlineCompetitive: Boolean

  //convenient information
  def local: Boolean = localCoop || localCompetitive
  def online: Boolean = onlineCoop || onlineCompetitive
  def cooperative: Boolean = localCoop || onlineCoop
  def competitive: Boolean = localCompetitive || onlineCompetitive

}
