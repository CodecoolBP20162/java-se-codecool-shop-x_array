/**
 * Created by majoross on 2017.05.11..
 */

import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DaoJdbcTest {


    @Test
    public void productGetAllTest() {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        List<Product> products = productDaoJDBC.getAll();
        assertEquals(9, products.size());
    }

    @Test
    public void supplierGetAllTest() {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        List<Supplier> suppliers = supplierDaoJDBC.getAll();
        assertEquals(4, suppliers.size());
    }

    @Test
    public void productCategoryGetAllTest() {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        List<ProductCategory> productCategories = productCategoryDaoJDBC.getAll();
        assertEquals(3, productCategories.size());
    }

    @Test
    public void getproductByIdTest() {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        Product product = productDaoJDBC.find(1);
        assertEquals("Amazon Fire", product.getName());
        assertEquals(49.9f, product.getDefaultPrice());
        assertEquals("USD", product.getDefaultCurrency().toString());
        assertEquals("Fantastic price. Large content ecosystem.", product.getDescription());
    }

    @Test
    public void getSupplierByIdTest() {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        Supplier supplier = supplierDaoJDBC.find(1);
        assertEquals("Amazon", supplier.getName());
        assertEquals("Digital content and services", supplier.getDescription());
    }

    @Test
    public void getProductCategoryById() {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        ProductCategory productCategory = productCategoryDaoJDBC.find(1);
        assertEquals("Tablet", productCategory.getName());
        assertEquals("Hardware", productCategory.getDepartment());
        assertEquals("A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.", productCategory.getDescription());
    }

    @Test
    public void invalidProductFindParameter() throws IllegalArgumentException {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productDaoJDBC.find(-1);
        });
    }

    @Test
    public void invalidSupplierFindParameter() throws IllegalArgumentException {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoJDBC.find(-1);
        });
    }

    @Test
    public void invalidProductCategoryFindParameter() throws IllegalArgumentException {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoJDBC.find(-1);
        });
    }

    @Test
    public void invalidProductRemoveParameter() throws IllegalArgumentException {
        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productDaoJDBC.remove(-1);
        });
    }

    @Test
    public void invalidSupplierRemoveParameter() throws IllegalArgumentException {
        SupplierDaoJDBC supplierDaoJDBC = SupplierDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoJDBC.remove(-1);
        });
    }

    @Test
    public void invalidProductCategoryRemoveParameter() throws IllegalArgumentException {
        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoJDBC.remove(-1);
        });
    }
}
