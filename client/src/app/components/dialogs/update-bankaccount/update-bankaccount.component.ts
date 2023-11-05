import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';

@Component({
  selector: 'app-update-bankaccount',
  templateUrl: './update-bankaccount.component.html',
  styleUrls: ['./update-bankaccount.component.css']
})
export class UpdateBankaccountComponent {

  updateBankAccountReq: any = {
    bankName: this.bankAccountToUpdate.bankName,
    accountType: this.bankAccountToUpdate.accountType,
    currency: this.bankAccountToUpdate.currency
  }

  form: FormGroup = new FormGroup({
    bankAccountName: new FormControl(''),
    accountType: new FormControl(this.bankAccountToUpdate.accountType),
    accountCurrency: new FormControl('')
  });

  constructor(private bankAccountService: BankAccountService,
    @Inject(MAT_DIALOG_DATA) public bankAccountToUpdate: BankAccount,
    public dialogRef: MatDialogRef<UpdateBankaccountComponent>) { }

  updateBankAccount() {
    this.updateBankAccountReq.bankName = this.form.value.bankAccountName;
    this.updateBankAccountReq.accountType = this.form.value.accountType;
    this.updateBankAccountReq.currency = this.form.value.accountCurrency;

    this.bankAccountService.updateBankAccount(this.bankAccountToUpdate.id, this.updateBankAccountReq)
      .subscribe({
        next: data => {
          this.dialogRef.close('Account updated successfully!');
        },
        error: err => {
          this.dialogRef.close('Error updating account, try again!');
        }
      });
  }
}
