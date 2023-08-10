package ifpe.edu.sysguardeinocorazon.model.entities;

import java.sql.Timestamp;

public class Ofensa {

    private int id;
    private Timestamp dataHora;
    private String descricao;

    private Desafeto desafeto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp data) {
        this.dataHora = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Desafeto getDesafeto() {
        return desafeto;
    }

    public void setDesafeto(Desafeto desafeto) {
        this.desafeto = desafeto;
    }
}
