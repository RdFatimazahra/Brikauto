import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Piece } from '../interfaces/Piece';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = 'http://localhost:8082/api/v1/User/pieces'; // Adjust this URL as needed

  constructor(private http: HttpClient) { }

  // Get all pieces
  getAllPieces(): Observable<Piece[]> {
    return this.http.get<Piece[]>(`${this.apiUrl}/show`);
  }

  // Add a piece to the wishlist
  addToWishlist(pieceId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/wishlist/add`, { pieceId });
  }

  // Remove a piece from the wishlist
  removeFromWishlist(pieceId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/wishlist/remove/${pieceId}`);
  }

  // Get the user's wishlist
  getWishlist(): Observable<Piece[]> {
    return this.http.get<Piece[]>(`${this.apiUrl}/wishlist`);
  }

  // Add a piece to the cart
  addToCart(pieceId: number, quantity: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/cart/add`, { pieceId, quantity });
  }

  // Remove a piece from the cart
  removeFromCart(pieceId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/cart/remove/${pieceId}`);
  }

  // Get the user's cart
  getCart(): Observable<any> {
    return this.http.get(`${this.apiUrl}/cart`);
  }

  // Place an order
  placeOrder(orderData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/order`, orderData);
  }
}