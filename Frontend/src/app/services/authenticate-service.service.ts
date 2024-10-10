import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Jwt } from '../interfaces/Jwt';
import { Observable, of } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { users } from '../interfaces/users';


const BASE_URL  = ["http://localhost:8082/api/v1/auth/"]

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  // private readonly TOKEN_KEY = 'jwt_token'
  private readonly TOKEN_KEY = 'jwt';
  private readonly ROLE_KEY = 'role';


  constructor(private http: HttpClient,private jwtHelper:JwtHelperService,  private router: Router) { }

  register(signRequest: any): Observable<Jwt> {
    return this.http.post<Jwt>(BASE_URL + 'registerUser', signRequest);
  }

  // registerFournisseur(signRequest: any): Observable<Jwt> {
  //   return this.http.post<Jwt>(BASE_URL + 'registerFournisseur', signRequest);
  // }

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

  getCurrentUserId(): Observable<number | null> {
    const token = this.getToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return of(decodedToken.userId); // Assuming the user ID is stored in the token as 'userId'
    }
    return of(null);
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem('jwt');
    localStorage.removeItem(this.ROLE_KEY)
    this.router.navigate(['/login']); 
  }



public getId():number | null{
  const token = localStorage.getItem(this.TOKEN_KEY);
  if(token)
  {
    const decodeToken = this.jwtHelper.decodeToken(token);
    return decodeToken.id;
  }
  return null;
}



}

