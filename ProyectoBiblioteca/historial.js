const contenedor = document.getElementById("historial");
const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];

if (historial.length === 0) {
  contenedor.innerHTML = "<p>No hay compras registradas.</p>";
} else {
  historial.forEach((compra, index) => {

    let librosHTML = "";
    compra.libros.forEach(id => {
      const libro = libros.find(l => l.id === id);
      if (libro) {
        librosHTML += `<li>${libro.titulo} - S/ ${libro.precio}</li>`;
      }
    });

    const card = document.createElement("div");
    card.className = "libro";

    card.innerHTML = `
      <h3>🧾 Compra #${index + 1}</h3>
      <p><strong>Fecha:</strong> ${compra.fecha}</p>
      <ul>${librosHTML}</ul>
      <p><strong>Total:</strong> S/ ${compra.total}.00</p>
      <button onclick="imprimirBoleta(${index})">🖨 Imprimir</button>
    `;

    contenedor.appendChild(card);
  });
}

function imprimirBoleta(i) {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF();

  const compra = historial[i];
  let y = 20;

  doc.setFontSize(16);
  doc.text("📚 Biblioteca Central de Lima", 20, y);
  y += 10;

  doc.setFontSize(12);
  doc.text(`Fecha: ${compra.fecha}`, 20, y);
  y += 10;

  doc.text("Libros alquilados:", 20, y);
  y += 8;

  compra.libros.forEach(id => {
    const libro = libros.find(l => l.id === id);
    if (libro) {
      doc.text(`• ${libro.titulo} - S/ ${libro.precio}`, 25, y);
      y += 8;
    }
  });

  y += 10;
  doc.setFontSize(14);
  doc.text(`TOTAL: S/ ${compra.total}.00`, 20, y);

  doc.save(`boleta_${i + 1}.pdf`);p
}
