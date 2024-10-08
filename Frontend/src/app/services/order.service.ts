import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  readonly api = 'http://localhost:8082/api/orders/place';

  constructor(
    private http: HttpClient
  ) { }

  placeOrder(userId: number): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.post<any>(`${this.api}/${userId}`, null, { headers }); // Add headers here
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
