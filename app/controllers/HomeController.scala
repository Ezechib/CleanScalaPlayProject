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

  def Todo() = TODO

  def Hello() = Action {
    // Ok(<h1>Hello World!</h1>).as("text/html")
    Ok(<h1>Hello World!</h1>).as(HTML)
  }

  def Charset() = Action { implicit request: Request[AnyContent] =>
    implicit val myCustomCharset: Codec = Codec.javaSupported("iso-8859-1")
    Ok("Hello World!")
  }

  def request() = Action { implicit request: Request[AnyContent] =>
    Ok(request.headers.get("Host").getOrElse("Value not found"))
  }

  def create() = Action {
    Ok("Cookie created").withCookies(
      Cookie("colour", "green")
    )
  }

  def cookie() = Action { implicit request: Request[AnyContent] =>
    request.cookies.get("colour") match {
      case Some(cookie) => Ok(s"Your cookie value is: ${cookie.value}")
      case _ => Ok("Cookie not found")
    }
  }

  def delete() = Action {
    Ok("Cookie deleted")
      .discardingCookies(
        DiscardingCookie("colour")
      )
  }

  def overriding() = Action { implicit request: Request[AnyContent] =>
    Ok("Session Override").withSession("connected" -> "user@gmail.com")
  }

  def adding() = Action { implicit request: Request[AnyContent] =>
    Ok("Session added").withSession(request.session + ("connected" -> "user@gmail.com"))
  }

  def reading() = Action { implicit request: Request[AnyContent] =>
    Ok(request.session.get("connected").getOrElse("User is not logged in"))
  }

  def removing() = Action { implicit request: Request[AnyContent] =>
    Ok("Session removed").withSession(request.session - "connected")
  }

  def addFlash() = Action { implicit request: Request[AnyContent] =>
    Redirect("readFlash").flashing("success" -> "You have been successfully redirected")
  }

  def readFlash() = Action { implicit request: Request[AnyContent] =>
    Ok(request.flash.get("success").getOrElse("Something went wrong while redirecting"))
  }
}
