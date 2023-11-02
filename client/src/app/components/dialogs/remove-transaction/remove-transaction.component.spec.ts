import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveTransactionComponent } from './remove-transaction.component';

describe('RemoveTransactionComponent', () => {
  let component: RemoveTransactionComponent;
  let fixture: ComponentFixture<RemoveTransactionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RemoveTransactionComponent]
    });
    fixture = TestBed.createComponent(RemoveTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
