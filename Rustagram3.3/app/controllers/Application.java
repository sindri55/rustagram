/*
*@(#)Hello.java1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package controllers;

/**
 * Imports all the nessacary classes, views and utils needed
 */

import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.Feed;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.service.RustagramService;
import play.data.Form;
import play.mvc.Result;
import views.html.addimage;
import views.html.addimage_success;
import views.html.index;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

/**
 * This controller is responsible for gathering all the data and forwarding it to the right view
 */

public class Application extends AbstractRustagramController {
    /**
     * creates form that can accept input from the form that is displayed in the view
     */
    final static Form<Image> addFeedForm = form(Image.class);
    final static Form<Comment> addCommentFeedForm = form(Comment.class);

    /**
     * This Result function gets all the image information in an object from the database
     * and creates an empty list to gather all the information about each image in one object.
     * Then the new list is sent to the index view for rendering.
     * @return the feed object with images and all the information about them to the index view.
     */
    public static Result index() {
        RustagramService service = (RustagramService) ctx.getBean("service");
        List<Image> images = service.getAllImages();
        List<Feed> feeds = new ArrayList<Feed>();

        for (Image image : images) {
            Feed feed = new Feed(image);


            feed.setComments(service.getCommentsOnImage(feed.getId()));
            feeds.add(feed);
        }
        return ok(index.render(feeds));
    }

    /**
     * This result function returns the form to add images.
     * @return the correct form to the view
     */
    public static Result showImageForm() {
        return ok(addimage.render(addFeedForm));

    }

    /**
     * Gathers the information users enter about the imaged and creates an image object
     * Sets the username from the session of the logged in user
     * Connects to the Rustagram service for connection to the database
     * Sends all the information to the ImageDataGateway for insertion in the database
     *
     * @return the image successfully added.
     */
    public static Result processImageForm() {

        Form<Image> filledFeedForm = addFeedForm.bindFromRequest();
        Image created = filledFeedForm.get();
        created.setCreator(session("username"));
        RustagramService service = (RustagramService) ctx.getBean("service");
        service.createImage(created.getCreatorUsername(), created.getUrl(), created.getDescription());

        return ok(addimage_success.render(created));
    }

    /**
     * Gathers the comment from the addCommentFeedForm
     * Creates a comment object with the information from the form
     * creates a connection to the database
     * adds the comment to the database
     * @return to the index view.
     */
    public static Result processCommentForm() {

        Form<Comment> filledFeedForm = addCommentFeedForm.bindFromRequest();
        Comment created = filledFeedForm.get();
        RustagramService service = (RustagramService) ctx.getBean("service");
        service.addCommentOnImage(created.getCreatorUsername(), created.getImageId(), created.getComment());

        return redirect("index");


    }
}




