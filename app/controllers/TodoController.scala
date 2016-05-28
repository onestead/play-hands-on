package controllers

import javax.inject.Inject

import play.api.data.Forms._
import play.api.data._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import services._
import views.html

/**
  * Created by Administrator on 2016/05/28.
  */
class TodoController @Inject() (todoService: TodoService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def helloworld() = Action { implicit request =>
    Ok("Hello World")
  }

  def list() = Action { implicit request =>
    val items: Seq[Todo] = todoService.list()
    Ok(html.list(items))
  }

  val todoForm: Form[String] = Form("name" -> nonEmptyText)

  def create = Action {
    Ok(html.createForm(todoForm))
  }

  def save() = Action { implicit request =>
    val name: String = todoForm.bindFromRequest().get
    todoService.insert(Todo(name))
    Redirect(routes.TodoController.list())
  }


}

