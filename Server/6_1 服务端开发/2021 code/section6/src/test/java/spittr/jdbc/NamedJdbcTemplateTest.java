package spittr.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("ut")
@ContextConfiguration(classes = JdbcConfig.class)
public class NamedJdbcTemplateTest {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String INSERT_SPITTER = "insert into Spitter (username, password, fullname, email, updateByEmail) values (:username, :password, :fullname, :email, :updateByEmail)";

    @Test
    @Transactional
    public void insertSpitter() {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("username", "taozs");
        paramMap.put("password", "password");
        paramMap.put("fullname", "taozhaosheng");
        paramMap.put("email", "tzs@163.com");
        paramMap.put("updateByEmail", true);

        namedJdbcTemplate.update(INSERT_SPITTER, paramMap);
    }
}
