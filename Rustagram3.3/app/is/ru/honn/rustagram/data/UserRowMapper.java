/*
*@(#)Hello java 1.7 21 Nov 2013 Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*
*Copyright(c)Davíð Snæhólm Baldursson og Sindri Þór Stefánsson
*/
package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements ParameterizedRowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt(1),  // id
                rs.getString(2),        // username
                rs.getString(3),        // password
                rs.getString(4),        // displayName
                rs.getString(5),        // email
                rs.getDate(7)           // registered
        );
    }
}
