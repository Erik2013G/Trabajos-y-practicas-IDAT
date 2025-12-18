// =================== USUARIO POR DEFECTO ===================
if (!localStorage.getItem("usuarios")) {
  localStorage.setItem("usuarios", JSON.stringify([
    { usuario: "admin", password: "1234", nombre: "Administrador", rol: "admin" }
  ]));
}

// =================== LOGIN ===================
function login() {
  const usuario = document.getElementById("usuario").value.trim();
  const password = document.getElementById("password").value.trim();
  const error = document.getElementById("error");

  if(!usuario || !password){
    error.textContent = "Complete todos los campos";
    return;
  }

  const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  const user = usuarios.find(u => u.usuario === usuario && u.password === password);

  if(user){
    localStorage.setItem("login", "true");
    localStorage.setItem("usuario", usuario);
    localStorage.setItem("rol", user.rol || "lector"); // Guardar rol
    window.location.href = "index.html";
  } else {
    error.textContent = "Usuario o contraseña incorrectos";
  }
}

function irARegistro() {
  window.location.href = "register.html";
}

// =================== REGISTRO ===================
function registrar() {
  const usuario = document.getElementById("usuario").value.trim();
  const password = document.getElementById("password").value.trim();
  const nombre = document.getElementById("nombre").value.trim();
  const fechaNacimiento = document.getElementById("fechaNacimiento").value;
  const email = document.getElementById("email").value.trim();
  const telefono = document.getElementById("telefono").value.trim();
  const rol = document.getElementById("regRol").value;
  const mensaje = document.getElementById("mensaje");

  if(!usuario || !password || !nombre || !fechaNacimiento || !email || !telefono){
    mensaje.textContent = "Complete todos los campos";
    return;
  }

  let usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  if(usuarios.find(u => u.usuario === usuario)){
    mensaje.textContent = "El usuario ya existe";
    return;
  }

  // Guardar usuario completo
  usuarios.push({
    usuario,
    password,
    nombre,
    fechaNacimiento,
    email,
    telefono,
    rol
  });

  localStorage.setItem("usuarios", JSON.stringify(usuarios));
  alert("Usuario registrado correctamente");
  window.location.href = "login.html";
}
document.addEventListener("DOMContentLoaded", () => {
  // Obtener el usuario logueado
  const usuarioLog = localStorage.getItem("usuario"); 
  const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];

  if(usuarioLog){
    const usuarioData = usuarios.find(u => u.usuario === usuarioLog);
    if(usuarioData){
      document.getElementById("nombreUsuario").textContent = usuarioData.nombre;
      document.getElementById("emailUsuario").textContent = usuarioData.email;
      document.getElementById("fechaNacimientoUsuario").textContent = usuarioData.fechaNacimiento;
      document.getElementById("telefonoUsuario").textContent = usuarioData.telefono;
      document.getElementById("btnUser").textContent = `👤 ${usuarioData.usuario}`;
    }
  }
});


// =================== LOGOUT ===================
function logout() {
  localStorage.removeItem("login");
  localStorage.removeItem("usuario");
  localStorage.removeItem("rol");
  window.location.href = "login.html";
}
