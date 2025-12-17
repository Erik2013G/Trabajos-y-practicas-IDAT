// ================= PROTECCIÓN =================
if (!localStorage.getItem("login")) {
  window.location.href = "login.html";
}

// ================= DOM =================
document.addEventListener("DOMContentLoaded", () => {
  const grid = document.querySelector(".catalogo-grid");
  const filtro = document.getElementById("filtroPrecio");
  const totalHTML = document.getElementById("total");
  const vaciarBtn = document.getElementById("vaciarAlquiler");

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
    totalHTML.textContent = total.toFixed(2);
  }

  // ==== Render inicial ====
  libros.forEach(libro => {
    const card = document.createElement("article");
    card.innerHTML = `
      <h3>${libro.titulo}</h3>
      <p>${libro.autor}</p>
      <p>S/ ${libro.precio}</p>
      <button></button>
    `;
    const btn = card.querySelector("button");

    const alquilado = alquileres.includes(libro.id);
    if (!libro.disponible || alquilado) {
      btn.textContent = "Alquilado";
      btn.disabled = true;
      libro.disponible = false;
    } else {
      btn.textContent = "Alquilar";
      btn.disabled = false;
    }

    btn.addEventListener("click", () => {
      alquileres.push(libro.id);
      libro.disponible = false;
      guardar();
      btn.textContent = "Alquilado";
      btn.disabled = true;
      calcularTotal();
    });

    card.dataset.precio = libro.precio;
    grid.appendChild(card);
  });

  // ==== Filtro por precio ====
  filtro.addEventListener("change", () => {
    const max = Number(filtro.value);
    grid.childNodes.forEach(card => {
      const precio = Number(card.dataset.precio);
      card.style.display = (max === 0 || precio <= max) ? "" : "none";
    });
  });

  // ==== Vaciar alquiler ====
  vaciarBtn.addEventListener("click", () => {
    alquileres = [];
    libros.forEach(l => l.disponible = true);
    guardar();

    grid.childNodes.forEach(card => {
      const btn = card.querySelector("button");
      btn.textContent = "Alquilar";
      btn.disabled = false;
    });

    calcularTotal();
  });

  // ==== Inicio ====
  calcularTotal();
});

// ==== Logout ====
function logout() {
  localStorage.removeItem("login");
  localStorage.removeItem("usuario");
  window.location.href = "login.html";
}
