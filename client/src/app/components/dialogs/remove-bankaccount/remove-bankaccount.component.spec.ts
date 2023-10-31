import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveBankaccountComponent } from './remove-bankaccount.component';

describe('RemoveBankaccountComponent', () => {
  let component: RemoveBankaccountComponent;
  let fixture: ComponentFixture<RemoveBankaccountComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RemoveBankaccountComponent]
    });
    fixture = TestBed.createComponent(RemoveBankaccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
