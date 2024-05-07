package tech.chillo.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerDTODaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    CustomerDao customerDao;

    @Test
    void shouldReturnListOfCustomers() {
        // Arrange / Given
        customerDao = new CustomerDao(jdbcTemplate);

        // Act / When
        List<CustomerDTO> customerDTOList = this.customerDao.search();

        // Assert / Then
        Assertions.assertEquals(8, customerDTOList.size());
        //Assertions.assertEquals(customerDTOList.get(0).email(), "accueil@chillo.tech");
    }
}
