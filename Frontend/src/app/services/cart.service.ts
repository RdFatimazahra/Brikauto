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
 /////////////////
 private baseUrl = 'http://localhost:8082/api/cart'; // Replace with your backend URL

 constructor(private http: HttpClient) {}

 // Add to Cart API
 addToCart(panierId: number, pieceId: number, quantity: number): Observable<any> {
   const url = `${this.baseUrl}/add/${panierId}/${pieceId}/${quantity}`;
   return this.http.post<any>(url, {});
 }

 // Get Panier ID by User ID
 getPanierIdByUserId(userId: number): Observable<number> {
   const url = `${this.baseUrl}/user/${userId}`;
   return this.http.post<number>(url, {});
 }
 ////////////////

 //Old functions//////

  getCart(): Observable<CartItem[]> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<CartItem[]>(`${this.baseUrl}/cart`, { headers });
  }





  removeFromCart(pieceId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.delete(`${this.baseUrl}/cart/remove/${pieceId}`, { headers });
  }

  updateQuantity(pieceId: number, quantity: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.put(`${this.baseUrl}/cart/update`, { pieceId, quantity }, { headers });
  }

  getItemQuantity(pieceId: number): Observable<number> {
    return new Observable(observer => {
      this.getCart().subscribe(
        (cartItems) => {
          const item = cartItems.find(item => item.id === pieceId);
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
    return this.http.post(`${this.baseUrl}/order`, orderData, { headers });
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