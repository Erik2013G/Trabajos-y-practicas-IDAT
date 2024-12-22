Create database ProyectoAPIServiceJAVA;
use ProyectoAPIServiceJAVA;
select*from pedidos;
CREATE TABLE Pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único del pedido',
    cliente VARCHAR(100) NOT NULL COMMENT 'Nombre del cliente que realiza el pedido',
    producto VARCHAR(100) NOT NULL COMMENT 'Nombre del producto solicitado',
    cantidad INT NOT NULL COMMENT 'Cantidad de productos solicitados',
    fecha_pedido DATEtime NOT NULL COMMENT 'Fecha en la que se realiza el pedido',
    estado ENUM('Pendiente', 'En Proceso', 'Entregado', 'Cancelado') NOT NULL DEFAULT 'Pendiente' COMMENT 'Estado actual del pedido',
    precio decimal(10,2)
) COMMENT = 'Tabla que almacena la información de los pedidos realizados.';

drop table pedidos;