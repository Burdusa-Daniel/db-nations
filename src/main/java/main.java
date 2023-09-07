import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String query = "select countries.country_id as id_paese , countries.name as nome, regions.name as regione , continents.name as continente \n" +
                "from countries\n" +
                "join regions  on countries.region_id  = regions.region_id\n" +
                "join continents on regions.continent_id  = continents.continent_id\n" +
                "where countries.name like ?\n" +
                "order by countries.name;";
        String url = "jdbc:mysql://localhost:3306/nazioni";
        String user = "root";
        String password = "root";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("cerca una nazione");
            String ricerca = input.nextLine();
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1,"%"+ricerca+"%");
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String idNazione = rs.getString("id_paese");
                        String nomeNazione = rs.getString("nome");
                        String regione = rs.getString("regione");
                        String continente = rs.getString("continente");
                        System.out.print(idNazione+" ");
                        System.out.print(nomeNazione+" ");
                        System.out.print(regione+" ");
                        System.out.println(continente);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }
}
