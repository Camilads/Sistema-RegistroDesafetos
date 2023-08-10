document.addEventListener("DOMContentLoaded", () => {
    const desafetoInfo = document.getElementById("desafeto-info");
    const ofensasList = document.getElementById("ofensas-list");
    const cadastrarOfensa = document.getElementById("cadastrar-ofensa");

    const urlParams = new URLSearchParams(window.location.search);
    const desafetoId = urlParams.get("id");

    cadastrarOfensa.addEventListener('click', () => {
        window.location.href = `cadastrarOfensa.html?desafeto_id=${desafetoId}`;
    });
 

    function carregarDesafeto() {
        fetch(`http://localhost:8080/desafeto/${desafetoId}`)
            .then(response => response.json())
            .then(desafeto => {
                exibirDesafetoInfo(desafeto);
            })
            .catch(error => console.error('Erro ao buscar desafeto:', error));
    }

    function exibirDesafetoInfo(desafeto) {
        const desafetoInfoHTML = `
            <h2>${desafeto.nome}</h2>
            <p><strong>Amigos:</strong> ${desafeto.amigos}</p>
            <p><strong>Escola:</strong> ${desafeto.escola}</p>
            <p><strong>Descrição:</strong> ${desafeto.descricao}</p>
        `;
        desafetoInfo.innerHTML = desafetoInfoHTML;
    }

    function carregarOfensas() {
        fetch(`http://localhost:8080/ofensa/desafeto/${desafetoId}`)
            .then(response => response.json())
            .then(data => {
                exibirOfensas(data);
            })
            .catch(error => console.error('Erro ao buscar ofensas:', error));
    }

    function carregarOfensas() {
        fetch(`http://localhost:8080/ofensa/desafeto/${desafetoId}`)
            .then(response => response.json())
            .then(data => {
                exibirOfensas(data);
            })
            .catch(error => console.error('Erro ao buscar ofensas:', error));
    }

    function exibirOfensas(ofensas) {
        ofensasList.innerHTML = "";

        ofensas.forEach(ofensa => {
            const newRow = document.createElement("tr");
            newRow.innerHTML = `
                <td>${formatarData(ofensa.dataHora)}</td>
                <td>${formatarHora(ofensa.dataHora)}</td>
                <td>${ofensa.descricao}</td>
            `;
            ofensasList.appendChild(newRow);
        });
    }

    function formatarData(dataHora) {
        const data = new Date(dataHora);
        const dia = data.getDate().toString().padStart(2, '0');
        const mes = (data.getMonth() + 1).toString().padStart(2, '0');
        const ano = data.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }

    function formatarHora(dataHora) {
        const data = new Date(dataHora);
        const hora = data.getHours().toString().padStart(2, '0');
        const minutos = data.getMinutes().toString().padStart(2, '0');
        return `${hora}:${minutos}`;
    }

    carregarDesafeto();
    carregarOfensas();
});
