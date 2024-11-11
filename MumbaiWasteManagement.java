import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class MumbaiWasteManagement {

    static final String URL = "jdbc:mysql://localhost:3306/mumbaiwastemanagementsystem";
    static final String USER = "root";
    static final String PASSWORD = "";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Define endpoints
        server.createContext("/api/waste/all", new WasteDataHandler());  // Fetch all data
        server.createContext("/api/waste/area", new AreaSpecificHandler()); // Fetch by area name

        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:8000");
    }

    // Handler to fetch all records from wastemanagement table
    static class WasteDataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                ArrayList<Map<String, String>> wasteData = getWasteData();
                String jsonResponse = new Gson().toJson(wasteData);

                // Add CORS headers
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                // Handle preflight requests
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1); // No content for OPTIONS request
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

    // Handler to fetch records for a specific area name
    static class AreaSpecificHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String queryParams = exchange.getRequestURI().getQuery();
                String areaName = queryParams.split("=")[1];

                ArrayList<Map<String, String>> areaData = getAreaData(areaName);
                String jsonResponse = new Gson().toJson(areaData);

                // Add CORS headers
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();
            } else if ("OPTIONS".equals(exchange.getRequestMethod())) {
                // Handle preflight requests
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1); // No content for OPTIONS request
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

    // Method to fetch all waste data
    public static ArrayList<Map<String, String>> getWasteData() {
        ArrayList<Map<String, String>> wasteList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM wastemanagement";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Map<String, String> wasteData = new HashMap<>();
                wasteData.put("area_name", rs.getString("area_name"));
                wasteData.put("waste_type", rs.getString("waste_type"));
                wasteData.put("collection_date", rs.getString("collection_date"));
                wasteList.add(wasteData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wasteList;
    }

    // Method to fetch data for a specific area
    public static ArrayList<Map<String, String>> getAreaData(String areaName) {
        ArrayList<Map<String, String>> areaList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM wastemanagement WHERE area_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, areaName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Map<String, String> areaData = new HashMap<>();
                areaData.put("area_name", rs.getString("area_name"));
                areaData.put("waste_type", rs.getString("waste_type"));
                areaData.put("collection_date", rs.getString("collection_date"));
                areaList.add(areaData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areaList;
    }
}
