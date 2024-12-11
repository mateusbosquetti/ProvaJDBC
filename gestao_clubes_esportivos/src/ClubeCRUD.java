import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubeCRUD {

    public static void postClube(Clube clube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Clube (nome, idTreinador, dataFundacao) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clube.getNome());
            ps.setInt(2, clube.getTreinador().getIdTreinador());
            ps.setString(3, clube.getDataFundacao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                clube.setIdClube(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Clube getClubeById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Clube WHERE idClube = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Clube(
                        rs.getInt(1),
                        rs.getString(2),
                        TreinadorCRUD.getTreinadorById(rs.getInt(3)),
                        rs.getString(4)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Clube não encontrado");
    }

    public static List<Clube> getClube() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Clube");
            ResultSet rs = ps.executeQuery();
            List<Clube> clubes = new ArrayList<>();
            while (rs.next()) {
                clubes.add(new Clube(
                        rs.getInt(1),
                        rs.getString(2),
                        TreinadorCRUD.getTreinadorById(rs.getInt(3)),
                        rs.getString(4)
                ));
            }
            return clubes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Clube> getClubeByTreinador(int idTreinador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Clube WHERE idTreinador = ?");
            ps.setInt(1, idTreinador);
            ResultSet rs = ps.executeQuery();
            List<Clube> clubesEncontrados = new ArrayList<>();
            while (rs.next()) {
                clubesEncontrados.add(new Clube(
                        rs.getInt(1),
                        rs.getString(2),
                        TreinadorCRUD.getTreinadorById(rs.getInt(3)),
                        rs.getString(4)
                ));
            }
            return clubesEncontrados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteClube(int id) {
        getClubeById(id);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Clube WHERE idClube = ?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            ClubeLigaCRUD.deleteAllByClube(id);
            for (Jogador jogador : JogadorCRUD.getJogadorByClube(id)) {
                JogadorCRUD.deleteJogador(jogador.getIdJogador());
            }
            deleteClube(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putClube(Clube clube) {
        getClubeById(clube.getIdClube());
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE Clube SET nome = ?, idTreinador = ?, dataFundacao = ? WHERE idClube = ?");
            ps.setString(1, clube.getNome());
            ps.setInt(2, clube.getTreinador().getIdTreinador());
            ps.setString(3, clube.getDataFundacao());
            ps.setInt(4, clube.getIdClube());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
