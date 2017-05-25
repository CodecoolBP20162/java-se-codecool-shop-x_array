package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoMem.class);

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(DATA.size() + 1);
        DATA.add(supplier);
    }

    @Override
    public Supplier find(int id) throws IllegalArgumentException{
        if(id < 1){
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be lower than 1");
        }

        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) throws IllegalArgumentException {
        if(id < 1){
            logger.warn("Id cannot be smaller than 1");
            throw new IllegalArgumentException("Id cannot be lower than 1");
        }
        DATA.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}
