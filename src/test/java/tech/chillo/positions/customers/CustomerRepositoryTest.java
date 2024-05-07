package tech.chillo.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    void shouldReturnAllCustomers() {
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("achille.mbougueng@chillo.tech").build();
        Customer customerTwo = Customer.builder().id(2).email("accueil@chillo.tech").build();
        this.customerRepository.saveAll(List.of(customerOne, customerTwo));

        // Act
        final List<Customer> customerList = this.customerRepository.findAll();

        // Assert
        Assertions.assertEquals(2, customerList.size());
    }

    @Test
    void should_return_customer_by_email() {
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("achille.mbougueng@chillo.tech").build();
        Customer customerTwo = Customer.builder().id(2).email("accueil@chillo.tech").build();
        this.customerRepository.saveAll(List.of(customerOne, customerTwo));

        // Act
        final Customer customer = this.customerRepository.findByEmail("accueil@chillo.tech");

        // Assert
        Assertions.assertEquals(customerTwo.getId(), customer.getId());
        Assertions.assertEquals(customerTwo.getEmail(), customer.getEmail());
    }

}
