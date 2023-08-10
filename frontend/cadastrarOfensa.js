document.addEventListener("DOMContentLoaded", () => {
    const descricaoInput = document.getElementById("descricao");
    const cadastrarOfensaForm = document.getElementById("cadastrar-ofensa-form");

    const urlParams = new URLSearchParams(window.location.search);
    const desafetoId = urlParams.get("desafeto_id");

    cadastrarOfensaForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const novaOfensa = {
            descricao: descricaoInput.value,
        };

        try {
            const response = await fetch(`http://localhost:8080/ofensa/${desafetoId}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(novaOfensa)
            });

            if (!response.ok) {
                throw new Error('Erro ao cadastrar ofensa');
            }

            console.log("Ofensa cadastrada com sucesso!");
            
            window.location.href = `ofensas.html?id=${desafetoId}`;
        } catch (error) {
            console.error("Erro ao cadastrar ofensa:", error);
        }
    });
});
