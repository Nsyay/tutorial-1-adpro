class OrderTest {
    private List<Product> products;
    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity (2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
    }

    //unhappy
    @Test
    void testCreateOrderEmmptyProduct(){
        this.product.clear();

        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                    this.products, 1708560000L, "Safira Sudrajat");
        })
    }

    //happy
    @Test
    void testCreate0rderDefaultStatus() {
        Order order = new Order( id: "13652556-012a-4c07-b546-54eb1396d79b",
            this.products, orderTime: 1708560000L, author: "Safira Sudrajat");
        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquats("Sampo Cap Bambang", order.getProducts().get(O).getProductName());
        assertEquals("Sabun Cap Usep",order.getProducts().get(1).getProductName());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getId());
        assertEquals(1708560000L,order.get0rderTime());
        assertEquals ("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    //happy
    @Test
    void testCreateOrdersuccessstatus() {
        Order order = new Order(id:"13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat", "SUCCESS");
        assertEquals("SUCCESS", order.getstatus());
    }

    //unhappy
    @Test
    void testCreate0rderInvalidStatus() (
    assertThrows(ItlegalArgumentException.class, () -> {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
            this.products, 1708560000L, "Safira Sudrajat", "MEOW");
    });

    //happy
    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    //unhappy
    void testSetStatusToInvalidStatus(){
        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumenException.class, () -> order.setStatus("MEOW"));
    }
}