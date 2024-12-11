import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorCRUD {

    public static void postJogador(Jogador jogador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Jogador (nome, idade, idClube, posicao) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, jogador.getNome());
            ps.setInt(2, jogador.getIdade());
            ps.setInt(3, jogador.getClube().getIdClube());
            ps.setString(4, jogador.getPosicao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                jogador.setIdJogador(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Jogador getJogadorById(int id) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Jogador WHERE idJogador = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Jogador(
                        ClubeCRUD.getClubeById(rs.getInt(4)),
                        rs.getInt(3),
                        rs.getString(2),
                        rs.getInt(1),
                        rs.getString(5)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Jogador não encontrado");
    }

    public static List<Jogador> getJogadorByClube(int idClube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Jogador WHERE idClube = ?");
            ps.setInt(1, idClube);
            ResultSet rs = ps.executeQuery();
            List<Jogador> jogadorsEncontrados = new ArrayList<>();
            while (rs.next()) {
                jogadorsEncontrados.add(new Jogador(
                        ClubeCRUD.getClubeById(rs.getInt(4)),
                        rs.getInt(3),
                        rs.getString(2),
                        rs.getInt(1),
                        rs.getString(5)
                ));
            }
            return jogadorsEncontrados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Jogador> getJogador() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Jogador ");
            ResultSet rs = ps.executeQuery();
            List<Jogador> jogadorsEncontrados = new ArrayList<>();
            while (rs.next()) {
                jogadorsEncontrados.add(new Jogador(
                        ClubeCRUD.getClubeById(rs.getInt(4)),
                        rs.getInt(3),
                        rs.getString(2),
                        rs.getInt(1),
                        rs.getString(5)
                ));
            }
            return jogadorsEncontrados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteJogador(int id) {
        getJogadorById(id);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Jogador WHERE idJogador = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void putJogador(Jogador jogador) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE Jogador SET nome = ?, idade = ?, idClube = ?, posicao = ? WHERE idJogador = ?");
            ps.setString(1, jogador.getNome());
            ps.setInt(2, jogador.getIdade());
            ps.setInt(3, jogador.getClube().getIdClube());
            ps.setString(4, jogador.getPosicao());
            ps.setInt(5, jogador.getIdJogador());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
