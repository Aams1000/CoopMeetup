package models.meetups

import java.time.ZonedDateTime

import models.games.TGame
import models.platforms.TPlatform
import models.users.TUser

/**
  * Created by Andrew on 2/13/16.
  */
case class Meetup (id: Long, title: String, games: Vector[TGame], platform: TPlatform, participants: Vector[TUser],
                   description: String, cooperative: Boolean, competitive: Boolean, local: Boolean, online: Boolean,
                   time: ZonedDateTime, creator: TUser) extends TMeetup{

}
