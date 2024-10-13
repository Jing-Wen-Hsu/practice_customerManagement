package com.customermanagement.customermanagement.Controller;

import com.customermanagement.customermanagement.Document.Customer;
import com.customermanagement.customermanagement.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//使用了thymeleaf作為前端，因此要使用Controller來呈現我們的網頁內容。
public class CustomerController {

    //定義了一個私有且不可變的 CustomerService 屬性，
    // 使得 CustomerController 可以訪問服務層的業務邏輯，從而處理與客戶相關的請求和操作。
    private final CustomerService customerService;

    //有參數建構式
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //顯示資料庫中全部的客戶資料
    @GetMapping("/")

    //傳遞數據到視圖(前端畫面)
    public String listConsumers(Model model) {

        //透過customerService 的 getAllCustomers() 方法來獲取所有客戶資料，
        // 並將結果存儲在 customers 列表中
        List<Customer> customers = customerService.getAllCustomers();

        // 使用model才能把資料從後端傳給前端，"customers"是識別用的名稱
        model.addAttribute("customers", customers);
        //表示要渲染的視圖名稱。
        // 通常，這個名稱會對應於一個 HTML 模板（例如，index.html）
        // Thymeleaf 將會用這個模板來生成最終的 HTML 頁面。
        return "index"; //index.html
    }


    //顯示新增客戶的網頁
    @GetMapping("/new")
    public String newCustomer(Model model) {

        //創建一個空的新客戶並傳送到前端
        Customer customer = new Customer();
            model.addAttribute("customer", customer);
        return "new_customer";    //顯示new_customer.html
    }

    //新增 Post
    //新增並儲存新增的客戶
    @PostMapping("/new")
    //Valid用來在前端驗證內容(不可為空、emali格式等)-Document有定義
    // ModelAttribute用來接收前端中customer的資料
    //如果資料不符合規定，例如留空或email格式錯誤，就會將document設定的訊息顯示在網頁上
    public String saveCustomer(Model model, @Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            model.addAttribute("customer",customer);
            return "new_customer"; //返回新增客戶的頁面，並顯示錯誤訊息。
        }
        //符合規定就可以直接新增
        customerService.createCustomer(customer);
        return "redirect:/";// 重定向到客戶列表頁面（可選）
    }

    //查詢 Get
    //查看單一客戶的頁面(指定id)
    @GetMapping("/view/{id}")
    //@PathVariable("id") String id：表示從路徑中提取 id 變數，並將其賦值給 id 參數，這樣就可以在方法內使用該 ID 來查詢客戶資料。
        public String viewCustomer(Model model, @PathVariable("id") String id){

        //將資料庫的客戶內容顯示在網頁上
        Customer customer = customerService.getCustomerById(id);
                                        //第一個custome是屬性名稱，key ;第二個是屬性的值value,客戶對象。
        model.addAttribute("customer",customer);
        return "view_customer";
    }

    //修改

    //為什麼使用 GET 而不是 PUT
    //目的不同
    //GET：用於從服務器獲取資源。當你要顯示一個編輯頁面時，你需要從資料庫中獲取該資源的當前狀態，以便用戶可以在表單中查看和修改。
    //PUT：用於更新資源的狀態。當你需要將修改後的資料提交到服務器以更新資料庫中的資源時，才會使用 PUT 方法。

    //顯示修改客戶的網頁(指定id)，注意這裡是"顯示"網頁 不是修改，因此用get!
    @GetMapping("/edit/{id}")
        public String editCustomer(Model model,@PathVariable("id") String id){

        //將資料庫原本的資料內容顯示在網頁上
        Customer customer = customerService.getCustomerById(id); //從資料庫調用指定ID的客戶資料
        model.addAttribute("customer",customer); //將客戶資料添加到模型中，以便在網頁上顯示
        return "edit_customer";
    }

    //儲存修改的客戶內容
    @PostMapping("/edit/{id}")
        public String updateCustomer(Model model,@PathVariable("id") String id,@Valid@ModelAttribute("customer") Customer customer,BindingResult bindingResult){

        //如果資料不符合規定，例如留空或email格式錯誤，就會將document設定的訊息顯示在網頁上
        if(bindingResult.hasErrors()){
            //將用戶在表單中已經輸入的資料保留在編輯頁面上(保留輸入的錯誤資料，增加用戶體驗)
            model.addAttribute("customer",customer);  // 將當前的客戶資料添加到模型中
            return "edit_customer"; // 返回編輯客戶的頁面，顯示錯誤訊息
        }
        // 如果資料有效(符合規定)，調用服務層來更新客戶資料
        customerService.updateCustomer(customer);
        // 更新成功後，重定向到客戶列表頁面
        return "redirect:/";// 重新導向到根路徑
    }


    //刪除客戶
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
