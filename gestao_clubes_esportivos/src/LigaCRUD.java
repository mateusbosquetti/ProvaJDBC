import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigaCRUD {
    public static void postLiga(Liga liga) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Liga (nome, anoFundacao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, liga.getNome());
            ps.setInt(2, liga.getAnoFundacao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                liga.setIdLiga(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Liga getLigaById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Liga WHERE idLiga = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Liga(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(1)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Liga não encontrada");
    }

    public static List<Liga> getLigas() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Liga");
            ResultSet rs = ps.executeQuery();
            //Apagar
            List<Liga> ligasEncontradas = new ArrayList<>();
            while (rs.next()) {
                ligasEncontradas.add(new Liga(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(1)
                ));
            }
            return ligasEncontradas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteLiga(int id) {
        getLigaById(id);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Liga WHERE idLiga = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            ClubeLigaCRUD.deleteAllByLiga(id);
            deleteLiga(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putLiga(Liga liga) {
        getLigaById(liga.getIdLiga());
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE Liga SET nome = ?, anoFundacao = ? WHERE idLiga = ?");
            ps.setString(1, liga.getNome());
            ps.setInt(2, liga.getAnoFundacao());
            ps.setInt(3, liga.getIdLiga());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
