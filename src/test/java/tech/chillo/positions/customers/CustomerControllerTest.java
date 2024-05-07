package tech.chillo.positions.customers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @DisplayName("[Controller] Test liste des utilisateurs")
    @Test
    void shouldReturnListOfCustomers() throws Exception {
        // Arrange
        CustomerDTO customerOne = new CustomerDTO(1, "achille.mbougueng@chillo.tech");
        CustomerDTO customerTwo = new CustomerDTO(2, "accueil@chillo.tech");
        when(this.customerService.search()).thenReturn(List.of(customerOne, customerTwo));

        // Act & Assert
        this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("achille.mbougueng")));

    }

    @DisplayName("[Controller] Test lecture d'un utilisateur")
    @Test
    void shouldReturnOneCustomer() throws Exception {
        CustomerDTO customerOne = new CustomerDTO(1, "achille.mbougueng@chillo.tech");
        when(this.customerService.read(anyInt())).thenReturn(customerOne);

        this.mockMvc
                .perform(get("/customers/1"))
                .andExpect(jsonPath("$.email").value(customerOne.email()))
                .andExpect(status().isOk())
                .andDo(print())
        ;

    }


}
