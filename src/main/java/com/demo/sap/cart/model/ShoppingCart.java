package com.demo.sap.cart.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ShoppingCart {
    private final Map<Product, Integer> itemAndQuantity;

    public ShoppingCart() {
        this.itemAndQuantity = new HashMap<>();
    }

    public ShoppingCart(Map<Product, Integer> itemAndQuantity) {
        this.itemAndQuantity = new HashMap<>(itemAndQuantity);
    }

    public ShoppingCart addItemToCart(Product item, Integer quantity) {
        validateEntry(item, quantity);
        Map<Product, Integer> newItems = new HashMap<>(itemAndQuantity); // O(n)
        newItems.put(item, quantity);
        return new ShoppingCart(newItems);
    }

    public ShoppingCart addItemsToCart(Map<Product, Integer> itemToQuantity) {
        Map<Product, Integer> newItems = new HashMap<>(itemToQuantity);
        // TODO: add validation
        newItems.putAll(itemToQuantity);
        return new ShoppingCart(newItems);
    }

    public ShoppingCart deleteItem(Product itemToDelete) {
        Map<Product, Integer> newItems = new HashMap<>(itemAndQuantity);
        newItems.remove(itemToDelete);
        return new ShoppingCart(newItems);
    }

    public double calculatePrice() {
        return itemAndQuantity.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * entry.getKey().getPrice())
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(itemAndQuantity, that.itemAndQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemAndQuantity);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "itemAndQuantity=" + itemAndQuantity +
                '}';
    }

    public Set<Product> getItems() {
        return new HashSet<>(itemAndQuantity.keySet());
    }

    public Map<Product, Integer> getItemToQuantity() {
        return new HashMap<>(itemAndQuantity);
    }

    private void validateEntry(Product item, Integer quantity) {
        if (item == null) {
            throw new IllegalArgumentException("Adding item to cart must not be null");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must not be zero or negative number");
        }
    }
}
