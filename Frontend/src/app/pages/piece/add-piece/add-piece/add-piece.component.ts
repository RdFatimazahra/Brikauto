import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { Piece } from 'src/app/interfaces/Piece';
import { ListPieceService } from 'src/app/services/piece-service.service';
import { UploadImageService } from 'src/app/services/upload-image.service';

@Component({
  selector: 'app-add-piece',
  templateUrl: './add-piece.component.html',
  styleUrls: ['./add-piece.component.scss']
})
export class AddPieceComponent {
  pieceForm: FormGroup;  // Use FormGroup
  imagePreviewUrl: string | null = null;

  constructor(
    private fb: FormBuilder,  // Inject FormBuilder
    private pieceService: ListPieceService,
    private uploadimage: UploadImageService,
    private router: Router
  ) {
    this.pieceForm = this.fb.group({
      prix: ['', Validators.required],
      nom: ['', Validators.required],
      reference: ['', Validators.required],
      quantite: [0, Validators.required],
      idFournisseur: [0, Validators.required],
      image: ['']  // Control for the image
    });
  }

 async onSubmit() {
    if (this.pieceForm.valid) {
      const pieceData = this.pieceForm.value;
      
      pieceData.image = await this.uploadimage.uploadImageToCloudinary(pieceData.image)

      this.pieceService.createPiece(pieceData).subscribe({
        next: async (response) => {
          console.log('Pièce créée avec succès', response);
          this.router.navigate(['dashboard/list-piece']); // Adjust the redirection path
        },
        error: (error) => {
          console.error('Erreur lors de la création de la pièce', error);
        }
      });
    }
  }

  onFileSelected(event: any, controlName: string): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/*/) != null) {
      const previewUrl = URL.createObjectURL(file);
      this.pieceForm.patchValue({ [controlName]: file });
      this.pieceForm.get(controlName)?.markAsTouched();
      this.imagePreviewUrl = previewUrl;
    } else {
      alert('Veuillez sélectionner une image valide');
    }
  }
}