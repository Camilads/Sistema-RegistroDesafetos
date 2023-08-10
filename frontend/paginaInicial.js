const desafetosList = document.getElementById('desafetos-list');
const cadastrarDesafetoBtn = document.getElementById('cadastrar-desafeto');

cadastrarDesafetoBtn.addEventListener('click', () => {
    window.location.href = 'cadastroDesafeto.html';
});

function carregarDesafetos() {
    fetch('http://localhost:8080/desafeto')
        .then(response => response.json())
        .then(data => {
            exibirDesafetos(data);
        })
        .catch(error => console.error('Erro ao buscar desafetos:', error));
}

function exibirDesafetos(desafetos) {
    desafetosList.innerHTML = '';

    desafetos.forEach(desafeto => {
        const listItem = document.createElement('tr');
        listItem.innerHTML = `<td><a href="ofensas.html?id=${desafeto.id}">${desafeto.nome}</a></td>`;
        desafetosList.appendChild(listItem);
    });
}

carregarDesafetos();
