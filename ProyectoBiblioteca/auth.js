// ===== Login =====
function login() {
  const usuario = document.getElementById("usuario").value.trim();
  const password = document.getElementById("password").value.trim();
  const error = document.getElementById("error");

  if (!localStorage.getItem("usuarios")) {
  localStorage.setItem("usuarios", JSON.stringify([
    { usuario: "admin", password: "1234" }
  ]));
}


  const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  const user = usuarios.find(u => u.usuario === usuario && u.password === password);

  if(user){
    localStorage.setItem("login", "true");
    localStorage.setItem("usuario", usuario);
    window.location.href = "index.html";
  } else {
    error.textContent = "Usuario o contraseña incorrectos";
  }
}

function irARegistro() {
  window.location.href = "register.html";
}
// Usuario por defecto si no hay usuarios guardados
if (!localStorage.getItem("usuarios")) {
  localStorage.setItem("usuarios", JSON.stringify([
    { usuario: "admin", password: "1234" }
  ]));
}

// ===== Registro =====
function registrar() {
  const usuario = document.getElementById("usuario").value.trim();
  const password = document.getElementById("password").value.trim();
  const error = document.getElementById("error");

  if(!usuario || !password){
    error.textContent = "Complete todos los campos";
    return;
  }

  let usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  if(usuarios.find(u => u.usuario === usuario)){
    error.textContent = "El usuario ya existe";
    return;
  }

  usuarios.push({usuario, password});
  localStorage.setItem("usuarios", JSON.stringify(usuarios));
  alert("Usuario registrado correctamente");
  window.location.href = "login.html";
}

