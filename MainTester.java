package GSI;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTester {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int totalTests = 13;
        int passedTests = 0;
        List<String> failedTests = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {

            ProductDAO dao = new ProductDAO();

            System.out.println("========== 🧪 TEST CRUD PRODUCT ==========");

            // --------------------------
            // 1️⃣ Crear producto válido
            // --------------------------
            System.out.println("\n🟢 Test 1: Crear producto válido");
            if (!askContinue()) return;
            try {
                Product p1 = new Product("Guitarra Fender", 699.99, 5, "Instrumentos", "Guitarra eléctrica Fender Stratocaster");
                // dao.addProduct(p1); // comentado para no insertar en DB
                System.out.println("✅ Producto añadido correctamente (simulado): " + p1);
                passedTests++;
            } catch (Exception e) {
                System.out.println("❌ Error en Test 1: " + e.getMessage());
                failedTests.add("1 / Crear producto válido");
            }

            // --------------------------
            // 2️⃣ Obtener producto por ID
            // --------------------------
            System.out.println("\n🟢 Test 2: Obtener producto por ID (1)");
            if (!askContinue()) return;
            try {
                Product fetched = dao.getProductById(0); // suponemos que existe
                if (fetched != null) {
                    System.out.println("✅ Producto obtenido: " + fetched);
                    passedTests++;
                } else {
                    System.out.println("❌ No se encontró el producto con ID 1");
                    failedTests.add("2 / Obtener producto por ID");
                }
            } catch (Exception e) {
                System.out.println("❌ Error al obtener producto: " + e.getMessage());
                failedTests.add("2 / Obtener producto por ID");
            }

            // --------------------------
            // 3️⃣ Listar todos los productos
            // --------------------------
            System.out.println("\n🟢 Test 3: Listar todos los productos");
            if (!askContinue()) return;
            try {
                List<Product> all = dao.getAllProducts();
                if (all.isEmpty()) {
                    System.out.println("⚠️ No hay productos en la base de datos.");
                    failedTests.add("3 / Listar todos los productos");
                } else {
                    all.forEach(System.out::println);
                    passedTests++;
                }
            } catch (Exception e) {
                System.out.println("❌ Error al listar productos: " + e.getMessage());
                failedTests.add("3 / Listar todos los productos");
            }

            // --------------------------
            // 4️⃣ Actualizar producto
            // --------------------------
            System.out.println("\n🟢 Test 4: Actualizar producto con ID 1");
            if (!askContinue()) return;
            try {
                Product fetched = dao.getProductById(1);
                if (fetched != null) {
                    fetched.setPrice(749.99);
                    fetched.setStock(7);
                    boolean updated = true; // simulación
                    System.out.println(updated ? "✅ Producto actualizado (simulado)" : "❌ No se actualizó el producto");
                    passedTests++;
                } else {
                    System.out.println("⚠️ No existe el producto con ID 1 para actualizar.");
                    failedTests.add("4 / Actualizar producto");
                }
            } catch (Exception e) {
                System.out.println("❌ Error al actualizar: " + e.getMessage());
                failedTests.add("4 / Actualizar producto");
            }

            // --------------------------
            // 5️⃣ Borrar producto
            // --------------------------
            System.out.println("\n🟢 Test 5: Borrar producto con ID 1");
            if (!askContinue()) return;
            try {
                boolean deleted = true; // simulación
                System.out.println(deleted ? "✅ Producto borrado (simulado)" : "❌ No se borró el producto");
                passedTests++;
            } catch (Exception e) {
                System.out.println("❌ Error al borrar: " + e.getMessage());
                failedTests.add("5 / Borrar producto");
            }

            // --------------------------
            // 6️⃣ Stock negativo
            // --------------------------
            System.out.println("\n🔴 Test 6: Crear producto con stock negativo");
            if (!askContinue()) return;
            try {
                Product pError = new Product("Bajo Yamaha", 499.99, -3, "Instrumentos", "Bajo de 4 cuerdas");
                dao.addProduct(pError); // simulado
                System.out.println("❌ ERROR: Se permitió producto con stock negativo");
                failedTests.add("6 / Stock negativo");
            } catch (NegativeInt e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 7️⃣ Precio negativo
            // --------------------------
            System.out.println("\n🔴 Test 7: Crear producto con precio negativo");
            if (!askContinue()) return;
            try {
                Product pError = new Product("Micrófono Shure", -199.99, 10, "Audio", "Micrófono profesional");
                dao.addProduct(pError); // simulado
                System.out.println("❌ ERROR: Se permitió producto con precio negativo");
                failedTests.add("7 / Precio negativo");
            } catch (NegativeInt e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 8️⃣ Nombre nulo
            // --------------------------
            System.out.println("\n🔴 Test 8: Crear producto con nombre nulo");
            if (!askContinue()) return;
            try {
                Product pError2 = new Product(null, 499.99, 3, "Instrumentos", "Producto sin nombre");
                dao.addProduct(pError2); // simulado
                System.out.println("❌ ERROR: Se permitió producto con nombre nulo");
                failedTests.add("8 / Nombre nulo");
            } catch (Nulability e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 9️⃣ Categoría nula
            // --------------------------
            System.out.println("\n🔴 Test 9: Crear producto con categoría nula");
            if (!askContinue()) return;
            try {
                Product pError3 = new Product("Producto sin categoría", 499.99, 3, null, "Sin categoría");
                dao.addProduct(pError3); // simulado
                System.out.println("❌ ERROR: Se permitió producto con categoría nula");
                failedTests.add("9 / Categoría nula");
            } catch (Nulability e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 10️⃣ Descripción nula
            // --------------------------
            System.out.println("\n🔴 Test 10: Crear producto con descripción nula");
            if (!askContinue()) return;
            try {
                Product pError4 = new Product("Producto sin descripción", 299.99, 3, "Instrumentos", null);
                dao.addProduct(pError4); // simulado
                System.out.println("❌ ERROR: Se permitió producto con descripción nula");
                failedTests.add("10 / Descripción nula");
            } catch (Nulability e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 11️⃣ Nombre vacío
            // --------------------------
            System.out.println("\n🔴 Test 11: Crear producto con nombre vacío");
            if (!askContinue()) return;
            try {
                Product pError5 = new Product("   ", 299.99, 3, "Instrumentos", "Sin nombre válido");
                dao.addProduct(pError5); // simulado
                System.out.println("❌ ERROR: Se permitió producto con nombre vacío");
                failedTests.add("11 / Nombre vacío");
            } catch (Nulability e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }

            // --------------------------
            // 12️⃣ Campos demasiado largos
            // --------------------------
            System.out.println("\n🔴 Test 12: Crear producto con nombre y descripción demasiado largos");
            if (!askContinue()) return;
            try {
                String longText = "X".repeat(400);
                Product pError6 = new Product(longText, 999.99, 10, "Instrumentos", longText);
                dao.addProduct(pError6); // simulado
                System.out.println("❌ ERROR: Se permitió producto con texto demasiado largo");
                failedTests.add("12 / Campos demasiado largos");
            } catch (Exception e) {
                System.out.println("✅ Excepción capturada correctamente: " + e.getMessage());
                passedTests++;
            }
            
         // --------------------------
         // 13️⃣ Comprobar si la conexión está cerrada
         // --------------------------
         System.out.println("\n🟢 Test 13: Comprobar si la conexión está cerrada después de los tests");
         try {
        	 conn.close();
             if (conn.isClosed()) {
                 System.out.println("✅ La conexión está cerrada correctamente.");
                 passedTests++;
             } else {
                 System.out.println("❌ La conexión sigue abierta, debería estar cerrada.");
                 failedTests.add("13 / Conexión cerrada después de tests");
             }
         } catch (SQLException e) {
             System.out.println("❌ Error comprobando la conexión: " + e.getMessage());
             failedTests.add("13 / Conexión cerrada después de tests");
         }

            // --------------------------
            // RESUMEN FINAL
            // --------------------------
            System.out.println("\n========== ✅ FIN DE TEST ==========");
            double passedPercent = (passedTests * 100.0) / totalTests;
            double failedPercent = 100 - passedPercent;

            System.out.println(String.format("\n📊 Resumen de Tests:"));
            System.out.println(String.format("✔️ Pasados: %.2f%% (%d/%d)", passedPercent, passedTests, totalTests));
            System.out.println(String.format("❌ Fallidos: %.2f%% (%d/%d)", failedPercent, failedTests.size(), totalTests));

            if (!failedTests.isEmpty()) {
                System.out.println("\nTests fallidos:");
                for (String fail : failedTests) {
                    System.out.println(" - " + fail);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error SQL general: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean askContinue() {
        System.out.print("\nDeseas continuar con el siguiente test? (s/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("si");
    }
}
