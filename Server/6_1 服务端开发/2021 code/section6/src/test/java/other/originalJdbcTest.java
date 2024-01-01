package other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.domain.Spitter;
import spittr.jdbc.JdbcConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("ut")
@ContextConfiguration(classes = JdbcConfig.class)
public class originalJdbcTest {

    @Autowired
    private DataSource dataSource;

    private static final String INSERT_SPITTER = "insert into Spitter (username, password, fullname, email, updateByEmail) values (?, ?, ?, ?, ?)";

    @Test
    public void insertSpitter() {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(INSERT_SPITTER);

            stmt.setString(1, "newbee");
            stmt.setString(2, "letmein");
            stmt.setString(3, "New Bee");
            stmt.setString(4, "newbee@habuma.com");
            stmt.setBoolean(5, true);

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
