package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Class is responsible for information exchange between the html and dao implementations.</h1>
 * Class can work with daoMem and daoJDBC implementations as well.
 * Currently it is set up to use the daoJDBC.
 * Data is loaded from database.
 * Filtered information is sent to Spark routes.
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 *
 */

public class ProductController {

    //DAOMEM usage
    //    private static ProductDao productDataStore = ProductDaoMem.getInstance();
//    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//    private static ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();

    /** instance of ProductDaoJDBC in order to use its instance methods.*/
    private static ProductDao productDataStore = ProductDaoJDBC.getInstance();
    /** instance of ProductCategoryDaoJDBC in order to use its instance methods.*/
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
    /** instance of SupplierDaoJDBC in order to use its instance methods.*/
    private static SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();
    /** instance of ShoppingCartDaoJDBC in order to use its instance methods.*/
    private static ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoJDBC.getInstance();
    /** categoryToFilter object declared */
    private static ProductCategory categoryToFilter;
    /** supplierToFilter object declared */
    private static Supplier supplierToFilter;

    /**
     * All unfiltered data is loaded from database and forwarded to Spark.
     * @param req unused
     * @param res unused
     * @return ModelAndView object incl a HashMap with all data
     */
    public static ModelAndView renderProducts(Request req, Response res) {

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    /**
     * Filtered data is loaded from database, filtered on req and forwarded to Spark
     * @param req category to filter the data on
     * @param res unused
     * @return ModelAndView object incl a HashMap with the filtered data
     */
    public static ModelAndView renderProductsFilteredByCategory(Request req, Response res) {

        for (ProductCategory cat : productCategoryDataStore.getAll()) {
            if (req.params(":name").equals(cat.getName())) {
                categoryToFilter = productCategoryDataStore.find(cat.getId());
            }
        }

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(categoryToFilter));
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    /**
     * Filtered data is loaded from database, filtered on req and forwarded to Spark
     * @param req supplier to filter the data on
     * @param res unused
     * @return ModelAndView object incl a HashMap with the filtered data
     */
    public static ModelAndView renderProductsFilteredBySupplier(Request req, Response res) {

        for (Supplier sup : supplierDataStore.getAll()) {
            if (req.params(":name").equals(sup.getName())) {
                supplierToFilter = supplierDataStore.find(sup.getId());
            }
        }

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getBy(supplierToFilter));
        params.put("suppliers", supplierDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    /**
     * All products in shopping cart are loaded from database and forwarded to Spark
     * @param req unused
     * @param res unused
     * @return ModelAndView object incl a HashMap with products in the cart
     */
    public static ModelAndView renderCart(Request req, Response res) {

        Map params = new HashMap<>();
        params.put("cart", shoppingCartDataStore.getAll());
        params.put("TotalPrice", shoppingCartDataStore.getTotal());
        return new ModelAndView(params, "product/cart");
    }
}

