// =================== VALIDAR LOGIN ===================
if (!localStorage.getItem("login")) {
  window.location.href = "login.html";
}

// =================== DOM ===================
document.addEventListener("DOMContentLoaded", () => {

  const grid = document.getElementById("catalogoGrid");
  const filtro = document.getElementById("filtroPrecio");
  const totalHTML = document.getElementById("total");
  const vaciarBtn = document.getElementById("vaciarAlquiler");
  const finalizarBtn = document.getElementById("finalizarCompra");
  const pdfBtn = document.getElementById("generarPDF");
  const buscarInput = document.getElementById("buscarLibro");
  const categoriasMenu = document.getElementById("categoriasMenu");

  let alquileres = JSON.parse(localStorage.getItem("alquileres")) || [];
  let libros = JSON.parse(localStorage.getItem("libros")) || dataLibros();

  // =================== USUARIO ===================
  const usuarioLog = localStorage.getItem("usuario");
  const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  const usuarioData = usuarios.find(u => u.usuario === usuarioLog);

  if (usuarioData) {
    document.getElementById("nombreUsuario").textContent = usuarioData.nombre;
    document.getElementById("emailUsuario").textContent = usuarioData.email;
    document.getElementById("fechaNacimientoUsuario").textContent = usuarioData.fechaNacimiento;
    document.getElementById("telefonoUsuario").textContent = usuarioData.telefono;
    document.getElementById("btnUser").textContent = `👤 ${usuarioData.usuario}`;
  }

  // =================== FUNCIONES ===================
  function guardar() {
    localStorage.setItem("alquileres", JSON.stringify(alquileres));
    localStorage.setItem("libros", JSON.stringify(libros));
  }

  function calcularTotal() {
    let total = 0;
    alquileres.forEach(id => {
      const libro = libros.find(l => l.id === id);
      if (libro) total += libro.precio;
    });
    totalHTML.textContent = total.toFixed(2);
  }

  function render(lista) {
    grid.innerHTML = "";
    lista.forEach(libro => {
      const alquilado = alquileres.includes(libro.id);

      const card = document.createElement("div");
      card.classList.add("catalogo-card");
      card.dataset.precio = libro.precio;

      card.innerHTML = `
        <h3>${libro.titulo}</h3>
        <p>${libro.autor}</p>
        <p>Categoría: ${libro.categoria}</p>
        <p>S/ ${libro.precio}</p>
        <button ${(!libro.disponible || alquilado) ? "disabled" : ""}>
          ${alquilado ? "Alquilado" : "Alquilar"}
        </button>
      `;

      const btn = card.querySelector("button");
      btn.onclick = () => {
        if (alquilado) return;
        alquileres.push(libro.id);
        libro.disponible = false;
        guardar();
        btn.textContent = "Alquilado";
        btn.disabled = true;
        calcularTotal();
      };

      grid.appendChild(card);
    });
    calcularTotal();
  }

  // =================== FILTROS ===================
  filtro.addEventListener("change", () => {
    const max = Number(filtro.value);
    render(libros.filter(l => max === 0 || l.precio <= max));
  });

  buscarInput.addEventListener("input", () => {
    const valor = buscarInput.value.toLowerCase();
    render(libros.filter(l =>
      l.titulo.toLowerCase().includes(valor) ||
      l.autor.toLowerCase().includes(valor)
    ));
  });

  // =================== CATEGORÍAS ===================
  categoriasMenu.innerHTML = "";

  const liTodos = document.createElement("li");
  liTodos.innerHTML = `<a href="#">Todos</a>`;
  liTodos.onclick = e => { e.preventDefault(); render(libros); };
  categoriasMenu.appendChild(liTodos);

  const categoriasSet = new Set(libros.map(l => l.categoria));
  categoriasSet.forEach(cat => {
    const li = document.createElement("li");
    li.innerHTML = `<a href="#">${cat}</a>`;
    li.onclick = e => {
      e.preventDefault();
      render(libros.filter(l => l.categoria === cat));
    };
    categoriasMenu.appendChild(li);
  });

  // =================== BOTONES ===================
  vaciarBtn.onclick = () => {
    alquileres = [];
    libros.forEach(l => l.disponible = true);
    guardar();
    render(libros);
  };

  finalizarBtn.onclick = () => {
    if (alquileres.length === 0) {
      alert("No hay libros alquilados");
      return;
    }

    const compra = {
      usuario: usuarioData.usuario,
      nombre: usuarioData.nombre,
      email: usuarioData.email,
      fecha: new Date().toLocaleString(),
      libros: alquileres.map(id => libros.find(l => l.id === id)),
      total: alquileres.reduce(
        (acc, id) => acc + libros.find(l => l.id === id).precio,
        0
      )
    };

    const historial = JSON.parse(localStorage.getItem("historialCompras")) || [];
    historial.push(compra);
    localStorage.setItem("historialCompras", JSON.stringify(historial));

    alert("✅ Compra guardada correctamente");

    alquileres = [];
    libros.forEach(l => l.disponible = true);
    guardar();
    render(libros);
  };

  // =================== PDF ===================
  pdfBtn.onclick = () => {
    if (alquileres.length === 0) {
      alert("No hay historial de compra");
      return;
    }

    const doc = new jsPDF();
    let y = 10;
    doc.setFontSize(14);

    doc.text(`Usuario: ${usuarioData.usuario}`, 10, y); y += 8;
    doc.text(`Nombre: ${usuarioData.nombre}`, 10, y); y += 8;
    doc.text(`Email: ${usuarioData.email}`, 10, y); y += 10;

    doc.text("📄 Historial de Compra", 10, y); y += 8;

    let total = 0;
    alquileres.forEach(id => {
      const libro = libros.find(l => l.id === id);
      if (libro) {
        doc.text(`• ${libro.titulo} - S/ ${libro.precio}`, 10, y);
        y += 7;
        total += libro.precio;
      }
    });

    doc.text(`Total: S/ ${total.toFixed(2)}`, 10, y);
    doc.save("historial_compra.pdf");
  };

  render(libros);
});

// =================== LOGOUT ===================
function logout() {
  localStorage.removeItem("login");
  localStorage.removeItem("usuario");
  localStorage.removeItem("rol");
  window.location.href = "login.html";
}

// =================== DATA ===================
function dataLibros() {
  const libros = [];
  for (let i = 1; i <= 100; i++) {
    libros.push({
      id: i,
      titulo: `Libro ${i}`,
      autor: `Autor ${i}`,
      categoria: `Categoria ${Math.ceil(i / 10)}`,
      precio: Math.floor(Math.random() * 100) + 10,
      disponible: true
    });
  }
  return libros;
}
