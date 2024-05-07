package tech.chillo.positions.customers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> search() {
        return this.customerService.search();
    }

    @GetMapping(path = "{id}")
    public CustomerDTO read(@PathVariable int id) {
        return this.customerService.read(id);
    }
}
