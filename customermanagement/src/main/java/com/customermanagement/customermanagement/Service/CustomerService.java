package com.customermanagement.customermanagement.Service;


import com.customermanagement.customermanagement.Document.Customer;
import com.customermanagement.customermanagement.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //宣告Service層，負責業務邏輯
public class CustomerService{
    private final CustomerRepository customerRepository;

    //有參數建構式
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //新增    // void 無回傳值
    //建立客戶並儲存 save
    public void createCustomer(Customer customer){
        customerRepository.save(customer);
    }

    //查詢 All //有回傳值
    //取得全部客戶 findAll
    public List<Customer> getAllCustomers(){
            //用List接客戶資料
        return customerRepository.findAll();
        //從customerRepository資料提取層找到所有的客戶資料並回傳
    }

    //查詢 id //有回傳值
    //取得指定ID的用戶
    public Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(null);
                                               //id錯誤時返回null
    }

    //修改     //void 無回傳值
    //修改客戶內容
    public void updateCustomer(Customer customer) {
        Customer oldCustomer = customerRepository.findById(customer.getId()).orElse(null);
        //Customer oldCustomer 這個要被修改的客戶  //id在資料庫內是否存在

        if (oldCustomer != null) {  //如果這個被查詢的id不等於null (也就是存在的意思)

            //進行修改
            oldCustomer.setFirstName(customer.getFirstName()); //修改名字
            //舊客戶對象(從資料庫查詢到的客戶對象)← //將新名字更新到舊客戶對象中←//從 customer 對象中取得新名字

            oldCustomer.setLastName(customer.getLastName());//修改姓氏

            oldCustomer.setEmail(customer.getEmail()); //修改email
            customerRepository.save(oldCustomer); //儲存
        }
    }

    //刪除     //void無回傳值
    //刪除客戶
    public void deleteCustomer(String id){
        customerRepository.deleteById(id);
    }

}

