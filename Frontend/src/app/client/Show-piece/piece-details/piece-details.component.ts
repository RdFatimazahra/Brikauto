import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';
import { CartService } from 'src/app/services/cart.service';
import { AuthenticateService } from 'src/app/services/authenticate-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-piece-details',
  templateUrl: './piece-details.component.html',
  styleUrls: ['./piece-details.component.scss'],
})
export class PieceDetailsComponent implements OnInit {
  piece: Piece | null = null;
  panierId: number | null = null;
  isProductInPanier: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private cartService: CartService,
    private authService: AuthenticateService,
    private router:Router
  ) {}

  ngOnInit(): void {
    const pieceId = this.route.snapshot.paramMap.get('id');
    if (pieceId) {
      this.loadPieceDetails(+pieceId);
    }

    const userId = this.authService.getId(); // Get user ID once
    if (userId) {
      this.getPanierByUserId(userId); // Use new method to load panier ID

      if (pieceId) {
        // Once the panier ID is loaded, check if the piece is in the panier
        this.checkPanierOnLoad(userId, +pieceId);
      }
    } else {
      console.error('User ID not found.');
    }
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

  // Load Panier ID by user ID
  getPanierByUserId(userId: number): void {
    this.cartService.getPanierIdByUserId(userId).subscribe(
      (panierId) => {
        this.panierId = panierId;
        console.log('Fetched panier ID:', panierId);
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
          // Optionally update UI or emit event
        },
        (error) => {
          console.error('Error adding to cart:', error);
        }
      );
    } else {
      console.error('Panier ID is not available.');
    }
  }

  checkPanierOnLoad(userId: number, pieceId: number): void {
    this.cartService.getPanierIdByUserId(userId).subscribe(
      (panierId) => {
        this.panierId = panierId;
        if (panierId) {
          this.checkPanier(panierId, pieceId);
        }
      },
      (error) => {
        console.error('Error fetching panier ID:', error);
      }
    );
  }

  checkPanier(panierId: number, pieceId: number): void {
    this.cartService.isProductInPanier(panierId, pieceId).subscribe(
      (isInPanier: boolean) => {
        this.isProductInPanier = isInPanier;
        console.log(`Is the piece in the panier: ${isInPanier}`);
      },
      (error) => {
        console.error('Error checking if piece is in panier:', error);
      }
    );
  }



  goToCart(){
      this.router.navigate(['/cart'])
  }


}
