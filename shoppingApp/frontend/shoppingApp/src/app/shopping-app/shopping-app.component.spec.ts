import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingAppComponent } from './shopping-app.component';

describe('ShoppingAppComponent', () => {
  let component: ShoppingAppComponent;
  let fixture: ComponentFixture<ShoppingAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShoppingAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
