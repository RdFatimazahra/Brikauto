import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-piece-details',
  templateUrl: './piece-details.component.html',
  styleUrls: ['./piece-details.component.scss']
})
export class PieceDetailsComponent implements OnInit {
  piece: Piece | null = null;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService
  ) { }

  ngOnInit(): void {
    const pieceId = this.route.snapshot.paramMap.get('id');
    if (pieceId) {
      this.loadPieceDetails(+pieceId);
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

  addToCart(pieceId: number): void {
    this.clientService.addToCart(pieceId, 1).subscribe(
      () => {
        console.log('Piece added to cart');
      },
      (error) => {
        console.error('Error adding to cart:', error);
      }
    );
  }
}