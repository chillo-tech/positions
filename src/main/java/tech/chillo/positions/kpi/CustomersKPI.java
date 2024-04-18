package tech.chillo.positions.kpi;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import tech.chillo.positions.customers.CustomerService;

@AllArgsConstructor
@Component
@Endpoint(id = "customers")
public class CustomersKPI {
    CustomerService customerService;
    @ReadOperation
    public int count() {
        return this.customerService.search().size();
    }

}
