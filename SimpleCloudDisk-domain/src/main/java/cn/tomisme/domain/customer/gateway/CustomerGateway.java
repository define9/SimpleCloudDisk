package cn.tomisme.domain.customer.gateway;

import cn.tomisme.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
