// 
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service'; // Import CartService

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  cartItemCount = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.loadCartItemCount();
  }

  loadCartItemCount(): void {
    const userId = 1; // Replace with the actual userId (from authentication)
    this.cartService.getPanierIdByUserId(userId).subscribe(
      (panierId) => {
        // Here, you would typically call another API to get the number of items in the cart
        // Mocking a fixed cart item count for now
        this.cartItemCount = 5; // Replace with real cart item count logic
      },
      (error) => {
        console.error('Error loading cart count:', error);
      }
    );
  }
}
