/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.*;
import model.CartDTO;
import model.ItemDTO;
import model.ProductDAO;
import model.ProductDTO;

public class CartCookieUtils {

    private static final String CART_COOKIE_NAME = "cart";
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7 ngày

    // Parse chuỗi "2-1,5-3" => Map<productId, quantity>
    public static Map<Integer, Integer> parseCartCookie(String cookieValue) {
        Map<Integer, Integer> cartMap = new HashMap<>();
        if (cookieValue == null || cookieValue.trim().isEmpty()) {
            return cartMap;
        }

        String[] items = cookieValue.split(",");
        for (String item : items) {
            String[] parts = item.split("-");
            if (parts.length == 2) {
                try {
                    int productId = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);
                    cartMap.put(productId, quantity);
                } catch (NumberFormatException e) {
                    // ignore malformed entry
                }
            }
        }
        return cartMap;
    }

    // Convert Map<productId, quantity> => chuỗi "2-1,5-3"
    public static String toCookieString(Map<Integer, Integer> cartMap) {
        List<String> parts = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            parts.add(entry.getKey() + "-" + entry.getValue());
        }
        return String.join(",", parts);
    }

    // Lấy Map từ request cookie (sau khi giải mã)
    public static Map<Integer, Integer> getCartMapFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cartData = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (CART_COOKIE_NAME.equals(c.getName())) {
                    try {
                        cartData = URLDecoder.decode(c.getValue(), "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return parseCartCookie(cartData);
    }

    // Cập nhật lại cookie (sau khi thêm/sửa) và mã hóa
    public static void updateCartCookie(HttpServletResponse response, Map<Integer, Integer> cartMap) {
        try {
            String rawValue = toCookieString(cartMap);
            String encodedValue = URLEncoder.encode(rawValue, "UTF-8");

            Cookie cartCookie = new Cookie(CART_COOKIE_NAME, encodedValue);
            cartCookie.setPath("/");
            cartCookie.setMaxAge(COOKIE_MAX_AGE);
            response.addCookie(cartCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thêm hoặc cập nhật sản phẩm trong cookie
    public static void addToCartCookie(HttpServletRequest request, HttpServletResponse response, int productId, int quantity) {
        Map<Integer, Integer> cartMap = getCartMapFromRequest(request);
        cartMap.put(productId, cartMap.getOrDefault(productId, 0) + quantity);
        updateCartCookie(response, cartMap);
    }

    // Tạo CartDTO từ cookie
    public static CartDTO getCartFromCookie(HttpServletRequest request) {
        ProductDAO dao = new ProductDAO();
        Map<Integer, Integer> cartMap = getCartMapFromRequest(request);
        CartDTO cart = new CartDTO();

        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            ProductDTO product = dao.getProductByID(entry.getKey());
            if (product != null) {
                int quantity = entry.getValue();
                ItemDTO item = new ItemDTO(product, quantity, product.getSalePrice());
                cart.addItem(item);
            }
        }

        return cart;
    }

    // Xoá 1 sản phẩm
    public static void removeItemFromCookie(HttpServletRequest request, HttpServletResponse response, int productId) {
        Map<Integer, Integer> cartMap = getCartMapFromRequest(request);
        cartMap.remove(productId);
        updateCartCookie(response, cartMap);
    }

    // Cập nhật số lượng
    public static void updateItemQuantity(HttpServletRequest request, HttpServletResponse response, int productId, int newQuantity) {
        Map<Integer, Integer> cartMap = getCartMapFromRequest(request);
        if (newQuantity <= 0) {
            cartMap.remove(productId);
        } else {
            cartMap.put(productId, newQuantity);
        }
        updateCartCookie(response, cartMap);
    }

    // Xoá toàn bộ giỏ hàng (cookie)
    public static void clearCartCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(CART_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static CartDTO buildCartFromMap(Map<Integer, Integer> cartMap) {
        ProductDAO dao = new ProductDAO();
        CartDTO cart = new CartDTO();

        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            ProductDTO product = dao.getProductByID(entry.getKey());
            if (product != null) {
                ItemDTO item = new ItemDTO(product, entry.getValue(), product.getSalePrice());
                cart.addItem(item);
            }
        }

        return cart;
    }

}
