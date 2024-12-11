
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorCRUD {

    public static void postTreinador(Treinador treinador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Treinador (nome, experiencia) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, treinador.getNome());
            ps.setInt(2, treinador.getExperiencia());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                treinador.setIdTreinador(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Treinador getTreinadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Treinador WHERE idTreinador = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Treinador(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(1)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Treinador não encontrado");
    }

    public static List<Treinador> getTreinador() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Treinador");
            ResultSet rs = ps.executeQuery();
            List<Treinador> treinadoresEncontrados = new ArrayList<>();
            while (rs.next()) {
                treinadoresEncontrados.add(new Treinador(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(1)
                ));
            }
            return treinadoresEncontrados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteTreinador(int id) {
        getTreinadorById(id);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Treinador WHERE idTreinador = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            for (Clube clube : ClubeCRUD.getClubeByTreinador(id)) {
                ClubeCRUD.deleteClube(clube.getIdClube());
            }
            deleteTreinador(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putTreinador(Treinador treinador) {
        getTreinadorById(treinador.getIdTreinador());
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE Treinador SET nome = ?, experiencia = ? WHERE idTreinador = ?");
            ps.setString(1, treinador.getNome());
            ps.setInt(2, treinador.getExperiencia());
            ps.setInt(3, treinador.getIdTreinador());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
