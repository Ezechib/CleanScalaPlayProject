package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Hello World!"))
  }

  def help() = Action {
    Redirect("http://google.com")
  }

  def infinite() = Action {
    Redirect(routes.HomeController.index())
  }

  def Help() = TODO
}
