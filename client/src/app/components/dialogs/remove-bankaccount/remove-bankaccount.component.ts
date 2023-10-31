import { Component, OnInit, inject } from '@angular/core';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';

@Component({
  selector: 'app-remove-bankaccount',
  templateUrl: './remove-bankaccount.component.html',
  styleUrls: ['./remove-bankaccount.component.css']
})
export class RemoveBankaccountComponent implements OnInit {

  userBankAccounts!: BankAccount[];
  bankAccountToDeleteId!: number;

  constructor(private bankAccountService: BankAccountService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getBankAccounts();
  }

  getBankAccounts() {
    this.bankAccountService.getBankAccounts().subscribe({
      next: res => {
        this.userBankAccounts = res;
      },
      error: err => {
        console.log(err);
      }
    });
  }

  getResult(bankAccountId: number) {
    this.bankAccountToDeleteId = bankAccountId;
    console.log(this.bankAccountToDeleteId);
  }
  // TODO: after pressing "DELETE BUTTON" close the dialog and when opening the dialog again: update data.

  durationInSeconds = 5;

  openSnackBar() {
    this._snackBar.openFromComponent(RemoveBankaccountComponent, {
      duration: this.durationInSeconds * 1000,
    });
  }
  snackBarRef = inject(MatSnackBarRef);
}
