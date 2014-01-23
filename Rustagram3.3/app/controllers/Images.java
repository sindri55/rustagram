/*package controllers;

import is.ru.honn.rustagram.data.ImageDataGateway;
import is.ru.honn.rustagram.data.UserDataGateway;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.RustagramObject;
import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.service.RustagramService;

import is.ru.honn.rustagram.service.UserNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.api.data.*;
import play.api.mvc.Request;
import play.cache.Cache;
import play.data.*;


import static play.data.Form.form;

import play.data.Form;
import play.libs.Json;
import play.mvc.Content;
import play.mvc.Result;
import scala.collection.immutable.Set;
import views.html.*;

import play.api.mvc.Action;
import views.html.addimage;
import views.html.addimage_success;
import views.html.index;
import views.html.login;
import views.html.signup;
import views.html.signup_success;

import java.lang.reflect.Array;
import java.util.List;


public class Images extends AbstractRustagramController {

    final static Form<Image> addImageForm = form(Image.class);
    final static Form<User> addUserForm = form(User.class);


    public static Result showImageForm(){
        return ok(addimage.render(addImageForm));

    }

    public static Result processImageForm(){

        Form<Image> filledImageForm = addImageForm.bindFromRequest();
        Image created = filledImageForm.get();
        created.setCreator(session("username"));

        RustagramService service = (RustagramService)ctx.getBean("service");

        return ok(addimage_success.render(created));


    }


        public static Result allImages(){

        RustagramService service = (RustagramService) ctx.getBean("service");
        List<Image> imageList = service.getAllImages();
       return ok(index.render(imageList));


    }




}            */

