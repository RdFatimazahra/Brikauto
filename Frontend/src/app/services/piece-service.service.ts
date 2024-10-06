import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Piece } from '../interfaces/Piece';

const BASE_URL = 'http://localhost:8082/api/v1/Admin/pieces';

@Injectable({
  providedIn: 'root'
})
export class ListPieceService {

  constructor(private http: HttpClient) { }

  // Création d'une nouvelle pièce
  createPiece(piece: Piece): Observable<Piece> {
    return this.http.post<Piece>(`${BASE_URL}/add`, piece);
  }

  // Suppression d'une pièce par ID
  deletePiece(id: number): Observable<void> {
    const headers = this.createAuthorizationHeader();
    return this.http.delete<void>(`${BASE_URL}/${id}`, { headers });
  }

  // Mise à jour d'une pièce
  updatePiece(piece: Piece, idPiece: number): Observable<Piece> {
    const headers = this.createAuthorizationHeader();
    return this.http.put<Piece>(`${BASE_URL}/${idPiece}`, piece, { headers });
  }

  // Récupération d'une pièce par ID
  getPieceById(idPiece: number): Observable<Piece> {
    // const headers = this.createAuthorizationHeader();
    return this.http.get<Piece>(`${BASE_URL}/${idPiece}`);
  }

  // Récupération de la liste de toutes les pièces
  getPieceList(): Observable<Piece[]> {
    // const headers = this.createAuthorizationHeader();
    return this.http.get<Piece[]>(`${BASE_URL}`);
  }

  // Création de l'en-tête d'autorisation avec JWT
  private createAuthorizationHeader(): HttpHeaders | undefined {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.error("JWT token non trouvé dans le localStorage");
      return undefined;
    }
  }
}
