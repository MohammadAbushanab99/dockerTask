package com.example;//package com.example;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepository extends JpaRepository<Users,Integer> {
//
//    Users findByUsername(String username);
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String checkUserInput(String username, String password) {
        String  pass = "";
        try {
            String query = "SELECT password FROM users WHERE username = ? AND password = ?";
             pass = jdbcTemplate.queryForObject(query,String.class, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }
}