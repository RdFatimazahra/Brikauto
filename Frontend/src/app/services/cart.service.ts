import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Piece } from '../interfaces/Piece';
import { CartItems } from '../interfaces/CartItems';

export interface CartItem extends Piece {
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
 /////////////////
 private baseUrl = 'http://localhost:8082/api/v1/User/cart'; // Replace with your backend URL

 constructor(private http: HttpClient) {}

 // Add to Cart API
 addToCart(panierId: number, pieceId: number, quantity: number): Observable<any> {
  const headers = this.createAuthorizationHeader();
   const url = `${this.baseUrl}/add/${panierId}/${pieceId}/${quantity}`;
   return this.http.post<any>(url, {headers});
 }

 // Get Panier ID by User ID
 getPanierIdByUserId(userId: number): Observable<number> {
  const headers = this.createAuthorizationHeader();
   const url = `${this.baseUrl}/user/${userId}`;
   return this.http.post<number>(url, {headers});
 }

 // Add the new method in CartService to call the API

getCartItemCount(panierId: number): Observable<number> {
  const headers = this.createAuthorizationHeader();
  const url = `${this.baseUrl}/count/${panierId}`;
  return this.http.get<number>(url, { headers });
}

//getPanierItems 

getPanierItemsByUtilisateurId(userId: number): Observable<CartItems[]> {
  const url = `http://localhost:8082/api/v1/User/cart/items/${userId}`;
  console.log('Fetching cart items from:', url); // Log URL for debugging
  return this.http.get<CartItems[]>(url);
}

 // Increment Quantity API
 incrementQuantity(panierId: number, pieceId: number): Observable<any> {
  const headers = this.createAuthorizationHeader();
  const url = `${this.baseUrl}/increment/${panierId}/${pieceId}`;
  return this.http.post<any>(url, { headers });
}

// Decrement Quantity API
decrementQuantity(panierId: number, pieceId: number): Observable<any> {
  const headers = this.createAuthorizationHeader();
  const url = `${this.baseUrl}/decrement/${panierId}/${pieceId}`;
  return this.http.post<any>(url, { headers });
}



// getPanierItemsByUtilisateurId(userId: number): Observable<CartItems[]> {
//   return this.http.get<CartItems[]>(`/api/v1/User/cart/items/${userId}`);
// }
 /////////////////////////////////////////////////////////////////////////////////////////

 ////////////////////////////////////Old functions////////////////////////////////////////

  // getCart(): Observable<CartItem[]> {
  //   const headers = this.createAuthorizationHeader();
  //   return this.http.get<CartItem[]>(`${this.baseUrl}/cart`, { headers });
  // }





  // removeFromCart(pieceId: number): Observable<any> {
  //   const headers = this.createAuthorizationHeader();
  //   return this.http.delete(`${this.baseUrl}/cart/remove/${pieceId}`, { headers });
  // }

  // updateQuantity(pieceId: number, quantity: number): Observable<any> {
  //   const headers = this.createAuthorizationHeader();
  //   return this.http.put(`${this.baseUrl}/cart/update`, { pieceId, quantity }, { headers });
  // }

  // getItemQuantity(pieceId: number): Observable<number> {
  //   return new Observable(observer => {
  //     this.getCart().subscribe(
  //       (cartItems) => {
  //         const item = cartItems.find(item => item.id === pieceId);
  //         observer.next(item ? item.quantity : 0);
  //         observer.complete();
  //       },
  //       (error) => observer.error(error)
  //     );
  //   });
  // }

  // getTotal(): Observable<number> {
  //   return new Observable(observer => {
  //     this.getCart().subscribe(
  //       (cartItems) => {
  //         const total = cartItems.reduce((sum, item) => sum + item.prix * item.quantity, 0);
  //         observer.next(total);
  //         observer.complete();
  //       },
  //       (error) => observer.error(error)
  //     );
  //   });
  // }

  // placeOrder(orderData: any): Observable<any> {
  //   const headers = this.createAuthorizationHeader();
  //   return this.http.post(`${this.baseUrl}/order`, orderData, { headers });
  // }

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