/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserData extends RuData implements UserDataGateway {
    public int addUser(User user) {
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("ru_users")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>(7);
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("displayName", user.getDisplayName());
        parameters.put("email", user.getEmail());
        parameters.put("registered", user.getRegistered());

        int returnKey;

        try {
            returnKey = insert.executeAndReturnKey(parameters).intValue();
        } catch (DataIntegrityViolationException divex) {
            throw new UsernameExistsException("User " + user.getUsername() + " already exits", divex);
        }

        user.setId(returnKey);
        return returnKey;
    }

    public int addUserRegistration(UserRegistration userRegistration) {
        SimpleJdbcInsert insert =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("ru_users")
                        .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<String, Object>(7);
        parameters.put("username", userRegistration.getUsername());
        parameters.put("password", userRegistration.getPassword());
        parameters.put("displayName", userRegistration.getDisplayName());
        parameters.put("email", userRegistration.getEmail());
        parameters.put("registered", userRegistration.getRegistered());

        int returnKey;

        try {
            returnKey = insert.executeAndReturnKey(parameters).intValue();
        } catch (DataIntegrityViolationException divex) {
            throw new UsernameExistsException("User " + userRegistration.getUsername() + " already exits", divex);
        }

        userRegistration.setId(returnKey);
        return returnKey;
    }

    public User getUserByUsername(String username) {
        Collection<String> users;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        User user;
        try {
            user = (User) jdbcTemplate.queryForObject(
                    "select * from ru_users where username = '" + username + "'", new UserRowMapper());
        } catch (EmptyResultDataAccessException erdaex) {
            throw new UserNotFoundException("No user found with username: " + username);
        }
        return user;
    }
}
