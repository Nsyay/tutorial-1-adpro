[Link Eshop](https://eshop-nasya-tasks.koyeb.app/)
# Module 1
## Reflection 1

Dalam membuat program ini saya menerapkan coding standards yaitu penerapan clean code dan secure coding.
- Contoh penerapan clean code yang saya buat pada program ini yaitu:
```dtd
# show product
@GetMapping("/product/list")
public String productList(Model model) {
    List<Product> products = productService.findAll();
    model.addAttribute("products", products);
    return "product/list";
}
```
potongan code ini menerapkan :
1. Meaningful Names --> dengan nama variable "product" yang jelas
2. Function --> menerapkan function productList untuk menampilkan list product
3. Comments --> komen menjelaskan tujuan fungsi ini
4. Object and Data Structure --> implementasi objek product
5. Error handling --> salah satunya merupakan function yang dipanggil dari repository

- Contoh penerapan security coding pada program ini yaitu:
```
public Product create(Product product){
    String productId = UUID.randomUUID().toString();
    product.setProductId(productId);
    productData.add(product);
    return product;
}
 ```
Saya mengatakan bahwa potongan code ini menerapkan security coding
karena menggunakan UUID (Universally Unique Identifier) yang merupakan string panjang, unik, sulit ditebak
untuk meminimalisir risiko terjadinya serangan IDOR

## Reflection 2
1. Setelah membuat unit test, saya menjadi semakin percaya diri
terhadap keakuratan code yang saya buat. Saya telah menyadari 
bahwa saat ini untuk mengecek keakuratan code saya dapat melakukan
testing lebih mudah dengan unit test tanpa saya perlu menjalankan codenya
bertahap. Banyaknya unit test yang harus dibuat adalah bergantung 
seberapa kompleks suatu class tersebut. Setidaknya, banyak unit test
yang dibuat dapat memprediksi worst-case input apa saja yang dapat terjadi.
Oleh karena itu, menurut saya, seberapa banyak unit test yang dibuat tidak penting.
Yang penting adalah seberapa mungkin unit test yang dibuat dalam memprediksi
kemungkinan yang dapat terjadi. 100% code coverage menurut saya tidak menutup kemungkinan 
terdapat bug atau error didalamnya
2. Dalam pembuatan uji fungsional baru dengan setup procedure dan instance variables
yang sama tentu akan mempengaruhi clean code.
Akan terjadi pengulangan code karena setup procedure dan instance variables yang sama.
Dapat diperbaiki dengan memisahkan setup procedure di file yang berbeda, kemudian tinggal dipanggil

--------------------------------------------------------
# Module 2
## Reflection 1

1. Code quality issue yang saya temukan dengan PMD
   * Unnecessary modifier 'public' on method 'create' in interface, karena semua variabel di interface sudah otomatis public static final. Saya melakukan perbaikan dengan menghapus modifier 'public'
   * Unused import 'import org.springframework.web.bind.annotation.*;', karena mengimport semua tetapi banyak yang tidak digunakan. Melakukan perbaikan dengan mengimport yang diperlukan saja
   * Remove unused import 'java.util.UUID' from Product.java in model. Sebelumnya saya menggunakan UUID di Product.java model, namun akhirnya saya menggunakan UUID pada repository, sehingga import UUID sebelumnya belum saya hapus
2. Menurut saya, CI/CD workflows yang sudah saya implementasi telah memenuhi definisi dari Continuous Integration dan Continuous Deployment. Pertama, semua workflow akan di-trigger setiap push dan pull request. Kemudian, program akan dicek juga quality nya melalui workflow PMD dan scorecard.
   Selain itu, workflow akan mengeksekusi `./gradlew test` yang akan menjalankan unit test yang sudah dibuat sebelumnya.
   Dalam pengimplementasian Continuous Deployment juga menurut saya sudah memenuhi definisinya, dimana CD adalah proses deploy yang telah terintegrasi dengan repository sehingga setiap perubahan yang dibuat pada code akan otomatis menjalankan proses deploy. CD eshop ini saya menggunakan PaaS koyeb. PaaS koyeb telah menjalankan fungsi continuous deployment dengan sangat baik, sehingga memenuhi definisi CI/CD workflows.

--------------------------------------------------------
# Module 3
## Reflection 1

1. SOLID Principle yang saya terapkan pada program ini yaitu :
   * _**Single Responsibility Principle (SRP)**_
      
      SRP saya implementasikan dengan memisahkan controller untuk home, product, dan car. Sehingga saat ini saya memiliki `CarController`, `ProductController`, dan `HomeController`.
      Saya juga mengimplementasikan SRP dengan menghapus `extends ProductController` pada CarController agar lebih berfokus terhadap satu objek saja sesuai dengan prinsip SRP
   * _**Open-Closed Principle (OCP)**_
      
      Model Product dan Car sudah memenuhi prinsip OCP karena jika ingin menambah method baru, dapat ditambahkan di class lain seperti pada Repository.
   * _**Liskov Substitution Principle (LSP)**_
   
      LSP sudah diimplementasikan di class Car dan Product ServiceImpl dengan Car dan Product service. Semua method pada Service class telah diimplementasi dan didefinisikan dengan benar di ServiceImpl class.
   * _**Interface Segregation Principle (ISP)**_
      
      ISP diimplementasikan di service, dimana Car dan Product service interface hanya menyediakan metode yang diperlukan untuk berinteraksi dengan object car dan product. Sedangkan Car dan Product serviceImpl mengimplementasikan method yang terdapat di CarService interface.
      Hal ini memenuhi prinsip ISP karena tidak bergantung pada fitur yang tidak digunakan.
   * _**Dependency Inversions Principle (DIP)**_
      
      DIP saya implementasi dengan mengubah `CarServiceImpl` menjadi `CarService` pada CarController untuk mencegah modul tingkat tinggi tidak bergantung dengan modul tingkat rendah. Juga tidak akan merusak CarController apabila dilakukan perubahan pada class CarServiceImpl. 
2. Keuntungan menerapkan SOLID Principle yaitu :
   * Code yang menerapkan SOLID Principle akan lebih clean dan lebih mudah di maintain (code maintainability)
   * Code akan lebih mudah dipahami dan dikembangkan jika suatu saat akan dilakukan perubahan
   * Pengubahan dan pengurangan code tidak akan merusak program secara keseluruhan
   * Mengurangi Dupliasi kode (DRY-Don't Repeat Yourself)
   * Meningkatkan keamanan sistem pada program karena adanya batasan akses
   * Testing pada program dapat berjalan secara lebih efektif karena setiap komponen sudah terpisah untuk memastikan kebenaran fungsionalitas
   
   _example_ : Ketika saya ingin menambah fungsionalitas baru, saya hanya perlu membuat interface baru pada CarService tanpa memengaruhi code yang sudah ada sebelumnya
3. Kerugian menerapkan SOLID Principle yaitu :
   * Code akan sulit dipahami karena kurang terstruktur dan terorganisir
   * Karena sulit dipahami, Code juga akan sulit dikembangkan ketika ingin menambah fungsionalitas baru
   * Code akan lebih sulit untuk di maintain
   * Suatu perubahan atau penambahan yang dibuat pada program berkemungkinan besar merusak fungsionalitas program yang lain
   
   _example_ : Sebelum diterapkannya SOLID, `CarController` dan `ProductController` masih digabungkan, sehingga banyak test yang gagal karena path tidak sesuai.