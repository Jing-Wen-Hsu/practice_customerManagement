package com.customermanagement.customermanagement.Repository;

import com.customermanagement.customermanagement.Document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;


//Repository 可以被視為資料提取層（Data Access Layer）或數據訪問層，它負責從數據源（如資料庫）中提取資料。

//以下為Repository層操作步驟://

//1.改為介面(interface)，並繼承MongoRepository<Customer,String>
                        //透過繼承MongoRepository來使用Spring Data MongoDB
//2.紅字該import的import

//以上為Repository層操作步驟://

//提供的許多方法來進行數據操作。
//Customer 是你定義的文檔類，代表了 MongoDB 中的文檔結構。
//String 表示主鍵的資料型態，即每個 Customer 的 ID。


                                                                //Customer是Document，String是id的資料型態。
    public interface CustomerRepository extends MongoRepository<Customer,String>{

//        若有需要可自行新增

    }
