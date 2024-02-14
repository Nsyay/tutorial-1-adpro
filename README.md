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
   * 