document.getElementById("messageForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const category = document.getElementById("category").value;
    const content = document.getElementById("content").value.trim();

    if (!content) {
        alert("Mensagem não pode ser vazia");
        return;
    }

    try {
        const res = await fetch("http://localhost:8080/api/messages", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ category, content }),
        });

        if (!res.ok) {
            throw new Error("Erro ao enviar mensagem");
        }

        // Optionally notify the user
        alert("Mensagem enviada com sucesso");

        // Reset form and reload logs
        e.target.reset();
        loadLogs();

    } catch (error) {
        console.error("Erro no envio:", error);
        alert("Falha ao enviar a mensagem. Tente novamente.");
    }
});

async function loadLogs() {
    try {
        const res = await fetch("http://localhost:8080/api/logs");
        if (!res.ok) throw new Error("Erro ao carregar logs");

        const data = await res.json();
        const tbody = document.querySelector("#logTable tbody");

        const fragment = document.createDocumentFragment();

        data.forEach(log => {
            const row = document.createElement("tr");

            row.innerHTML = `   
                <td>${log.userName} (${log.userEmail})</td>
                <td>${log.channel}</td>
                <td>${log.category}</td>
                <td>${log.sucess ? "✓" : "✗"}</td>
                <td>${new Date(log.timestamp).toLocaleString()}</td>
            `;

            fragment.appendChild(row);
        });

        tbody.innerHTML = "";
        tbody.appendChild(fragment);

    } catch (error) {
        console.error("Erro ao carregar logs:", error);
        alert("Não foi possível carregar os logs.");
    }
}

window.onload = loadLogs;
