import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';
import { CartService } from 'src/app/services/cart.service'; // Import CartService

@Component({
  selector: 'app-piece-details',
  templateUrl: './piece-details.component.html',
  styleUrls: ['./piece-details.component.scss'],
})
export class PieceDetailsComponent implements OnInit {
  piece: Piece | null = null;
  panierId: number | null = null; // Panier ID for the current user

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private cartService: CartService // Inject CartService
  ) {}

  ngOnInit(): void {
    const pieceId = this.route.snapshot.paramMap.get('id');
    if (pieceId) {
      this.loadPieceDetails(+pieceId);
    }
    this.loadPanierId(); // Load the panier ID when the component initializes
  }

  loadPieceDetails(pieceId: number): void {
    this.clientService.getPieceDetails(pieceId).subscribe(
      (piece) => {
        this.piece = piece;
      },
      (error) => {
        console.error('Error fetching piece details:', error);
      }
    );
  }

  // Load the Panier ID for the current user (assuming userId is 1 for demo purposes)
  loadPanierId(): void {
    const userId = 1; // Replace with actual userId based on your authentication system
    this.cartService.getPanierIdByUserId(userId).subscribe(
      (panierId) => {
        this.panierId = panierId;
      },
      (error) => {
        console.error('Error fetching panier ID:', error);
      }
    );
  }

  addToCart(pieceId: number): void {
    if (this.panierId) {
      this.cartService.addToCart(this.panierId, pieceId, 1).subscribe(
        () => {
          console.log('Piece added to cart');
          // Optionally, you could emit an event or update the cart count in the navbar
        },
        (error) => {
          console.error('Error adding to cart:', error);
        }
      );
    }
  }
}
