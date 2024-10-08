import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';
import { CartService } from 'src/app/services/cart.service';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';

@Component({
  selector: 'app-piece-details',
  templateUrl: './piece-details.component.html',
  styleUrls: ['./piece-details.component.scss'],
})
export class PieceDetailsComponent implements OnInit {
  piece: Piece | null = null;
  panierId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private cartService: CartService,
    private authService: AuthenticateService
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

  // Load the Panier ID for the current user
  loadPanierId(): void {
    const userId = this.authService.getId(); // Get the user ID directly
    if (userId) {
      this.cartService.getPanierIdByUserId(userId).subscribe(
        (panierId) => {
          this.panierId = panierId;
        },
        (error) => {
          console.error('Error fetching panier ID:', error);
        }
      );
    } else {
      console.error('User ID not found.');
    }
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
    } else {
      console.error('Panier ID is not available.');
    }
  }
}
