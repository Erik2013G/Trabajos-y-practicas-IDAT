<?php
$host = 'localhost';
$user = 'root';
$pass = '109Inuyash@'; // Cambia si tu contraseña es diferente
$port = 5000;
$dbname = 'productos_db';

$conn = new mysqli($host, $user, $pass, $dbname, $port);

if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}
?>