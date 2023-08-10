package ifpe.edu.sysguardeinocorazon.model.repository;

import ifpe.edu.sysguardeinocorazon.model.entities.Desafeto;
import ifpe.edu.sysguardeinocorazon.model.entities.Ofensa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfensaRepository implements GenericRepository<Ofensa, Integer> {

    protected OfensaRepository(){}

    @Override
    public void create(Ofensa ofensa) throws SQLException {

        String sql = "insert into ofensa (desafeto_id, dataHora, descricao) values(?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, ofensa.getDesafeto().getId());
        pstm.setTimestamp(2, Timestamp.valueOf(ofensa.getDataHora().toLocalDateTime()) );
        pstm.setString(3, ofensa.getDescricao());

        pstm.execute();
    }

    @Override
    public void update(Ofensa ofensa) throws SQLException {
        String sql = "update ofensa set descricao=? where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, ofensa.getDescricao());
        pstm.setInt(2, ofensa.getId());

        pstm.execute();
    }

    @Override
    public Ofensa read(Integer key) throws SQLException {

        String sql= "select * from ofensa where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setInt(1, key);

        ResultSet result = pstm.executeQuery();
        Ofensa ofensaRead = null;

        if (result.next()){
            ofensaRead = new Ofensa();
            ofensaRead.setId(key);

            DesafetoRepository desafetoRepository = new DesafetoRepository();
            Desafeto desafeto = desafetoRepository.read(result.getInt(("desafeto_id")));
            ofensaRead.setDesafeto(desafeto);

            ofensaRead.setDataHora(result.getTimestamp("dataHora"));
            ofensaRead.setDescricao(result.getString("descricao"));
        }
        return ofensaRead;
    }

    @Override
    public void delete(Integer key) throws SQLException {

        String sql = "delete from ofensa where id=?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, key);

        pstm.execute();
    }

    @Override
    public List<Ofensa> readAll() throws SQLException {

        String sql = "select * from ofensa";

        List<Ofensa> ofensas = new ArrayList<>();

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        ResultSet result = pstm.executeQuery();

        while (result.next()){
            Ofensa ofensa = new Ofensa();
            ofensa.setId(result.getInt("id"));

            DesafetoRepository desafetoRepository = new DesafetoRepository();
            Desafeto desafeto = desafetoRepository.read(result.getInt(("desafeto_id")));
            ofensa.setDesafeto(desafeto);

            ofensa.setDataHora(result.getTimestamp("dataHora"));
            ofensa.setDescricao(result.getString("descricao"));

            ofensas.add(ofensa);
        }
        return ofensas;
    }

    @Override
    public List<Ofensa> readAllFilter(Integer key) throws SQLException{
        String sql = "SELECT * FROM ofensa WHERE desafeto_id = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, key);

        ResultSet result = pstm.executeQuery();

        List<Ofensa> ofensas = new ArrayList<>();

        while (result.next()) {
            Ofensa ofensa = new Ofensa();
            ofensa.setId(result.getInt("id"));

            DesafetoRepository desafetoRepository = new DesafetoRepository();
            Desafeto desafeto = desafetoRepository.read(key);
            ofensa.setDesafeto(desafeto);

            ofensa.setDataHora(result.getTimestamp("dataHora"));
            ofensa.setDescricao(result.getString("descricao"));
            ofensas.add(ofensa);
        }

        return ofensas;
    }


}
