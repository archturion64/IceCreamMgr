import { AppComponent } from "./app.component";
import { ComponentFixture, TestBed } from '@angular/core/testing';

describe('AppComponent', () => {
    let component: AppComponent;
    let fixture: ComponentFixture<AppComponent>;
  
    let serviceA: any;
    let serviceB: any;
  
    beforeEach(async () =>  {
        await TestBed.configureTestingModule({
            //(3)
            providers: [
            ],
          }).compileComponents();
          fixture = TestBed.createComponent(AppComponent);
          component = fixture.componentInstance;
    })
  
    it('should create', () => {
      expect(component).toBeTruthy()
    })
  })