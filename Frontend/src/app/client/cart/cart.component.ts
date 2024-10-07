// import { Component, OnInit } from '@angular/core';
// import { CartService, CartItem } from '../services/cart.service';
// import { faPlus, faMinus, faTimes } from '@fortawesome/free-solid-svg-icons';

// @Component({
//   selector: 'app-cart',
//   templateUrl: './cart.component.html',
//   styleUrls: ['./cart.component.scss']
// })
// export class CartComponent implements OnInit {
//   cartItems: CartItem[] = [];
//   total: number = 0;
//   faPlus = faPlus;
//   faMinus = faMinus;
//   faTimes = faTimes;

//   constructor(private cartService: CartService) { }

//   ngOnInit(): void {
//     this.loadCart();
//   }

//   loadCart(): void {
//     this.cartService.getCart().subscribe(
//       (items) => {
//         this.cartItems = items;
//         this.updateTotal();
//       },
//       (error) => console.error('Error loading cart:', error)
//     );
//   }

//   removeItem(pieceId: number): void {
//     this.cartService.removeFromCart(pieceId).subscribe(
//       () => this.loadCart(),
//       (error) => console.error('Error removing item from cart:', error)
//     );
//   }

//   updateQuantity(pieceId: number, change: number): void {
//     const item = this.cartItems.find(item => item.idPiece === pieceId);
//     if (item) {
//       const newQuantity = item.quantity + change;
//       if (newQuantity > 0) {
//         this.cartService.updateQuantity(pieceId, newQuantity).subscribe(
//           () => this.loadCart(),
//           (error) => console.error('Error updating quantity:', error)
//         );
//       } else {
//         this.removeItem(pieceId);
//       }
//     }
//   }

//   updateTotal(): void {
//     this.cartService.getTotal().subscribe(
//       (total) => this.total = total,
//       (error) => console.error('Error calculating total:', error)
//     );
//   }

//   proceedToCheckout(): void {
//     // Implement checkout logic here
//     console.log('Proceeding to checkout');
//   }
// }