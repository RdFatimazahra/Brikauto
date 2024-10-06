import { Component } from '@angular/core';


export interface Piece {
  idPiece: number;
  nom: string;
  reference: string;
  prix: number;
  quantite: number;
  image: string;
}
@Component({
  selector: 'app-show-piece',
  templateUrl: './show-piece.component.html',
  styleUrls: ['./show-piece.component.scss']
})
export class ShowPieceComponent {
  autoParts: Piece[] = [
    { idPiece: 1, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2, image: 'assets/oil-filter.png' },
    { idPiece: 2, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2, image: 'assets/brake-pads.png' },
    { idPiece: 3, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2, image: 'assets/spark-plugs.png' },
    { idPiece: 4, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2, image: 'assets/air-filter.png' },
    {idPiece: 5, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2, image: 'assets/alternator.png' },
    {idPiece: 6, nom: 'Oil Filter', reference: 'Ref125', prix: 12.99,quantite:2,image: 'assets/radiator.png' },
  ];

  wishlist: number[] = [];
  cart: { [key: number]: number } = {};

  addToWishlist(partId: number) {
    if (!this.wishlist.includes(partId)) {
      this.wishlist.push(partId);
    }
  }

  addToCart(partId: number) {
    this.cart[partId] = (this.cart[partId] || 0) + 1;
  }

  isInWishlist(partId: number): boolean {
    return this.wishlist.includes(partId);
  }

  getCartQuantity(partId: number): number {
    return this.cart[partId] || 0;
  }
}
