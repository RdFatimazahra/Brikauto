import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderConfirmation } from '../interfaces/OrderConfirmation';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  readonly api = 'http://localhost:8082/api/orders/place';

  constructor(
    private http: HttpClient
  ) { }

  // placeOrder(userId: number): Observable<any> {
  //   const headers = this.createAuthorizationHeader();
  //   return this.http.post<any>(`${this.api}/${userId}`, null, { headers }); // Add headers here
  // }

  placeOrder(userId: number): Observable<string> {
    const headers = this.createAuthorizationHeader();
    return this.http.post<string>(`${this.api}/${userId}`, null, { headers, responseType: 'text' as 'json' });
  }
  
  

  //ConfirmOrder::
   // Method to get order confirmation details
   getOrderConfirmation(orderId: number): Observable<OrderConfirmation[]> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<OrderConfirmation[]>(`http://localhost:8082/api/orders/${orderId}/confirmation`, { headers });
  }
  
    // New method to get all orders
    getAllOrders(): Observable<OrderConfirmation[]> {
      const headers = this.createAuthorizationHeader();
      return this.http.get<OrderConfirmation[]>(`http://localhost:8082/api/orders/all`, { headers });
    }


  private createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.error("JWT token not found in localStorage");
      return new HttpHeaders();
    }
  }
}
