if (!localStorage.getItem("login")) {
  window.location.href = "login.html";
}

const contenedor = document.getElementById("historial");
const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];

if (historial.length === 0) {
  contenedor.innerHTML = "<p>No hay compras registradas.</p>";
} else {
  historial.forEach((compra, index) => {
    let librosHTML = "";

    compra.libros.forEach(libro => {
      librosHTML += `
        <li>
          ${libro.titulo} - ${libro.autor} - 
          <strong>S/ ${libro.precio}</strong>
        </li>
      `;
    });

    contenedor.innerHTML += `
      <div class="boleta">
        <h3>🧾 Boleta #${index + 1}</h3>
        <p><strong>Usuario:</strong> ${compra.usuario}</p>
        <p><strong>Nombre:</strong> ${compra.nombre}</p>
        <p><strong>Email:</strong> ${compra.email}</p>
        <p><strong>Fecha:</strong> ${compra.fecha}</p>

        <ul>${librosHTML}</ul>

        <p class="total">
          <strong>Total:</strong> S/ ${compra.total.toFixed(2)}
        </p>

        <button onclick="generarPDF(${index})">📄 Descargar PDF</button>
      </div>
    `;
  });
}

// ================= PDF POR BOLETA =================
function generarPDF(index) {
  const { jsPDF } = window.jspdf;
  const compra = historial[index];

  const doc = new jsPDF();
  let y = 10;

  doc.setFontSize(14);
  doc.text("🧾 BOLETA DE COMPRA", 10, y);
  y += 10;

  doc.setFontSize(11);
  doc.text(`Usuario: ${compra.usuario}`, 10, y); y += 7;
  doc.text(`Nombre: ${compra.nombre}`, 10, y); y += 7;
  doc.text(`Email: ${compra.email}`, 10, y); y += 7;
  doc.text(`Fecha: ${compra.fecha}`, 10, y); y += 10;

  doc.text("Libros alquilados:", 10, y);
  y += 7;

  compra.libros.forEach(libro => {
    doc.text(`• ${libro.titulo} - S/ ${libro.precio}`, 10, y);
    y += 6;
  });

  y += 5;
  doc.text(`TOTAL: S/ ${compra.total.toFixed(2)}`, 10, y);

  doc.save(`boleta_${compra.usuario}_${index + 1}.pdf`);
}
