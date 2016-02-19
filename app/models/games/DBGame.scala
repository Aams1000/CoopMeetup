package models.games

/**
  * Created by Andrew on 2/7/16.
  *
  * The basic game object in the GameTable. This is combined with other tables to create a Game object.
  */
case class DBGame (id: Long,
                 name: String,
                 developer: Option[String],
                 publisher: Option[String],
                 minPlayers: Option[Int],
                 maxPlayers: Option[Int],
                 localCoop: Boolean,
                 onlineCoop: Boolean,
                 localCompetitive: Boolean,
                 onlineCompetitive: Boolean,
                 description: Option[String],
                 photoLocation: Option[String])
{

}
