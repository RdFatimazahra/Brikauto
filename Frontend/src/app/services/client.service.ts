import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    const headers = this.createAuthorizationHeader();
    return this.http.get<Piece[]>(`${this.apiUrl}/show`, { headers });
  }

  // Get piece details
  getPieceDetails(pieceId: number): Observable<Piece> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<Piece>(`${this.apiUrl}/details/${pieceId}`, { headers });
  }
  
  // Add a piece to the wishlist
  addToWishlist(pieceId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post(`${this.apiUrl}/wishlist/add`, { pieceId }, { headers });
  }

  // Remove a piece from the wishlist
  removeFromWishlist(pieceId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.delete(`${this.apiUrl}/wishlist/remove/${pieceId}`, { headers });
  }

  // Get the user's wishlist
  getWishlist(): Observable<Piece[]> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<Piece[]>(`${this.apiUrl}/wishlist`, { headers });
  }

  // Add a piece to the cart
  addToCart(pieceId: number, quantity: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post(`${this.apiUrl}/cart/add`, { pieceId, quantity }, { headers });
  }

  // Remove a piece from the cart
  removeFromCart(pieceId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.delete(`${this.apiUrl}/cart/remove/${pieceId}`, { headers });
  }

  // Get the user's cart
  getCart(): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.get(`${this.apiUrl}/cart`, { headers });
  }

  // Place an order
  placeOrder(orderData: any): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post(`${this.apiUrl}/order`, orderData, { headers });
  }

  // Create the Authorization header with JWT
  private createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt'); // Assuming you store the JWT in localStorage
    if (jwtToken) {
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.error("JWT token not found in localStorage");
      return new HttpHeaders();
    }
  }
}
