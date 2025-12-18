// =================== VALIDAR LOGIN Y ROL ===================
if (!localStorage.getItem("login") || localStorage.getItem("rol") !== "admin") {
  window.location.href = "login.html";
}

// =================== DOM ===================
document.addEventListener("DOMContentLoaded", () => {

  const contenedor = document.getElementById("adminData");
  const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];

  if (historial.length === 0) {
    contenedor.innerHTML = "<p>No hay compras registradas.</p>";
    return;
  }

  // Agrupar compras por usuario
  const resumen = {};

  historial.forEach(compra => {
    if (!resumen[compra.usuario]) {
      resumen[compra.usuario] = {
        totalCompras: 0,
        totalLibros: 0,
        totalGastado: 0
      };
    }

    resumen[compra.usuario].totalCompras++;
    resumen[compra.usuario].totalLibros += compra.libros.length;
    resumen[compra.usuario].totalGastado += compra.total;
  });

  // Render
  let html = "<h2>📊 Resumen de Usuarios</h2>";

  for (let usuario in resumen) {
    html += `
      <div class="admin-card">
        <h3>👤 ${usuario}</h3>
        <p>🧾 Compras realizadas: ${resumen[usuario].totalCompras}</p>
        <p>📚 Libros alquilados: ${resumen[usuario].totalLibros}</p>
        <p>💰 Total gastado: S/ ${resumen[usuario].totalGastado.toFixed(2)}</p>
      </div>
    `;
  }

  contenedor.innerHTML = html;
});
