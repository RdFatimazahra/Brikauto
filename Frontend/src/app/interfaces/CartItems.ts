import { Cart } from "./Cart"
import { Piece } from "./Piece"

export interface CartItems { 
  pieceImageLink: string
  pieceName: string
  piecePrice: number
  quantite: number
  totalPrice: number 
  panier: { idPanier: number; };
  pieceId: number
}