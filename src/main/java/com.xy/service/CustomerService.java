package com.xy.service;


import com.xy.helper.DatabaseHelper;
import com.xy.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by issuser on 2017/9/23.
 *
 * 提供客户数据服务
 */
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList(){
        List<Customer> customerList = new ArrayList<Customer>();
        Connection conn = null;
        String sql = "SELECT * FROM CUSTOMER";
        //连接数据库，
        try{
            conn = DatabaseHelper.getConnection();
            //创建statement
            customerList = DatabaseHelper.queryEntityList(Customer.class,conn,sql);
        }catch(Exception e){
            logger.error("excute sql failuer",e);
        }
        return customerList;
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id){
        String sql = "SELECT * FROM CUSTOMER WHERE ID = '"+id+"'";
        return DatabaseHelper.queryEntity(Customer.class,sql);
    }

    /**
     * 创建客户
     * @param fileMap
     * @return
     */
    public boolean createCustomer(Map<String,Object> fileMap){
        //TODO
        return false;
    }

    /**
     * 更新客户
     * @param id
     * @param fileMap
     * @return
     */
    public boolean updateCustomer(long id,Map<String,Object> fileMap){
        //TODO
        return false;
    }


    public boolean deleteCustomer(long id){
        //TODO
        return false;
    }

}
