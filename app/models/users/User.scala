package models.users

import play.api.libs.json.Json

/**
  * Created by Andrew on 1/24/16.
  */
case class User (id: Long, username: String, password: String, email: String, numPosts: Int,
            reputation: Int) extends TUser{


  //json serializer
  object User {
    implicit val userFormatter = Json.format[User]
  }
}
