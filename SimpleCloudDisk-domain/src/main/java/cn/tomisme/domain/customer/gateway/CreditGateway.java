package cn.tomisme.domain.customer.gateway;

import cn.tomisme.domain.customer.Customer;
import cn.tomisme.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
