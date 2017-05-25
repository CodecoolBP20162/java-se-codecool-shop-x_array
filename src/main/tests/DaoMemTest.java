import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DaoMemTest {
    @Test
    public void invalidProductFindParameter() throws IllegalArgumentException {
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productDaoMem.find(-1);
        });
    }

    @Test
    public void invalidSupplierFindParameter() throws IllegalArgumentException {
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoMem.find(-1);
        });
    }

    @Test
    public void invalidProductCategoryFindParameter() throws IllegalArgumentException {
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoMem.find(-1);
        });
    }

    @Test
    public void invalidProductRemoveParameter() throws IllegalArgumentException {
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productDaoMem.remove(-1);
        });
    }

    @Test
    public void invalidSupplierRemoveParameter() throws IllegalArgumentException {
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoMem.remove(-1);
        });
    }

    @Test
    public void invalidProductCategoryRemoveParameter() throws IllegalArgumentException {
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoMem.remove(-1);
        });
    }

    @Test
    public void productGetAllTest() {
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        ProductCategory productCategory = new ProductCategory("testcategory", "hardware", "desc");
        Supplier supplier = new Supplier("testsupplier", "description");
        Product product = new Product("test", 20, "USD", "description", productCategory, supplier);
        Product product1 = new Product("test1", 20, "USD", "description", productCategory, supplier);
        productDaoMem.add(product);
        productDaoMem.add(product1);
        List<Product> products = productDaoMem.getAll();
        assertEquals(2, products.size());

    }

    @Test
    public void supplierGetAllTest() {
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        Supplier supplier = new Supplier("testsupplier", "description");
        Supplier supplier1 = new Supplier("testsupplier1", "description");
        supplierDaoMem.add(supplier);
        supplierDaoMem.add(supplier1);
        List<Supplier> suppliers = supplierDaoMem.getAll();
        assertEquals(2, suppliers.size());
    }

    @Test
    public void productCategoryGetAllTest() {
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        ProductCategory productCategory = new ProductCategory("testcategory", "hardware", "desc");
        ProductCategory productCategory1 = new ProductCategory("testcategory1", "hardware", "desc");
        productCategoryDaoMem.add(productCategory);
        productCategoryDaoMem.add(productCategory1);
        List<ProductCategory> productCategories = productCategoryDaoMem.getAll();
        assertEquals(2, productCategories.size());
    }
}
