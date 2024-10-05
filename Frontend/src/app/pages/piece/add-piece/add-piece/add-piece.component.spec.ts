import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPieceComponent } from './add-piece.component';

describe('AddPieceComponent', () => {
  let component: AddPieceComponent;
  let fixture: ComponentFixture<AddPieceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddPieceComponent]
    });
    fixture = TestBed.createComponent(AddPieceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
