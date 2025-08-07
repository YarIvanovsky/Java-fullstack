package com.dailycodework.buynowdotcom.controller;

import com.dailycodework.buynowdotcom.dtos.CartItemDto;
import com.dailycodework.buynowdotcom.model.Cart;
import com.dailycodework.buynowdotcom.model.CartItem;
import com.dailycodework.buynowdotcom.model.User;
import com.dailycodework.buynowdotcom.response.ApiResponse;
import com.dailycodework.buynowdotcom.service.cart.CartItemService;
import com.dailycodework.buynowdotcom.service.cart.CartService;
import com.dailycodework.buynowdotcom.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam Long productId,
            @RequestParam int quantity){
        User user = userService.getAuthenticatedUser();
        Cart cart = cartService.initializeNewCartForUser(user);

        cartItemService.addItemToCart(cart.getId(), productId, quantity);

        CartItem cartItem = cartItemService.getCartItem(cart.getId(), productId);
        CartItemDto dto = cartItemService.convertCartItemToDto(cartItem);
        return ResponseEntity.ok(new ApiResponse("Item added successfully!", null));
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,@PathVariable Long itemId){
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Item removed successfully!", null));
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateCartItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId,
            @RequestParam int quantity){

        cartItemService.updateItemQuantity(cartId, itemId, quantity);
        CartItem updatedItem = cartItemService.getCartItem(cartId, itemId);
        CartItemDto dto = cartItemService.convertCartItemToDto(updatedItem);

        return ResponseEntity.ok(new ApiResponse("Item updated successfully!", null));
    }
}
