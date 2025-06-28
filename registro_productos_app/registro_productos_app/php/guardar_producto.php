<?php
<?php
header('Content-Type: text/html; charset=utf-8');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nombre = $_POST['nombre'] ?? '';
    $precio = $_POST['precio'] ?? '';
    $categoria = $_POST['categoria'] ?? '';

    // Conexión a la base de datos (ajusta los datos)
    $conn = new mysqli("localhost","root","109Inuyash@","productos_db",5000);
    if ($conn->connect_error) {
        die("Error de conexión: " . $conn->connect_error);
    }

    $stmt = $conn->prepare("INSERT INTO productos (nombre, precio, categoria) VALUES (?, ?, ?)");
    if ($stmt) {
        $stmt->bind_param("sds", $nombre, $precio, $categoria);
        if ($stmt->execute()) {
            echo "Producto registrado exitosamente.";
        } else {
            echo "Error al ejecutar la consulta: " . $stmt->error;
        }
        $stmt->close();
    } else {
        echo "Error en la preparación de la consulta: " . $conn->error;
    }
    $conn->close();
} else {
    echo "Acceso denegado. Solo se permite método POST.";
}
?>