package com.customermanagement.customermanagement.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



//以下為Document層操作步驟://

//1-1.宣告為Document層 (類似Entity，可以理解為資料庫表中的每一行)。
//1-2 @Document (collection = "customer")   將此類映射到 "customer" 集合(類似資料庫的表)。
    //Document 用於 MongoDB，代表文檔（document）。
    //映射（Mapping）是將這個 Java 類的屬性(key)對應到 MongoDB 中文檔的結構(value)，形成 key-value 對應關係。

//2.設定該類中的變數 (客戶管理系統-id、姓名、姓氏、Email...等 依需求設計)

//3-1.宣告 @Id 宣告變數Id為主鍵
//3-2.註解 @NotEmpty(message = "名字為必填項目") 設定該欄資料不可為空，為空時出現message訊息提示。
//3-3.註解 @Email(message ="請填入正確的Email") 須符合email格式XXX@XXX，該欄不可為空，可以再多註解@NotEmpty，不多註解也行。

//4-1.Generate出 無參數建構式
//4-2.Generate出 四個變數的有參數建構式
//4-3.Generate出 Getter Setter

//5.紅字該import的import

//這頁先這樣，若還有紅字等其他頁完成再來檢查一次。


//以上為操作步驟//




//NoSQL的稱呼，類似於之前的Entity，作用相同 //Entity : 數據庫表
@Document (collection = "customer")  // 將此類映射到 "customer" 集合
public class Customer {

   @Id  //Id為主鍵
    private String id;
    //使用String而不是lone、int等數值類型的原因為可以靈活地表示各種唯一標示符（如 UUID 或其他業務規則生成的字符串）
    //在MongoDB中，預設的id格式是UUID（Universally Unique Identifier），
    // 這裡提供一個產生的UUID的範例：560266b0-2bf0-4241-a578-66efb7c3ec80。


    //姓名
    //留空時會出現錯誤，前端顯示message
    @NotEmpty(message = "名字為必填項目")
    private String firstName;

    //姓氏
    @NotEmpty(message = "姓氏為必填項目")
    private String lastName;

    //Email
    //@Email須符合email格式 XXX@XXX，否則會顯示錯誤及message
    @Email(message ="請填入正確的Email")
    private String email;

    //無參數建構式
    public Customer() {
    }


    //有參數建構式
    public Customer(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //GETTER、SETTER

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotEmpty(message = "名字為必填項目") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotEmpty(message = "名字為必填項目") String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty(message = "姓氏為必填項目") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty(message = "姓氏為必填項目") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "請填入正確的Email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "請填入正確的Email") String email) {
        this.email = email;
    }
}
