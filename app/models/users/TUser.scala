package models.users

/**
  * Created by Andrew on 1/30/16.
  */
trait TUser {

  /**
    * Every incarnation of TUser will have the following values
    */

  //account information
  def id: Long
  def username: String
  def password: String
  def email: String

  //reputation + activity
  def reputation: Int


}
