document.addEventListener("DOMContentLoaded", () => {
  const data = JSON.parse(localStorage.getItem("alquileres")) || {};
  const contenedor = document.getElementById("adminData");

  for (let usuario in data) {
    contenedor.innerHTML += `
      <h3>👤 ${usuario}</h3>
      <p>Libros alquilados: ${data[usuario].length}</p>
    `;
  }
});
