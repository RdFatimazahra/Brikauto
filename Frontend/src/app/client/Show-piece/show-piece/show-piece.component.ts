import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/interfaces/CartItem';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-show-piece',
  templateUrl: './show-piece.component.html',
  styleUrls: ['./show-piece.component.scss']
})
export class ShowPieceComponent implements OnInit {
  autoParts: Piece[] = [];
  // wishlist: number[] = []; // Commented out wishlist
  cart: { [key: number]: number } = {};

  constructor(private clientService: ClientService, private router: Router) { }

  ngOnInit(): void {
    this.loadPieces();
    // this.loadWishlist(); // Commented out wishlist loading
    this.loadCart();
  }

  loadPieces(): void {
    this.clientService.getAllPieces().subscribe(
      (pieces) => {
        this.autoParts = pieces;
      },
      (error) => {
        console.error('Error fetching pieces:', error);
      }
    );
  }

  //Show details:
  showPieceDetails(piece: Piece): void {
    if (piece && piece.id!== undefined) {
      this.router.navigate(['/piece-details', piece.id]);
    } else {
      console.error('Attempted to navigate to piece details with undefined pieceId', piece);
    }
  }

  // Commented out wishlist-related code
  // loadWishlist(): void {
  //   this.clientService.getWishlist().subscribe(
  //     (wishlistPieces) => {
  //       this.wishlist = wishlistPieces.map(piece => piece.idPiece);
  //     },
  //     (error) => {
  //       console.error('Error fetching wishlist:', error);
  //     }
  //   );
  // }

  loadCart(): void {
    this.clientService.getCart().subscribe(
      (cartItems: CartItem[]) => {  // Use CartItem[] as the type for cartItems
        this.cart = cartItems.reduce((acc: { [key: number]: number }, item: CartItem) => {
          acc[item.pieceId] = item.quantity;
          return acc;
        }, {});
      },
      (error) => {
        console.error('Error fetching cart:', error);
      }
    );
  }
  

  // Commented out wishlist-related code
  // addToWishlist(pieceId: number): void {
  //   this.clientService.addToWishlist(pieceId).subscribe(
  //     () => {
  //       this.wishlist.push(pieceId);
  //     },
  //     (error) => {
  //       console.error('Error adding to wishlist:', error);
  //     }
  //   );
  // }

  addToCart(pieceId: number): void {
    this.clientService.addToCart(pieceId, 1).subscribe(
      () => {
        this.cart[pieceId] = (this.cart[pieceId] || 0) + 1;
      },
      (error) => {
        console.error('Error adding to cart:', error);
      }
    );
  }

  // Commented out wishlist-related method
  // isInWishlist(pieceId: number): boolean {
  //   return this.wishlist.includes(pieceId);
  // }

  getCartQuantity(pieceId: number): number {
    return this.cart[pieceId] || 0;
  }
}
