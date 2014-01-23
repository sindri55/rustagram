/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package controllers;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ru.honn.rustagram.service.RustagramService;
import is.ru.honn.rustagram.service.UserNotFoundException;
import play.data.Form;
import play.mvc.Result;
import views.html.login;
import views.html.signup;
import views.html.signup_success;

import javax.xml.transform.Result;

import static play.data.Form.form;

/**
 * This class handles the user signup, login and log out.
 */
public class Users extends AbstractRustagramController {

    /**
     * Loginform makes the functions from the userclass available to the forms
     * SignupForm makes the functions from the userregistration class available to the form
     */
    final static Form<User> loginForm = form(User.class);
    final static Form<UserRegistration> signupForm = form(UserRegistration.class);

    /**
     * This Result returns the signup view with the signup form
     * @return the signup view
     */
    public static Result showSignupForm() {
        return ok(signup.render(signupForm));
    }

    /**
     * This Result gathers the information from the userregistration form, error checks it against
     * and makes sure the form is properly filled out
     * @return
     */
    public static Result processSignupForm() {
        Form<UserRegistration> filledForm = signupForm.bindFromRequest();
        /**
         * Gives error if the Terms and condition have not been accepted
         */
        if (!"true".equals(filledForm.field("accept").value())) {
            filledForm.reject("accept", "You must accept the terms and conditions");

        }
        /**
         * Rejects the username if it is shorter than 4 letters.
         */
        if (filledForm.field("username").value().length() < 4) {
            filledForm.reject("username", "Username must be at least 4 characters");
        }
        /**
         * Rejects the password if it is shorter than 6 letters
         */
        if (filledForm.field("password").value().length() < 6) {
            filledForm.reject("password", "Password too short");
        }
        /**
         * Gives error if the repeated password does not match the first password
         */
        if (!filledForm.field("password").value().equals(filledForm.field("repeatPassword").value())) {
            filledForm.reject("repeatPassword", "Password does not match");
        }

        /**
         * returns the signupform if there are any errors in the form.
         */
        if (filledForm.hasErrors()) {
            return badRequest(signup.render(filledForm));
        } else {

            /**
             * Gathers all the information from the form.
             */
            Form<UserRegistration> filledUserForm = signupForm.bindFromRequest();

            /**
             * Creates a user object with all the information from the signupform
             */
            User user = filledUserForm.get();
            /**
             * Gets connection to the database
             */
            RustagramService service = (RustagramService) ctx.getBean("service");
            /**
             * Inserts all the information to the database.
             */
            service.userSignup(user.getUsername(), user.getPassword(), user.getDisplayName(), user.getEmail());
            /**
             * Returns the signup successful view and passes the user object to the view.
             */
            return ok(signup_success.render(user));
        }

    }

    /**
     * This result returns the login form
     * @return the login view with the correct form
     */
    public static Result showLoginForm() {
        return ok(login.render(loginForm));
    }

    /**
     * This result verifies the user and password exist and match by comparing them to the users in the database
     * and throws an exception if the user is not found.
     * @return to the index view if login is successful
     */
    public static Result processLoginForm() {
        /**
         * Gather the information entered by the user from the form
         */
        Form<User> filledForm = loginForm.bindFromRequest();


        // We get the context from AbstractRustagramController
        RustagramService service = (RustagramService) ctx.getBean("service");
        /**
         * Tries to find the user in the database and if it is not found throws a UserNotFoundException
         */
        try {
            /**
             * creates an user object if the user is found in database with all the information
             */
            User user = service.getUser(filledForm.field("username").value());
            /**
             * If the password does not match it throws an Usernotfoundexception
             */
            if (!user.getPassword().equals(filledForm.field("password").value())) {
                // Let's throw this exception here to use the same logic for
                // unsuccessful login (both username not found and incorrect
                // password.
                throw new UserNotFoundException();
            }

            // User was found and correct password entered.
            session("username", user.getUsername());
            session("displayName", user.getDisplayName());
            /**
             * Catches the UserNotFoundException and rejects the login and returns the user to the login
             * view with error message
             */
        } catch (UserNotFoundException unfe) {
            filledForm.reject("password", "User not found or incorrect password entered.");
            return badRequest(login.render(filledForm));
        }
        /**
         * If the login in successful it redirects the user to the index view
         */
        return redirect("index");
    }

    /**
     * Clears the session when the user logs out
     * @return to the index view.
     */
    public static Result logout() {
        session().clear();

        return redirect("index");
    }
}
