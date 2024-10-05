import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Jwt } from '../interfaces/Jwt';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { users } from '../interfaces/users';


const BASE_URL  = ["http://localhost:8082/api/v1/auth/"]

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  private readonly TOKEN_KEY = 'jwt_token'

  constructor(private http: HttpClient,private jwtHelper:JwtHelperService,  private router: Router) { }

  registerFournisseur(signRequest: any): Observable<Jwt> {
    return this.http.post<Jwt>(BASE_URL + 'registerFournisseur', signRequest);
  }

  login(loginRequest: any): Observable<Jwt> {
    return this.http.post<Jwt>(BASE_URL + 'authenticate', loginRequest);
  }

  getUserCount(): Observable<number> {
    return this.http.get<number>(BASE_URL + 'Admin/count');
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }


  getAllUser(): Observable<users[]> {
    return this.http.get<users[]>(`${BASE_URL}Admin/AllUsers`);
  }

 

  getUserRole(): string {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.role; 
    }
    return '';
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigate(['/login']);  // Redirect to login page after logout
  }


}

