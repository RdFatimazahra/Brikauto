// // order.component.ts
// import { Component, OnInit } from '@angular/core';
// import { ClientService } from 'src/app/services/client.service';

// interface CartItem {
//   idPiece: number;
//   nom: string;
//   prix: number;
//   quantity: number;
// }

// @Component({
//   selector: 'app-order',
//   template: `
//     <div class="order-container">
//       <h2>Your Order</h2>
//       <div class="cart-items">
//         <div *ngFor="let item of cartItems" class="cart-item">
//           <span>{{ item.nom }}</span>
//           <span>{{ item.prix | currency:'dh' }}</span>
//           <input type="number" [(ngModel)]="item.quantity" min="1" (change)="updateQuantity(item)">
//           <button (click)="removeItem(item.idPiece)">Remove</button>
//         </div>
//       </div>
//       <div class="total">
//         <strong>Total: {{ calculateTotal() | currency:'dh' }}</strong>
//       </div>
//       <button class="place-order-btn" (click)="placeOrder()" [disabled]="cartItems.length === 0">
//         Place Order
//       </button>
//     </div>
//   `,
//   styles: [`
//     .order-container {
//       max-width: 600px;
//       margin: 0 auto;
//       padding: 20px;
//     }
//     .cart-items {
//       margin-bottom: 20px;
//     }
//     .cart-item {
//       display: flex;
//       justify-content: space-between;
//       align-items: center;
//       margin-bottom: 10px;
//       padding: 10px;
//       border: 1px solid #ddd;
//       border-radius: 4px;
//     }
//     .cart-item input {
//       width: 50px;
//       text-align: center;
//     }
//     .total {
//       text-align: right;
//       margin-bottom: 20px;
//     }
//     .place-order-btn {
//       width: 100%;
//       padding: 10px;
//       background-color: #4CAF50;
//       color: white;
//       border: none;
//       border-radius: 4px;
//       cursor: pointer;
//     }
//     .place-order-btn:disabled {
//       background-color: #cccccc;
//       cursor: not-allowed;
//     }
//   `]
// })
// export class OrderComponent implements OnInit {
//   cartItems: CartItem[] = [];

//   constructor(private clientService: ClientService) {}

//   ngOnInit() {
//     this.loadCartItems();
//   }

//   loadCartItems() {
//     this.clientService.getCart().subscribe(
//       (cart: any) => {
//         this.cartItems = cart.items;
//       },
//       (error) => {
//         console.error('Error loading cart:', error);
//       }
//     );
//   }

//   updateQuantity(item: CartItem) {
//     this.clientService.addToCart(item.idPiece, item.quantity).subscribe(
//       () => {
//         console.log('Quantity updated');
//       },
//       (error) => {
//         console.error('Error updating quantity:', error);
//       }
//     );
//   }

//   removeItem(pieceId: number) {
//     this.clientService.removeFromCart(pieceId).subscribe(
//       () => {
//         this.cartItems = this.cartItems.filter(item => item.idPiece !== pieceId);
//       },
//       (error) => {
//         console.error('Error removing item from cart:', error);
//       }
//     );
//   }

//   calculateTotal(): number {
//     return this.cartItems.reduce((total, item) => total + (item.prix * item.quantity), 0);
//   }

//   placeOrder() {
//     const orderData = {
//       items: this.cartItems.map(item => ({
//         pieceId: item.idPiece,
//         quantity: item.quantity
//       }))
//     };

//     this.clientService.placeOrder(orderData).subscribe(
//       (response) => {
//         console.log('Order placed successfully:', response);
//         // Clear the cart after successful order
//         this.cartItems = [];
//         // You might want to navigate to an order confirmation page here
//       },
//       (error) => {
//         console.error('Error placing order:', error);
//       }
//     );
//   }
// }