package ifpe.edu.sysguardeinocorazon.model.repository;

import ifpe.edu.sysguardeinocorazon.model.entities.Desafeto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DesafetoRepository implements GenericRepository<Desafeto, Integer> {

    protected DesafetoRepository(){}

    @Override
    public void create(Desafeto desafeto) throws SQLException {

        String sql = "insert into desafeto(nome, amigos, escola, descricao) value(?, ?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, desafeto.getNome());
        pstm.setString(2, desafeto.getAmigos());
        pstm.setString(3, desafeto.getEscola());
        pstm.setString(4, desafeto.getDescricao());

        pstm.execute();
    }

    @Override
    public void update(Desafeto desafeto) throws SQLException {

        String sql = "update desafeto set nome=?, amigos=?, escola=?, descricao=? where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, desafeto.getNome());
        pstm.setString(2, desafeto.getAmigos());
        pstm.setString(3, desafeto.getEscola());
        pstm.setString(4, desafeto.getDescricao());

        pstm.setInt(5, desafeto.getId());

        pstm.execute();
    }

    @Override
    public Desafeto read(Integer key) throws SQLException {

        String sql = "select * from desafeto where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, key);

        ResultSet result = pstm.executeQuery();
        Desafeto desafetoRead = null;

        if (result.next()){
            desafetoRead = new Desafeto();
            desafetoRead.setId(key);
            desafetoRead.setNome(result.getString("nome"));
            desafetoRead.setAmigos(result.getString("amigos"));
            desafetoRead.setEscola(result.getString("escola"));
            desafetoRead.setDescricao(result.getString("descricao"));
        }
        return desafetoRead;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        String sql = "delete from desafeto where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, key);

        pstm.execute();
    }

    @Override
    public List<Desafeto> readAll() throws SQLException {

        String sql = "select * from desafeto";

        List<Desafeto> desafetos = new ArrayList<>();

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        ResultSet result = pstm.executeQuery();

        Desafeto desafetoRead = null;

        while (result.next()){
            desafetoRead = new Desafeto();
            desafetoRead.setId(result.getInt("id"));
            desafetoRead.setNome(result.getString("nome"));
            desafetoRead.setAmigos(result.getString("amigos"));
            desafetoRead.setEscola(result.getString("escola"));
            desafetoRead.setDescricao(result.getString("descricao"));

            desafetos.add(desafetoRead);
        }
        return desafetos;
    }

    @Override
    public List<Desafeto> readAllFilter(Integer key) throws SQLException {
        return null;
    }
}
