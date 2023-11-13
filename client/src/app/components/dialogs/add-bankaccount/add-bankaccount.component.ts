import { Component } from '@angular/core';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-bankaccount.component.html',
  styleUrls: ['./add-bankaccount.component.css']
})

export class AddBankAccountComponent {

  addBankAccountReq: any = {
    bankName: "",
    accountType: "",
    currency: ""
  }

  form: FormGroup = new FormGroup({
    bankAccountName: new FormControl('', [Validators.required, Validators.minLength(3)]),
    accountType: new FormControl('', [Validators.required]),
    accountCurrency: new FormControl('', [Validators.required])
  });
  submitted: boolean = false;

  constructor(private bankAccountService: BankAccountService) { }

  addBankAccount() {
    if (this.submitted && !this.form.invalid) {
      this.addBankAccountReq.bankName = this.form.value.bankAccountName;
      this.addBankAccountReq.accountType = this.form.value.accountType;
      this.addBankAccountReq.currency = this.form.value.accountCurrency;

      this.bankAccountService.addBankAccount(this.addBankAccountReq).subscribe();
    }
  }

  onSubmit() {
    this.submitted = true;
  }
  getErrorMessage(): string {
    if (this.form.get('bankAccountName')?.hasError('minlength')) {
      return 'Minimum characters: 3';
    }
    return 'You must enter a value';
  }
}

