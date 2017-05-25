DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS shoppingcart;
DROP TABLE IF EXISTS users;

CREATE TABLE suppliers
(
  supplier_id           SERIAL PRIMARY KEY,
  supplier_name         VARCHAR(200),
  supplier_description  VARCHAR(300)
);


CREATE TABLE categories
(
  category_id           SERIAL PRIMARY KEY,
  category_name         VARCHAR(200),
  department            VARCHAR (200),
  category_description  VARCHAR(300)
);

CREATE TABLE products
(
  product_id            SERIAL PRIMARY KEY,
  product_name          VARCHAR(200),
  default_price         FLOAT,
  currency_string       VARCHAR(200),
  product_description   VARCHAR (300),
  cat_id                INT REFERENCES categories (category_id),
  supp_id               INT REFERENCES suppliers (supplier_id)
);

CREATE TABLE shoppingcart
(
  cart_item_id          SERIAL PRIMARY KEY,
  prod_id               INT REFERENCES products (product_id),
  quantity              INT,
  subtotal_price        FLOAT,
  product_price         FLOAT
);

CREATE TABLE users
(
  user_id               SERIAL PRIMARY KEY,
  user_name             VARCHAR(50),
  phone_number          VARCHAR(50),
  billing_address       VARCHAR(100),
  shipping_address      VARCHAR(100),
  email_address         VARCHAR(100)
);
