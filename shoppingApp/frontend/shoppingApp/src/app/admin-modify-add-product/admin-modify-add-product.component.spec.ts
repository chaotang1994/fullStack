import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminModifyAddProductComponent } from './admin-modify-add-product.component';

describe('AdminModifyAddProductComponent', () => {
  let component: AdminModifyAddProductComponent;
  let fixture: ComponentFixture<AdminModifyAddProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminModifyAddProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminModifyAddProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
