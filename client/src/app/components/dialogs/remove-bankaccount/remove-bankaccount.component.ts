import { Component, OnInit, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';

@Component({
  selector: 'app-remove-bankaccount',
  templateUrl: './remove-bankaccount.component.html',
  styleUrls: ['./remove-bankaccount.component.css']
})
export class RemoveBankaccountComponent implements OnInit {
  userBankAccounts!: BankAccount[];

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

  deleteBankAccount(bankAccountId: number): void {
    this.bankAccountService.deleteBankAccount(bankAccountId.toString()).subscribe({
      next: res => {
        this.openSnackBar('Account successfully deleted!');
        this.ngOnInit();
      },
      error: err => {
        this.openSnackBar('There was an error deleting account!');
      }
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, { duration: 4000 });
  }
}
