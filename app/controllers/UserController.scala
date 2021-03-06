package controllers

/**
  * Created by Andrew on 1/31/16.
  */

import dal.UserRepository
import play.api._
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json
import models.users.User

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._

class UserController @Inject() (repo: UserRepository, val messagesApi: MessagesApi)
                                 (implicit ec: ExecutionContext) extends Controller with I18nSupport{

  /**
    * The mapping for the user form.
    */
  val userForm: Form[CreateUserForm] = Form {
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText,
      "numPosts" -> number(min = 0),
      "reputation" -> number(min = 1, max = 5)
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  }

  /**
    * The index action.
    */
  def index = Action {
    Ok(views.html.index(userForm))
  }

  /**
    * The add user action.
    *
    * This is asynchronous, since we're invoking the asynchronous methods on userRepository.
    */
  def addUser = Action.async { implicit request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle success.
    userForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the user creation function returns a future.
      errorForm => {
        Future.successful(Ok(views.html.index(errorForm)))
      },
      // There were no errors in the from, so create the user.
      user => {
        repo.create(user.username, user.password, user.email, user.numPosts, user.reputation).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.UserController.index)
        }
      }
    )
  }

  /**
    * A REST endpoint that gets all the people as JSON.
    */
  def getUsers = Action.async {
    repo.list().map { Users =>
      Ok(Json.toJson(Users))
    }
  }

  implicit val userFormatter = Json.format[User]
}


/**
  * The create user form.
  *
  * Generally for forms, you should define separate objects to your models, since forms very often need to present data
  * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
  * that is generated once it's created.
  */
case class CreateUserForm(username: String, password: String, email: String, numPosts: Int,
                          reputation: Int)