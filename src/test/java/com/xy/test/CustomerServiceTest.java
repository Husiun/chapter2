package com.xy.test;

import com.xy.model.Customer;
import com.xy.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by issuser on 2017/9/24.
 *
 * CustomerService测试类
 */
public class CustomerServiceTest {
    private CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init(){
        //TODO
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        System.out.println("customer number = " + customerList.size());
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomer(){
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        System.out.println("name = "+customer.getName());
        Assert.assertNotNull(customer);
    }

    public void createCustomerTest(){
        Map<String,Object> fileMap = new HashMap<String,Object>();
        fileMap.put("name","cus100");
        fileMap.put("contact","cusfff");
        fileMap.put("telephone","1774488899");
        boolean result = customerService.createCustomer(fileMap);
        Assert.assertTrue(result);
    }

    public void updateCustomerTest(){
        long id = 2;
        Map<String,Object> fileMap = new HashMap<String, Object>();
        fileMap.put("name","hahah");
        boolean result = customerService.updateCustomer(id,fileMap);
        Assert.assertTrue(result);
    }

    public void deleteCustomerTest(){
        long id = 3;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
