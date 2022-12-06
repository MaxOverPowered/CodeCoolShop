package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;
<<<<<<< HEAD
import java.util.Objects;

public class Product extends BaseModel {
    private int id;
=======

public class Product extends BaseModel {

>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    private BigDecimal defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
<<<<<<< HEAD
    private String picture;

    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, String picture) {
=======


    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
<<<<<<< HEAD
        this.picture = picture;
=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

<<<<<<< HEAD
    @Override
    public int getId() {
        return id;
    }

=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

<<<<<<< HEAD
    public String getPicture() {
        return this.picture;
    }

=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

<<<<<<< HEAD

=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
<<<<<<< HEAD

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(defaultPrice, product.defaultPrice) && Objects.equals(defaultCurrency, product.defaultCurrency) && Objects.equals(productCategory, product.productCategory) && Objects.equals(supplier, product.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultPrice, defaultCurrency, productCategory, supplier);
    }
=======
>>>>>>> cdc4cebda8b11f8861073ec00b2d5533038d7def
}
