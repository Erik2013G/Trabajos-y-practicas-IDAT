<?php
require 'php/conexion.php';

$nombre = "Producto prueba";
$precio = 19.99;
$categoria = "Electrónica";

$stmt = $conn->prepare("INSERT INTO productos (nombre, precio, categoria) VALUES (?, ?, ?)");
$stmt->bind_param("sds", $nombre, $precio, $categoria);

if ($stmt->execute()) {
    echo "✅ Producto insertado directamente.";
} else {
    echo "❌ Error al insertar: " . $stmt->error;
}

$stmt->close();
$conn->close();
?>