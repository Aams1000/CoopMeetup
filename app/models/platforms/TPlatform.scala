package models.platforms

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.H2Driver.MappedColumnType

/**
  * Created by Andrew on 2/6/16.
  */
sealed trait TPlatform {

  /**
    * Every incarnation of TPlatform will have the following values
    */

  def name: String

}

  case object NDS extends TPlatform { def name = "Nintendo DS"}
  case object N3DS extends TPlatform { def name = "Nintendo 3DS"}
  case object PC extends TPlatform { def name = "PC"}
  case object Playstation_3 extends TPlatform { def name = "Playstation 3"}
  case object Playstation_4 extends TPlatform { def name = "Playstation 4"}
  case object Xbox_360 extends TPlatform { def name = "Xbox 360"}
  case object Xbox_One extends TPlatform { def name = "Xbox One"}
  case object Wii extends TPlatform { def name = "Nintendo Wii"}
  case object Wii_U extends TPlatform { def name = "Nintendo Wii U"}



//object PlatformColumnMapper {
//
//  implicit val platformMapper = MappedColumnType.base[TPlatform, String](
//    //map TPlatform to String
//    t => t.name,
//    //map String to TPlatform
//    s => s match {
//      case "Nintendo DS" => NDS
//      case "Nintendo 3DS" => N3DS
//      case "PC" => PC
//      case "Playstation 3" => Playstation_3
//      case "Playstation 4" => Playstation_4
//      case "Xbox 360" => Xbox_360
//      case "Xbox One" => Xbox_One
//      case "Nintendo Wii" => Wii
//      case "Nintendo Wii U" => Wii_U
//    }
//  )
//}



