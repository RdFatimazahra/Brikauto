import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItems } from 'src/app/interfaces/CartItems';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';
import { CartService } from 'src/app/services/cart.service';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';

@Component({
  selector: 'app-show-piece',
  templateUrl: './show-piece.component.html',
  styleUrls: ['./show-piece.component.scss']
})
export class ShowPieceComponent implements OnInit {
  autoParts: Piece[] = [];
  cart: { [key: number]: number } = {};
  cartItemCount: number = 0;
  userId: number | null = null;
  panierId: number | null = null;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private cartService: CartService,
    private authService: AuthenticateService
  ) { }

  ngOnInit(): void {
    this.loadPieces();
    this.getUserIdAndPanier();
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

  showPieceDetails(piece: Piece): void {
    if (piece && piece.id !== undefined) {
      this.router.navigate(['/piece-details', piece.id]);
    } else {
      console.error('Attempted to navigate to piece details with undefined pieceId', piece);
    }
  }

    loadCart(): void {
      this.clientService.getCart().subscribe(
        (cartItems: CartItems[]) => {
          this.cart = cartItems.reduce((acc: { [key: number]: number }, item: CartItems) => {
            if (item.pieceId && item.pieceId) { // Ensure piece and its id exist
              acc[item.pieceId] = item.quantite; // Store pieceId as key and quantite as value
            }            return acc;
          }, {});
          this.updateCartItemCount();
        },
        (error) => {
          console.error('Error fetching cart:', error);
        }
      );
    }

  getUserIdAndPanier() {
    

    this.authService.getCurrentUserId().subscribe(
      (userId) => {
        this.userId = userId;
        if (this.userId) {
          this.getPanierId();
        }
      },
      (error) => console.error('Error getting user ID:', error)
    );
  }

  getPanierId() {
    if (this.userId) {
      this.cartService.getPanierIdByUserId(this.userId).subscribe(
        (panierId) => {
          this.panierId = panierId;
          this.updateCartItemCount();
        },
        (error) => console.error('Error getting panier ID:', error)
      );
    }
  }

  updateCartItemCount() {
    if (this.panierId) {
      this.cartService.getCartItemCount(this.panierId).subscribe(
        (count) => this.cartItemCount = count,
        (error) => console.error('Error getting cart item count:', error)
      );
    }
  }

  addToCart(pieceId: number): void {
    if (this.panierId) {
      this.cartService.addToCart(this.panierId, pieceId, 1).subscribe(
        () => {
          this.cart[pieceId] = (this.cart[pieceId] || 0) + 1;
          this.updateCartItemCount();
        },
        (error) => {
          console.error('Error adding to cart:', error);
        }
      );
    } else {
      console.error('Panier ID is not available');
    }
  }

  getCartQuantity(pieceId: number): number {
    return this.cart[pieceId] || 0;
  }
}