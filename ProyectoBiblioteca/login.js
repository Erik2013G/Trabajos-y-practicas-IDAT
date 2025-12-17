function login() {
  const user = document.getElementById("usuario").value;
  const pass = document.getElementById("password").value;

  if (user === "admin" && pass === "1234") {
    localStorage.setItem("login", "true");
    window.location.href = "index.html";
  } else {
    document.getElementById("error").textContent =
      "❌ Usuario o contraseña incorrectos";
  }
}
