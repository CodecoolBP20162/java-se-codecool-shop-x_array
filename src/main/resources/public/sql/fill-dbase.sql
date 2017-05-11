INSERT INTO categories (category_id, category_name, department, category_description)
VALUES
  (1,'Tablet', 'Hardware',
   'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
  (2,'Laptop','Hardware',
   'A laptop, often called a notebook or notebook computer, is a small, portable personal computer with a clamshell form factor, an alphanumeric keyboard on the lower part of the clamshell and a thin LCD or LED computer screen on the upper portion, which is opened up to use the computer.'),
  (3, 'Phone', 'Hardware',
   'A mobile phone is a portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area.');

INSERT INTO suppliers(supplier_id, supplier_name,supplier_description)
VALUES
  (1,'Amazon','Digital content and services'),
  (2,'Lenovo','Computers'),
  (3,'Apple','not for lunch'),
  (4,'Dell','Like doll but with an e');

INSERT INTO products (product_name, default_price, currency_string, product_description, cat_id, supp_id)
VALUES
  ('Amazon Fire', 49.9, 'USD', 'Fantastic price. Large content ecosystem.', 1, 1),
  ('Lenovo IdeaPad Miix 700',479,'USD','Keyboard cover is included. Fanless Core m5 processor.',1,2),
  ('Amazon Fire HD 8',89,'USD','Amazon''s latest Fire HD 8. Great value for media consumption.',1,1),
  ('Dell Vostro',600,'USD','Dell Vostro is a line of computers from Dell.',2,4),
  ('Macbook Pro',1500,'USD','its more than a book trust me',2,3),
  ('Lenovo',800,'USD','yo wassup im out of ideas',2,2),
  ('Iphone 7',700, 'USD', 'imagine calling your girl with an apple',3,3),
  ('Phab 2 Pro', 500, 'USD', 'buy it to be phabolous',3,2),
  ('Amazon Fire Phone', 450, 'USD', 'amazon has its own electronic devices!!!',3,1);

INSERT INTO shoppingcart (cart_item_id, prod_id, quantity, subtotal_price, product_price)
VALUES
  (1,1,1,49.9,49.9);
