package user;

import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

import static java.sql.JDBCType.INTEGER;
import static java.sql.JDBCType.VARCHAR;
//import static java.util.Calendar.DATE;
import static javax.swing.plaf.synth.Region.TABLE;
//import static sun.security.x509.CertificateX509Key.KEY;

@RegisterBeanMapper(User.class)
public interface UserDao extends SqlObject {


    @SqlUpdate("""
            CREATE TABLE user (
            id IDENTITY PRIMARY KEY ,
            username VARCHAR NOT NULL,
            password VARCHAR NOT NULL,
            name VARCHAR NOT NULL,
            email VARCHAR NOT NULL,
            gender VARCHAR NOT NULL,
            localdate INTEGER NOT NULL
    )
        """
                )

    void createTable();

    @SqlUpdate("INSERT INTO user VALUES (:username, :password, :name, :email, :gender, :localdate)")
    @GetGeneratedKeys
    Long insertUser(@BindBean User user);

    @SqlQuery("SELECT * FROM user")
    Optional<User> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM user WHERE username IS NOT NULL")
    Optional<User> findByUsername(@Bind("username")String username);

    void delete(User user);

    @SqlQuery("SELECT * FROM user ORDER BY id")
    List<User> listUsers();


}
