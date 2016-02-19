package models.meetups

import java.time.ZonedDateTime

import models.games.TGame
import models.platforms.TPlatform
import models.users.TUser

/**
  * Created by Andrew on 2/13/16.
  */
trait TMeetup {

  /**
    * Every incarnation of TMeetup will have the following values
    */

  def id: Long

  //Who, what, where, and when
  def title: String
  def time: ZonedDateTime
  def creator: TUser
  def platform: TPlatform
  def games: Vector[TGame]
  def participants: Vector[TUser]


  //description
  def description: String
  //def announcements: Vector[TAnnouncement]

  //type of arrangement
  def cooperative: Boolean
  def competitive: Boolean
  def local: Boolean
  def online: Boolean

}
