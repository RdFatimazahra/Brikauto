import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ListPieceService } from 'src/app/services/piece-service.service';

@Component({
  selector: 'app-piece-list',
  templateUrl: './list-piece.component.html',
  styleUrls: ['./list-piece.component.scss']
})
export class PieceListComponent implements OnInit {

  pieceList: Piece[] = [];

  constructor(private pieceService: ListPieceService, private router: Router) {}

  ngOnInit(): void {
    this.loadPieces();
  }

  // Chargement de la liste des pièces
  private loadPieces(): void {
    this.pieceService.getPieceList().subscribe(
      (data: Piece[]) => {
        console.log('Liste des pièces récupérées :', data);
        this.pieceList = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération de la liste des pièces', error);
      }
    );
  }

  // Méthode pour mettre à jour une pièce
  updatePiece(idPiece: number): void {
    this.router.navigate(['/edit-piece', idPiece]); // Redirection vers la page de mise à jour
  }

  // Méthode pour supprimer une pièce
  deletePiece(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette pièce ?')) {
      this.pieceService.deletePiece(id).subscribe(
        () => {
          this.pieceList = this.pieceList.filter(piece => piece.idPiece !== id); // Mise à jour de la liste
          alert('Pièce supprimée avec succès');
        },
        (error) => {
          alert('Erreur lors de la suppression de la pièce');
          console.error('Erreur lors de la suppression de la pièce', error);
        }
      );
    }
  }
}
