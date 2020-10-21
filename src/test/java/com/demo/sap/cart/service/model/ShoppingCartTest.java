package com.demo.sap.cart.service.model;

import com.demo.sap.cart.model.Product;
import com.demo.sap.cart.model.ProductBuilder;
import com.demo.sap.cart.model.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    @Mock
    private Product product;

    @Test
    void whenCreatingShoppingCart_usingSampleValidData_shouldCreateCartSuccessfully() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product cucumbers = new ProductBuilder()
                .id(UUID.randomUUID().toString())
                .name("cucumbers")
                .details("Fresh cucumbers from our village")
                .price(2.00d)
                .createProduct();
        Product pumpkins = new ProductBuilder()
                .id(UUID.randomUUID().toString())
                .name("pumpkins")
                .details("Huge and sweet pumpkins from our village")
                .price(2.00d)
                .createProduct();

        // Act
        cart = cart.addItemToCart(cucumbers, 12);
        cart = cart.addItemToCart(pumpkins, 240);

        // Assert
        assertEquals(504d, cart.calculatePrice());
    }

    @Test
    void whenCreatingShoppingCart_usingInvalidQuantityNumber_shouldThrowException() {
        ShoppingCart cart = new ShoppingCart();

        Assertions.assertThrows(IllegalArgumentException.class, () -> cart.addItemToCart(product, 0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> cart.addItemToCart(product, -156912));
    }

    @Test
    void whenCreatingShoppingCart_usingInvalidProduct_shouldThrowException() {
        ShoppingCart cart = new ShoppingCart();

        Assertions.assertThrows(IllegalArgumentException.class, () -> cart.addItemToCart(null, 1));

    }
}
