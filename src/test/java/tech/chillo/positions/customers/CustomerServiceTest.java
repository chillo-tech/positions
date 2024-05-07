package tech.chillo.positions.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @DisplayName("[Service] Test liste des utilisateurs")
    @Test
    void shouldReturnAllCustomers() {
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("achille.mbougueng@chillo.tech").build();
        Customer customerTwo = Customer.builder().id(2).email("accueil@chillo.tech").build();
        when(this.customerRepository.findAll()).thenReturn(List.of(customerOne, customerTwo));

        // Act
        final List<CustomerDTO> customerList = this.customerService.search();

        // Assert
        Assertions.assertEquals(2, customerList.size());
    }

    @DisplayName("[Service] Test lecture d'un utilisateur")
    @Test
    void shouldReturnCustomerById() {
        // Arrange
        Customer customerOne = Customer.builder().id(1).email("achille.mbougueng@chillo.tech").build();
        when(this.customerRepository.findById(1)).thenReturn(Optional.of(customerOne));

        // Act
        final CustomerDTO customerDTO = this.customerService.read(1);

        // Assert
        Assertions.assertEquals(customerOne.getId(), customerDTO.id());
    }

    @Test
    void shouldThrowException( ) {
        // Arrange
        when(this.customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Act
        // Assert
        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.customerService.read(1)
        );

        assertEquals("No customer for id 1", exception.getMessage());
    }
}
