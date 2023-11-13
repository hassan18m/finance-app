import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveBudgetComponent } from './remove-budget.component';

describe('RemoveBudgetComponent', () => {
  let component: RemoveBudgetComponent;
  let fixture: ComponentFixture<RemoveBudgetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RemoveBudgetComponent]
    });
    fixture = TestBed.createComponent(RemoveBudgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
