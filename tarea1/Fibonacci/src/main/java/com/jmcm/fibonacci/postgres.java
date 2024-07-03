
package com.jmcm.fibonacci;

import java.sql.*;
import java.util.Scanner;

public class postgres {
    public static void main(String[] args) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/Fibonacci", "postgres", "1234")) {

            System.out.print("Introduzca un número para obtener su fibonnaci: ");
            Scanner sc = new Scanner(System.in);
            int posicion = sc.nextInt();

            int numero = fibonacci.getFibo(posicion);
            System.out.print("El fibonacci es: " + numero + '\n');

            PreparedStatement st = connection.prepareStatement("INSERT INTO fibo (posicion, numero) VALUES (?, ?)");
            st.setInt(1, posicion);
            st.setInt(2, numero);
            st.executeUpdate();
            st.close();

            Statement statement = connection.createStatement();
            System.out.println("Tabla postgres:");
            System.out.printf("%-30.30s  %-30.30s%n", "posicion", "numero");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM fibo");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", 
                        resultSet.getString("posicion"), resultSet.getString("numero"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
