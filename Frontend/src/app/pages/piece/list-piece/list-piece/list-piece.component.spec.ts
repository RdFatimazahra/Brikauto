import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPieceComponent } from './list-piece.component';

describe('ListPieceComponent', () => {
  let component: ListPieceComponent;
  let fixture: ComponentFixture<ListPieceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListPieceComponent]
    });
    fixture = TestBed.createComponent(ListPieceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
