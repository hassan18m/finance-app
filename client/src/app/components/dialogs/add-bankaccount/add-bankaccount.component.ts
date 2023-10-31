import { Component, inject } from '@angular/core';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { SnackbarComponent } from './snackbar/snackbar.component';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-bankaccount.component.html',
  styleUrls: ['./add-bankaccount.component.css']
})
export class AddBankAccountComponent {

  bankAccountReq: any = {
    bankName: '',
    accountType: '',
    currency: ''
  }

  constructor(private bankAccountService: BankAccountService,
    private _snackBar: MatSnackBar) { }

  addBankAccount() {
    this.bankAccountService.addBankAccount(this.bankAccountReq).subscribe({
      next: res => {
        this.openSnackBar();
        console.log(res);
      },
      error: err => {
        console.log(err);
      }
    });
  }

  durationInSeconds = 3;

  openSnackBar() {
    this._snackBar.openFromComponent(SnackbarComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
}
