/**
 *  Class that handles the user registration. It encapsulates the data
 *  and passes data to and from the data layer and to the controllers.
 *
 *
 *@author Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
 *@version 1.7, 21 Nov 2013
 */

package is.ru.honn.rustagram.domain;

import java.util.Date;


public class UserRegistration extends User {

    /**
     * String variable created for use in functions.
     */
    protected String repeatPassword;

    /**
     * Empty constructor for the UserRegistration
     */
    public UserRegistration() {

    }

    /**
     * Receives the object from the data layer with all the information from the database.
     * @param id of the user
     * @param username of the user
     * @param password of the user
     * @param displayName of the user
     * @param email of the user
     * @param registered of the user
     * @param repeatPassword of the user
     */

    public UserRegistration(int id, String username, String password, String displayName,
                            String email, Date registered, String repeatPassword) {
        super(id, username, password, displayName, email, registered);
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the registration info from the user and forwards it to the data layer in an object.
     *
     * @param username of the user
     * @param password of the user
     * @param displayName of the user
     * @param email of the user
     * @param repeatPassword  of the user for verification that the password is correc
     */

    public UserRegistration(String username, String password, String displayName,
                            String email, String repeatPassword) {
        super(username, password, displayName, email);
        this.repeatPassword = repeatPassword;
    }

    /**
     * Returns the password
     * @return the password the user entered for verification that the password is the same
     */

    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Sets the repeat password
     * @param repeatPassword the user entered.
     */

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }


}