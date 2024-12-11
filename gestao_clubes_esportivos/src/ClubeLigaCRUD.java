import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubeLigaCRUD {

    public static void postClubeLiga(Clube clube, Liga liga) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ClubeLiga (idClube, idLiga) VALUES (?, ?)");
            ps.setInt(1, clube.getIdClube());
            ps.setInt(2, liga.getIdLiga());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ClubeLiga> getClubeLiga() {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ClubeLiga");
            ResultSet rs = ps.executeQuery();
            List<ClubeLiga> clubeLigasEncontradas = new ArrayList<>();
            while (rs.next()) {
                clubeLigasEncontradas.add(new ClubeLiga(
                        ClubeCRUD.getClubeById(rs.getInt(1)),
                        LigaCRUD.getLigaById(rs.getInt(2))
                ));
            }
            return clubeLigasEncontradas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ClubeLiga> getLigaByClube(int idClube) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ClubeLiga WHERE idClube = ?");
            ps.setInt(1, idClube);
            ResultSet rs = ps.executeQuery();
            List<ClubeLiga> clubeLigasEncontradas = new ArrayList<>();
            while (rs.next()) {
                clubeLigasEncontradas.add(new ClubeLiga(
                        ClubeCRUD.getClubeById(rs.getInt(1)),
                        LigaCRUD.getLigaById(rs.getInt(2))
                ));
            }
            return clubeLigasEncontradas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ClubeLiga> getClubeByLiga(int idLiga) {
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ClubeLiga WHERE idLiga = ?");
            ps.setInt(1, idLiga);
            ResultSet rs = ps.executeQuery();
            List<ClubeLiga> clubeLigasEncontradas = new ArrayList<>();
            while (rs.next()) {
                clubeLigasEncontradas.add(new ClubeLiga(
                        ClubeCRUD.getClubeById(rs.getInt(1)),
                        LigaCRUD.getLigaById(rs.getInt(2))
                ));
            }
            return clubeLigasEncontradas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteClubeLiga(int clubeId, int ligaId){
        ClubeCRUD.getClubeById(clubeId);
        LigaCRUD.getLigaById(ligaId);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ClubeLiga WHERE idClube = ? AND idLiga = ?");
            ps.setInt(1, clubeId);
            ps.setInt(2, ligaId);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllByClube(int clubeId){
        ClubeCRUD.getClubeById(clubeId);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ClubeLiga WHERE idClube = ?");
            ps.setInt(1, clubeId);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteAllByLiga(int ligaId){
        LigaCRUD.getLigaById(ligaId);
        try (Connection connection = ConexaoBanco.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ClubeLiga WHERE idLiga = ?");
            ps.setInt(1, ligaId);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
