document.addEventListener("DOMContentLoaded", () => {

  const grid = document.querySelector(".catalogo-grid");
  const filtroPrecio = document.getElementById("filtroPrecio");
  const totalHTML = document.getElementById("total");
  const btnVaciar = document.getElementById("vaciarAlquiler");

  let alquileres = JSON.parse(localStorage.getItem("alquileres")) || [];

  function guardar() {
    localStorage.setItem("alquileres", JSON.stringify(alquileres));
  }

  function calcularTotal() {
    let total = 0;
    alquileres.forEach(id => {
      const libro = libros.find(l => l.id === id);
      if (libro) total += libro.precio;
    });
    totalHTML.textContent = `Total a pagar: S/ ${total}.00`;
  }

  function render(lista) {
    grid.innerHTML = "";

    lista.forEach(libro => {
      const alquilado = alquileres.includes(libro.id);

      const card = document.createElement("article");
      card.className = "libro";

      card.innerHTML = `
        <h3>${libro.titulo}</h3>
        <p><strong>Autor:</strong> ${libro.autor}</p>
        <p><strong>Precio:</strong> S/ ${libro.precio}</p>
        <p><strong>Estado:</strong> 
          ${libro.disponible ? "✅ Disponible" : "❌ Sin stock"}
        </p>
        <button 
          ${(!libro.disponible || alquilado) ? "disabled" : ""}
        >
          ${alquilado ? "Alquilado" : "Alquilar"}
        </button>
      `;

      const boton = card.querySelector("button");

      boton.addEventListener("click", () => {
        alquileres.push(libro.id);
        libro.disponible = false;
        guardar();
        calcularTotal();
        render(filtrarPorPrecio());
      });

      grid.appendChild(card);
    });

    calcularTotal();
  }

  function filtrarPorPrecio() {
    const max = Number(filtroPrecio.value);
    if (max === 0) return libros;
    return libros.filter(libro => libro.precio <= max);
  }

  filtroPrecio.addEventListener("change", () => {
    render(filtrarPorPrecio());
  });

  btnVaciar.addEventListener("click", () => {
    alquileres = [];
    libros.forEach(libro => libro.disponible = true);
    guardar();
    render(filtrarPorPrecio());
  });
  const btnFinalizar = document.getElementById("finalizarCompra");
  btnFinalizar.addEventListener("click", () => {

  if (alquileres.length === 0) {
    alert("❌ No has seleccionado ningún libro.");
    return;
  }

  let resumen = "📚 Libros alquilados:\n\n";
  let total = 0;

  alquileres.forEach(id => {
    const libro = libros.find(l => l.id === id);
    if (libro) {
      resumen += `- ${libro.titulo} (S/ ${libro.precio})\n`;
      total += libro.precio;
    }
  });

  resumen += `\n💰 Total a pagar: S/ ${total}.00\n\n¿Confirmar compra?`;

  const confirmar = confirm(resumen);

  if (!confirmar) return;

  // Guardar historial
  const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];
  historial.push({
    fecha: new Date().toLocaleString(),
    total,
    libros: [...alquileres]
  });
  localStorage.setItem("historialCompras", JSON.stringify(historial));

  // Limpiar alquileres
  alquileres = [];
  localStorage.setItem("alquileres", JSON.stringify(alquileres));

  alert("✅ Compra realizada con éxito");

  render(filtrarPorPrecio());
});


  render(libros);
});
