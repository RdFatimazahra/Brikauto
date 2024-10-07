import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Piece } from '../interfaces/Piece';

export interface CartItem extends Piece {
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8082/api/v1/User/pieces'; // Adjust this URL as needed
  private cartItems: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>([]);

  constructor(private http: HttpClient) { }

  getCart(): Observable<CartItem[]> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<CartItem[]>(`${this.apiUrl}/cart`, { headers });
  }

  addToCart(piece: Piece, quantity: number = 1): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post(`${this.apiUrl}/cart/add`, { pieceId: piece.idPiece, quantity }, { headers });
  }

  removeFromCart(pieceId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.delete(`${this.apiUrl}/cart/remove/${pieceId}`, { headers });
  }

  updateQuantity(pieceId: number, quantity: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.put(`${this.apiUrl}/cart/update`, { pieceId, quantity }, { headers });
  }

  getItemQuantity(pieceId: number): Observable<number> {
    return new Observable(observer => {
      this.getCart().subscribe(
        (cartItems) => {
          const item = cartItems.find(item => item.idPiece === pieceId);
          observer.next(item ? item.quantity : 0);
          observer.complete();
        },
        (error) => observer.error(error)
      );
    });
  }

  getTotal(): Observable<number> {
    return new Observable(observer => {
      this.getCart().subscribe(
        (cartItems) => {
          const total = cartItems.reduce((sum, item) => sum + item.prix * item.quantity, 0);
          observer.next(total);
          observer.complete();
        },
        (error) => observer.error(error)
      );
    });
  }

  placeOrder(orderData: any): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post(`${this.apiUrl}/order`, orderData, { headers });
  }

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