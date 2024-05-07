package tech.chillo.positions.customers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> search() {
        return this.customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getEmail()))
                .collect(Collectors.toList());
    }

    public CustomerDTO read(int id) {
        Customer customer = this.customerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No customer for id "+ id));
        return new CustomerDTO(customer.getId(), customer.getEmail());
    }
}
