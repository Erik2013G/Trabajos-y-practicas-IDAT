CREATE DATABASE CruzDelNorte;
USE CruzDelNorte;
-- Tabla Clientes
CREATE TABLE Clientes (
    ID_Cliente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Dirección VARCHAR(200),
    Teléfono VARCHAR(15),
    Email VARCHAR(100)
);

-- Tabla Productos
CREATE TABLE Productos (
    ID_Producto INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL
);

-- Tabla Empleados
CREATE TABLE Empleados (
    ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Cargo VARCHAR(50) NOT NULL
);

-- Tabla Pedidos
CREATE TABLE Pedidos (
    ID_Pedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Cliente INT,
    Fecha DATE NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);

-- Tabla Quejas
CREATE TABLE Quejas (
    ID_Queja INT AUTO_INCREMENT PRIMARY KEY,
    ID_Cliente INT,
    Descripción TEXT NOT NULL,
    Fecha DATE NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);
-- Insertar datos en la tabla Clientes
INSERT INTO Clientes (Nombre, Dirección, Teléfono, Email) VALUES
('Juan Pérez', 'Calle Falsa 123', '555-1234', 'juan.perez@example.com'),
('María Gómez', 'Avenida Siempre Viva 456', '555-5678', 'maria.gomez@example.com');

-- Insertar datos en la tabla Productos
INSERT INTO Productos (Nombre, Precio) VALUES
('Producto A', 19.99),
('Producto B', 29.99);

-- Insertar datos en la tabla Empleados
INSERT INTO Empleados (Nombre, Cargo) VALUES
('Carlos López', 'Vendedor'),
('Ana Martínez', 'Gerente');

-- Insertar datos en la tabla Pedidos
INSERT INTO Pedidos (ID_Cliente, Fecha, Total) VALUES
(1, '2023-10-01', 19.99),
(2, '2023-10-02', 29.99);

-- Insertar datos en la tabla Quejas
INSERT INTO Quejas (ID_Cliente, Descripción, Fecha, Estado) VALUES
(1, 'El producto llegó dañado.', '2023-10-01', 'Pendiente'),
(2, 'El pedido no llegó a tiempo.', '2023-10-02', 'Resuelta');