/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ruframework.data.RuDataAccess;

public interface UserDataGateway extends RuDataAccess {
    public int addUser(User user);

    public int addUserRegistration(UserRegistration userRegistration);

    public User getUserByUsername(String username);
}