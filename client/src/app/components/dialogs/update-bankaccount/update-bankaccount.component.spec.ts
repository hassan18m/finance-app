import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBankaccountComponent } from './update-bankaccount.component';

describe('UpdateBankaccountComponent', () => {
  let component: UpdateBankaccountComponent;
  let fixture: ComponentFixture<UpdateBankaccountComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateBankaccountComponent]
    });
    fixture = TestBed.createComponent(UpdateBankaccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
