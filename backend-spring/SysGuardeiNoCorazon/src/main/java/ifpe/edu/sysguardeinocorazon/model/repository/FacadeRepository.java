package ifpe.edu.sysguardeinocorazon.model.repository;

import ifpe.edu.sysguardeinocorazon.model.entities.Desafeto;
import ifpe.edu.sysguardeinocorazon.model.entities.Ofensa;

import java.sql.SQLException;
import java.util.List;

public class FacadeRepository {

    private static FacadeRepository instance = new FacadeRepository();

    private GenericRepository<Desafeto, Integer> desafetoRepository = null;
    private GenericRepository<Ofensa, Integer> ofensaRepository = null;

    public FacadeRepository() {
        this.desafetoRepository = new DesafetoRepository();
        this.ofensaRepository = new OfensaRepository();
    }


    public static FacadeRepository getCurrentInstance(){
        return instance;
    }

    public void createDesafeto(Desafeto desafeto) throws SQLException{
        this.desafetoRepository.create(desafeto);
    }

    public void createOfensa(Ofensa ofensa) throws SQLException{
        this.ofensaRepository.create(ofensa);
    }

    public void updateDesafeto(Desafeto desafeto) throws SQLException{
        this.desafetoRepository.update(desafeto);
    }

    public void updateOfensa(Ofensa ofensa) throws SQLException{
        this.ofensaRepository.update(ofensa);
    }

    public Desafeto readDesafeto(int id) throws SQLException{
        return this.desafetoRepository.read(id);
    }

    public Ofensa readOfensa(int id) throws SQLException{
        return this.ofensaRepository.read(id);
    }

    public List<Desafeto> readAllDesafeto() throws SQLException{
        return this.desafetoRepository.readAll();
    }
    public List<Ofensa> readAllOfensa() throws SQLException{
        return this.ofensaRepository.readAll();
    }

    public List<Ofensa> readAllOfensaByDesafetoId(int desafetoId) throws SQLException {
        return ofensaRepository.readAllFilter(desafetoId);
    }

}
