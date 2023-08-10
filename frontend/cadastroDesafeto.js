
document.addEventListener("DOMContentLoaded", function(){

    const urlApi = "http://localhost:8080/desafeto";

    async function cadastrarDesafeto(event){
        event.preventDefault();
        
        const nome = document.getElementById('nome').value;
        const amigos = document.getElementById('amigos').value;
        const escola = document.getElementById('escola').value;
        const descricao = document.getElementById('descricao').value;

        const novoDesafeto = {
            nome,
            amigos,
            escola,
            descricao,
        };

        try{
            const response = await fetch(urlApi, {
                method:"POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(novoDesafeto),
            });

            if(!response.ok){
                const errorMensage = await response.text();
                exibirMensagemErro(errorMessage);
                return;
            }

            exibirMensagemSucesso("Desafeto cadastrado com sucesso!");

            setTimeout(() => {
                 window.location.href = "paginaInicial.html"; 
            }, 2000);
            
        }catch(error){
            exibirMensagemErro("Erro ao cadastrar o desafeto.");
        }
    }

    function exibirMensagemSucesso(mensagem) {
        const mensagemDiv = document.getElementById("mensagem");
        mensagemDiv.textContent = mensagem;
        mensagemDiv.classList.remove("mensagem-erro");
        mensagemDiv.classList.add("mensagem-sucesso");
        mensagemDiv.style.color = "green"; 
    }

    function exibirMensagemErro(mensagem) {
        const mensagemDiv = document.getElementById("mensagem");
        mensagemDiv.textContent = mensagem;
        mensagemDiv.classList.remove("mensagem-sucesso");
        mensagemDiv.classList.add("mensagem-erro");
        mensagemDiv.style.color = "red"; 
    }

    const cadastroForm = document.getElementById('cadastro-form');
    cadastroForm.addEventListener("submit", cadastrarDesafeto);

});
