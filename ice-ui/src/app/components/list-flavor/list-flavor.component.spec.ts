import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFlavorComponent } from './list-flavor.component';

describe('ListFlavorComponent', () => {
  let component: ListFlavorComponent;
  let fixture: ComponentFixture<ListFlavorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListFlavorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListFlavorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
