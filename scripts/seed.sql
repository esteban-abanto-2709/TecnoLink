INSERT IGNORE INTO users (id, active, email, first_name, last_name, password, phone, registered_on) VALUES
  ('demo-client', b'1', 'cliente@tecnolink.pe', 'Demo', 'Cliente', 'demo1234', '999111222', CURDATE()),
  ('techperu',    b'1', 'ventas@techperu.pe',   'Tech', 'Peru',    'demo1234', '999333444', CURDATE());

INSERT IGNORE INTO clients (id, dni, address, region) VALUES
  ('demo-client', '12345678', 'Av. Arequipa 123', 'Lima');

INSERT IGNORE INTO suppliers (id, name, ruc, status, reputation, description, district, region, since_year) VALUES
  ('techperu', 'TechPeru SAC', '20123456789', 'VERIFIED', 4.5, 'Distribuidor de laptops', 'Miraflores', 'Lima', 2015);

INSERT IGNORE INTO categories (id, active, description, kind, name) VALUES
  ('laptops', b'1', 'Laptops y notebooks', 'PRODUCT', 'Laptops');

INSERT IGNORE INTO listings (id, category_id, description, name, price, published_on, status, supplier_id) VALUES
  ('laptop-lenovo-ideapad', 'laptops', 'Ryzen 5, 16 GB RAM, 512 GB SSD', 'Lenovo IdeaPad 5', 2899.90, CURDATE(), 'ACTIVE', 'techperu'),
  ('laptop-hp-victus',      'laptops', 'Core i5, 8 GB RAM, RTX 3050',    'HP Victus 15',     3499.00, CURDATE(), 'ACTIVE', 'techperu');

INSERT IGNORE INTO products (id, brand, model, stock) VALUES
  ('laptop-lenovo-ideapad', 'Lenovo', 'IdeaPad 5', 10),
  ('laptop-hp-victus',      'HP',     'Victus 15', 5);

INSERT IGNORE INTO specifications (product_id, name, spec_value, unit) VALUES
  ('laptop-lenovo-ideapad', 'Procesador', 'Ryzen 5', NULL),
  ('laptop-lenovo-ideapad', 'RAM', '16', 'GB'),
  ('laptop-lenovo-ideapad', 'Almacenamiento', '512', 'GB'),
  ('laptop-hp-victus', 'Procesador', 'Core i5', NULL),
  ('laptop-hp-victus', 'RAM', '8', 'GB'),
  ('laptop-hp-victus', 'Tarjeta grafica', 'RTX 3050', NULL);
