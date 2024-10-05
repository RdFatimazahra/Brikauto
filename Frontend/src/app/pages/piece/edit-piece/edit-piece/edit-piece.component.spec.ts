import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPieceComponent } from './edit-piece.component';

describe('EditPieceComponent', () => {
  let component: EditPieceComponent;
  let fixture: ComponentFixture<EditPieceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditPieceComponent]
    });
    fixture = TestBed.createComponent(EditPieceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
