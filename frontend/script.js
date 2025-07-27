document.getElementById("messageForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const category = document.getElementById("category").value;
  const content = document.getElementById("content").value.trim();

  try {
    const res = await fetch("http://localhost:8080/api/messages", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ category, content }),
    });

    if (!res.ok) throw new Error("Error");

    const text = await res.text();
    alert(text); // exibe "Message sent" ou erro vindo do backend

    document.getElementById("messageForm").reset();

    // Atualiza o log chamando GET /api/logs
    const logsRes = await fetch("http://localhost:8080/api/logs");
    if (!logsRes.ok) throw new Error("Error");
    const logs = await logsRes.json();
    console.log("📋 Logs recebidos do backend:", logs);

    // Filtra logs pela categoria selecionada antes de atualizar a tabela
    const selectedCategory = document.getElementById("category").value;
    const filteredLogs = logs.filter(log => log.category === selectedCategory);

    updateLogTable(filteredLogs);

  } catch (err) {
    alert("Erro: " + err.message);
  }
});

// Função para atualizar tabela de logs
function updateLogTable(result) {
  const tbody = document.querySelector("#logTable tbody");
  tbody.innerHTML = "";

  console.log("🔍 Dados brutos recebidos:", result);

  result.forEach(entry => {
    console.log("➕ Linha adicionada:", entry);

    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${entry.userName ?? '-'}</td>
      <td>${entry.channel ?? '-'}</td>
      <td>${entry.category ?? '-'}</td>
      <td>${entry.status ?? '-'}</td>
      <td>${entry.timestamp ? new Date(entry.timestamp).toLocaleString() : '-'}</td>
      <td>${entry.message ?? '-'}</td>
    `;
    tbody.appendChild(tr);
  });
}

// Atualiza tabela de logs também quando mudar a categoria
document.getElementById("category").addEventListener("change", async () => {
  try {
    const logsRes = await fetch("http://localhost:8080/api/logs");
    if (!logsRes.ok) throw new Error("Error");
    const logs = await logsRes.json();

    const selectedCategory = document.getElementById("category").value;
    const filteredLogs = logs.filter(log => log.category === selectedCategory);

    updateLogTable(filteredLogs);
  } catch (err) {
    alert("Erro ao carregar logs: " + err.message);
  }
});


document.getElementById("userForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const name = document.getElementById("name").value.trim();
  const email = document.getElementById("email").value.trim();
  const phone = document.getElementById("phone").value.trim();

  const subscriptions = Array.from(document.querySelectorAll(".subscription:checked")).map(cb => cb.value);
  const channels = Array.from(document.querySelectorAll(".channel:checked")).map(cb => cb.value);

  if (!name || !email || !phone || subscriptions.length === 0 || channels.length === 0) {
    alert("Preencha todos os campos");
    return;
  }

  const payload = { name, email, phone, subscriptions, channels };

  try {
    const res = await fetch("http://localhost:8080/api/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    if (!res.ok) throw new Error("Error");
    alert("Usuário cadastrado!");
    document.getElementById("userForm").reset();
    loadUsers();
  } catch (err) {
    alert("Erro: " + err.message);
  }
});

async function loadUsers() {
  try {
    const res = await fetch("http://localhost:8080/api/users");

    if (!res.ok) throw new Error(`HTTP ${res.status}`);



    //const users = JSON.parse(text);
    const users = await res.json();

    const tbody = document.querySelector("#userTable tbody");
    tbody.innerHTML = "";

    users.forEach(user => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td>${user.subscriptions?.join(", ") || ""}</td>
        <td>${user.channels?.join(", ") || ""}</td>
      `;
      tbody.appendChild(tr);
    });
  } catch (err) {
    console.error("Error:", err);
  }
}

// Inicialização da página
window.addEventListener("load", async () => {
  await loadUsers();

  try {
    const logsRes = await fetch("http://localhost:8080/api/logs");
    if (!logsRes.ok) throw new Error("Error");
    const logs = await logsRes.json();

    const selectedCategory = document.getElementById("category").value;
    const filteredLogs = logs.filter(log => log.category === selectedCategory);

    updateLogTable(filteredLogs);
  } catch (err) {
    alert("Erro ao carregar logs: " + err.message);
  }
});
