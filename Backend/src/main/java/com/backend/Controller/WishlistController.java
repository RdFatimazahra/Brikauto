//package com.backend.Controller;
//
//import com.backend.DTO.WishlistDTO;
//import com.backend.Service.WishlistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/User/wishlist")
//public class WishlistController {
//
//    @Autowired
//    private WishlistService wishlistService;
//
//    @GetMapping
//    public ResponseEntity<WishlistDTO> getWishlist(@AuthenticationPrincipal UserDetails userDetails) {
//        Long userId = getUserIdFromUserDetails(userDetails);
//        WishlistDTO wishlist = wishlistService.getWishlistByUserId(userId);
//        return ResponseEntity.ok(wishlist);
//    }
//
//    @PostMapping("/add/{pieceId}")
//    public ResponseEntity<WishlistDTO> addToWishlist(@AuthenticationPrincipal UserDetails userDetails,
//                                                     @PathVariable Long pieceId) {
//        Long userId = getUserIdFromUserDetails(userDetails);
//        WishlistDTO updatedWishlist = wishlistService.addPieceToWishlist(userId, pieceId);
//        return ResponseEntity.ok(updatedWishlist);
//    }
//
//    @DeleteMapping("/remove/{pieceId}")
//    public ResponseEntity<WishlistDTO> removeFromWishlist(@AuthenticationPrincipal UserDetails userDetails,
//                                                          @PathVariable Long pieceId) {
//        Long userId = getUserIdFromUserDetails(userDetails);
//        WishlistDTO updatedWishlist = wishlistService.removePieceFromWishlist(userId, pieceId);
//        return ResponseEntity.ok(updatedWishlist);
//    }
//
//    @DeleteMapping("/clear")
//    public ResponseEntity<Void> clearWishlist(@AuthenticationPrincipal UserDetails userDetails) {
//        Long userId = getUserIdFromUserDetails(userDetails);
//        wishlistService.clearWishlist(userId);
//        return ResponseEntity.noContent().build();
//    }
//
//    private Long getUserIdFromUserDetails(UserDetails userDetails) {
//        // Implement this method based on how you store the user ID in UserDetails
//        // For example, if you use a custom UserDetails implementation:
//        // return ((CustomUserDetails) userDetails).getId();
//        throw new UnsupportedOperationException("Method not implemented");
//    }
//}