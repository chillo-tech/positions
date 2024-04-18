package tech.chillo.positions.customers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final JdbcTemplate jdbcTemplate;
    private final static String FIND_ALL = "SELECT * FROM customers";
    private RowMapper<Customer> customerRowMapper =
            (rs, name) -> new Customer(rs.getInt("id"), rs.getString("email"));

    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> search() {
        return this.jdbcTemplate.query(FIND_ALL, customerRowMapper);
    }

}
