package models.genres

/**
  * Created by Andrew on 2/6/16.
  */

sealed trait TGenre {

  /**
    * Every incarnation of TGenre will have the following values
    */

  def name: String

}

//possible genres
case object Action extends TGenre { def name = "Action" }
case object Adventure extends TGenre { def name = "Adventure" }
case object Fighting extends TGenre { def name = "Fighting" }
case object Music extends TGenre { def name = "Music" }
case object Platformer extends TGenre { def name = "Platformer" }
case object Puzzle extends TGenre { def name = "Puzzle" }
case object Racing extends TGenre { def name = "Racing" }
case object RPG extends TGenre { def name = "RPG" }
case object Shooter extends TGenre { def name = "Shooter" }
case object Sports extends TGenre { def name = "Sports" }
case object Strategy extends TGenre { def name = "Strategy" }
