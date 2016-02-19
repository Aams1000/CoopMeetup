package models.ratings

import slick.driver.H2Driver.MappedColumnType

/**
  * Created by Andrew on 2/7/16.
  */
sealed trait TRating {

  /**
    * Every incarnation of TRating will have the following values
    */

  def name: String
}

case object E extends TRating { def name = "E" }
case object T extends TRating { def name = "T" }
case object M extends TRating { def name = "M" }

//object RatingColumnMapper {
//
//  implicit val ratingMapper = MappedColumnType.base[TRating, String](
//    //map TPlatform to String
//    t => t.name,
//    //map String to TPlatform
//    s => s match {
//      case "E" => E
//      case "T" => T
//      case "M" => M
//    }
//  )
//}