import { Component, Input, OnInit } from '@angular/core';
import { CartItems } from 'src/app/interfaces/CartItems';
import { Piece } from 'src/app/interfaces/Piece';
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
  piece: Piece[]=[];
  errorMessage: string | null = null;
  idpaner?: number;
  // idpiece?: number;

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
      this.getPanierByUserId(userId),
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

 //GetPanierByIdUser
  getPanierByUserId(userId: number): void {
    this.cartService.getPanierIdByUserId(userId).subscribe(
      (panierId) => {
        console.log('Fetched panier ID:', panierId);
        this.idpaner = panierId;
      },
      error => {
        console.error('Error fetching panier ID:', error);
      }
    );
  }

  //IncreaseQuantity:::
  increaseQuantity(item: CartItems): void {
    const userId = this.authService.getId();
    
    if (userId ) {
      // Proceed if the item and user are valid
      this.cartService.incrementQuantity(this.idpaner, item.pieceId).subscribe(
        ()=>{
          console.log("done")
          this.loadCartItems();
        }, error =>{
          console.log('error',error)
          this.loadCartItems();
        }
        
        
      );

    } else {
      if (!userId) {
        this.errorMessage = 'User not logged in.';
      } else {
        this.errorMessage = 'Invalid item.';
        console.error('Invalid item properties:', this.errorMessage);
      }
    }
  }
  
  
  

  //DecreaseQuantity:::
  decreaseQuantity(item: CartItems): void {
    if (item.quantite > 1) {
      const userId = this.authService.getId();
      if (userId && item.pieceId) {
        this.cartService.decrementQuantity(this.idpaner, item.pieceId).subscribe(
          ()=>{
            console.log("done")
            this.loadCartItems();
          }, error =>{
            console.log('error',error)
            this.loadCartItems();
          }
          
          
        );
      } else {
        this.errorMessage = 'User not logged in or invalid item.';
      }
    } else {
      this.errorMessage = 'Quantity cannot be less than 1.';
    }
  }
//Checkout:::
  // checkout(){
  //   const userId = this.authService.getId(); 
  //   if (userId) {
  //     //place order
  //     this.orderService.placeOrder(userId).subscribe(
  //       (data)=>{
  //           console.log("place order done ")
  //       },
  //       (error)=>{
  //         console.error("your error",error)
  //       }
  //     )
  //   } else {
  //     this.errorMessage = 'User not logged in.';
  //   }

  // }



  checkout() {
    const userId = this.authService.getId(); 
    if (userId) {
        this.orderService.placeOrder(userId).subscribe(
            (confirmationUrl) => {
                console.log("Order placed successfully");
                window.location.href = confirmationUrl; // Redirect to the confirmation URL
            },
            (error) => {
                this.errorMessage = 'Error placing order: ' + JSON.stringify(error); // Log the error response
                console.error("Error", error);
            }
        );
    } else {
        this.errorMessage = 'User not logged in.';
    }
}

  
  



}
