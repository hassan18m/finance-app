import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTransactionComponent } from './update-transaction.component';

describe('UpdateTransactionComponent', () => {
  let component: UpdateTransactionComponent;
  let fixture: ComponentFixture<UpdateTransactionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateTransactionComponent]
    });
    fixture = TestBed.createComponent(UpdateTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
