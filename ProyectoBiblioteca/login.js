function login() {
  const user = document.getElementById("usuario").value;
  const pass = document.getElementById("password").value;

  if (user === "admin" && pass === "123") {
    localStorage.setItem("usuarioLogueado", user);
    window.location.href = "admin.html";
    return;
  }

  if (user && pass) {
    localStorage.setItem("usuarioLogueado", user);
    window.location.href = "index.html";
  } else {
    document.getElementById("error").textContent = "Datos incorrectos";
  }
}
