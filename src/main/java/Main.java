import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        get("/supplier/:name", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProductsFilteredBySupplier(req, res));
        });

        get("/category/:name", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProductsFilteredByCategory(req, res));
        });

        //Shopping Cart
        get("/cart", ProductController::renderCart, new ThymeleafTemplateEngine());

        //Add to cart
        get("/add/:id", (Request req, Response res) -> {

            Product product = ProductDaoMem.getInstance().find(Integer.parseInt(req.params(":id")));
            LineItem item = new LineItem(product, 1);
            ShoppingCartDaoMem.getInstance().add(item);

            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        get("/cart1/:id", (Request req, Response res) -> {
            LineItem item = ShoppingCartDaoMem.getInstance().find(Integer.parseInt(req.params(":id")));
            item.changeAmount(1);
            return new ThymeleafTemplateEngine().render(ProductController.renderCart(req, res));

        });

        get("/cart-1/:id", (Request req, Response res) -> {
            LineItem item = ShoppingCartDaoMem.getInstance().find(Integer.parseInt(req.params(":id")));
            item.changeAmount(-1);
            return new ThymeleafTemplateEngine().render(ProductController.renderCart(req, res));
        });

        get("/cart/remove/:id", (Request req, Response res) -> {
            LineItem item = ShoppingCartDaoMem.getInstance().find(Integer.parseInt(req.params(":id")));
            ShoppingCartDaoMem.getInstance().remove(item);
            item.setQuantity(1);
            return new ThymeleafTemplateEngine().render(ProductController.renderCart(req, res));
        });


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "not for lunch");
        supplierDataStore.add(apple);
        Supplier dell = new Supplier("Dell", "Like doll but with an e");

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop, often called a notebook or notebook computer, is a small, portable personal computer with a clamshell form factor, an alphanumeric keyboard on the lower part of the clamshell and a thin LCD or LED computer screen on the upper portion, which is opened up to use the computer.");
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A mobile phone is a portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area.");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(laptop);
        productCategoryDataStore.add(phone);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Good parental controls.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8. Great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Dell Vostro", 600, "USD", "Dell Vostro is a line of computers from Dell.", laptop, dell));
        productDataStore.add(new Product("Macbook Pro", 1500, "USD", "its more than a book trust me", laptop, apple));
        productDataStore.add(new Product("Lenovo", 800, "USD", "yo wassup im out of ideas", laptop, lenovo));
        productDataStore.add(new Product("Iphone 7", 700, "USD", "imagine calling your girl with an apple", phone, apple));
        productDataStore.add(new Product("Phab 2 Pro", 500, "USD", "buy it to be phabolous", phone, lenovo));
        productDataStore.add(new Product("Amazon Fire Phone", 450, "USD", "amazon has its own electronic devices!!!", phone, amazon));

        //setting up shopping cart
        ShoppingCart cart1 = new ShoppingCart();


    }


}
