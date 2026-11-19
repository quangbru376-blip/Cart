package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }

    public int getCount() {
        return items != null ? items.size() : 0;
    }

    public void addItem(LineItem lineItem) {
        if (items == null) {
            items = new ArrayList<>();
        }
        String code = lineItem.getProduct().getProductCode();
        int quantity = lineItem.getQuantity();

        // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng
        for (int i = 0; i < items.size(); i++) {
            LineItem item = items.get(i);
            if (item.getProduct().getProductCode().equalsIgnoreCase(code)) {
                item.setQuantity(quantity);
                return;
            }
        }
        // Nếu chưa có, thêm mới vào giỏ hàng
        items.add(lineItem);
    }

    public void removeItem(LineItem lineItem) {
        if (items == null) {
            return;
        }
        String code = lineItem.getProduct().getProductCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem item = items.get(i);
            if (item.getProduct().getProductCode().equalsIgnoreCase(code)) {
                items.remove(i);
                return;
            }
        }
    }
}
