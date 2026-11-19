package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProductIO {

    // Danh sách dự phòng nếu không đọc được file products.txt
    private static final Map<String, Product> DEFAULT_PRODUCTS = new HashMap<>();

    static {
        DEFAULT_PRODUCTS.put("8601", new Product("8601", "86 (the band) - True Life Songs and Pictures", 14.95));
        DEFAULT_PRODUCTS.put("pf01", new Product("pf01", "Paddlefoot - The first CD", 12.95));
        DEFAULT_PRODUCTS.put("pf02", new Product("pf02", "Paddlefoot - The second CD", 14.95));
        DEFAULT_PRODUCTS.put("jr01", new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95));
    }

    public static Product getProduct(String productCode, String filepath) {
        if (productCode == null || productCode.trim().isEmpty()) {
            return null;
        }

        if (filepath != null) {
            try {
                File file = new File(filepath);
                if (file.exists()) {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = in.readLine();
                    while (line != null) {
                        StringTokenizer t = new StringTokenizer(line, "|");
                        if (t.countTokens() >= 3) {
                            String code = t.nextToken();
                            String name = t.nextToken();
                            double price = Double.parseDouble(t.nextToken());
                            if (code.equalsIgnoreCase(productCode)) {
                                in.close();
                                return new Product(code, name, price);
                            }
                        }
                        line = in.readLine();
                    }
                    in.close();
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("ProductIO Error: " + e.getMessage());
            }
        }

        // Dự phòng nếu không tìm thấy file hoặc mã sản phẩm trong file
        return getProduct(productCode);
    }

    public static Product getProduct(String productCode) {
        if (productCode == null) {
            return null;
        }
        return DEFAULT_PRODUCTS.get(productCode);
    }

    public static ArrayList<Product> getProducts(String filepath) {
        ArrayList<Product> products = new ArrayList<>();
        if (filepath != null) {
            try {
                File file = new File(filepath);
                if (file.exists()) {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = in.readLine();
                    while (line != null) {
                        StringTokenizer t = new StringTokenizer(line, "|");
                        if (t.countTokens() >= 3) {
                            String code = t.nextToken();
                            String name = t.nextToken();
                            double price = Double.parseDouble(t.nextToken());
                            products.add(new Product(code, name, price));
                        }
                        line = in.readLine();
                    }
                    in.close();
                    return products;
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("ProductIO Error: " + e.getMessage());
            }
        }
        return new ArrayList<>(DEFAULT_PRODUCTS.values());
    }
}
