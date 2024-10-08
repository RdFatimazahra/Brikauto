import { Component, OnInit } from '@angular/core';
import { CartItems } from 'src/app/interfaces/CartItems';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  cartItems: CartItems[] = [];
  errorMessage: string | null = null;

  constructor(
    private cartService: CartService,
    private authService: AuthenticateService,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.loadCartItems();
  }

  loadCartItems(): void {
    const userId = this.authService.getId(); // Use the getId method

    if (userId) {
      // Fetch cart items based on user ID
      this.cartService.getPanierItemsByUtilisateurId(userId).subscribe(
        (items) => {
          console.log('Fetched cart items:', items); 
          this.cartItems = items; // Assign fetched items to cartItems
        },
        error => {
          this.errorMessage = 'Failed to load cart items.';
          console.error('Error loading cart items:', error);
        }
      );
    } else {
      this.errorMessage = 'User not logged in.';
    }
  }




  checkout(){
    const userId = this.authService.getId(); 
    if (userId) {
      //place order
      this.orderService.placeOrder(userId).subscribe(
        (data)=>{
            console.log("place order done ")
        },
        (error)=>{
          console.error("your error",error)
        }
      )
    } else {
      this.errorMessage = 'User not logged in.';
    }

  }




}
