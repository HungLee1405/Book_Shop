/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trang
 */
public class CartDTO {

    private List<ItemDTO> listItems;

    public CartDTO() {
        this.listItems = new ArrayList<>();
    }

    public CartDTO(List<ItemDTO> listItems) {
        this.listItems = listItems;
    }

    public List<ItemDTO> getListItems() {
        return listItems;
    }

    public void setListItems(List<ItemDTO> listItems) {
        this.listItems = listItems;
    }

    public ItemDTO getItemByProductId(int productId) {
        for (ItemDTO item : listItems) {
            if (item.getProduct().getProductId() == productId) {
                return item;
            }
        }
        return null;
    }

    public void addItem(ItemDTO newItem) {
        ItemDTO existingItem = getItemByProductId(newItem.getProduct().getProductId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
        } else {
            listItems.add(newItem);
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (ItemDTO item : listItems) {
            total += item.getQuantity() * item.getProduct().getSalePrice();
        }
        return Math.round(total * 100.0) / 100.0;
    }
}
