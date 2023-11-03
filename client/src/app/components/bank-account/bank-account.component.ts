import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddBankAccountComponent } from '../dialogs/add-bankaccount/add-bankaccount.component';
import { UpdateBankaccountComponent } from '../dialogs/update-bankaccount/update-bankaccount.component';
import { RemoveBankaccountComponent } from '../dialogs/remove-bankaccount/remove-bankaccount.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddTransactionComponent } from '../dialogs/add-transaction/add-transaction.component';
import { UpdateTransactionComponent } from '../dialogs/update-transaction/update-transaction.component';
import { MatAccordion } from '@angular/material/expansion';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';
import { Transaction } from 'src/app/types/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { ShowTransactionsComponent } from '../dialogs/show-transactions/show-transactions.component';
import { AddExpenseTransactionComponent } from '../dialogs/add-expense-transaction/add-expense-transaction.component';
import { AddIncomeTransactionComponent } from '../dialogs/add-income-transaction/add-income-transaction.component';

@Component({
  selector: 'app-test-split-screen',
  templateUrl: './bank-account.component.html',
  styleUrls: ['./bank-account.component.css']
})
export class BankAccountComponent implements OnInit {
  bankAccounts!: BankAccount[];

  constructor(private bankAccountService: BankAccountService,
    private transactionService: TransactionService,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getBankAccounts();
  }

  getBankAccounts() {
    this.bankAccountService.getBankAccounts().subscribe({
      next: res => {
        this.bankAccounts = res;
      },
      error: err => {
        console.log(err);
      }
    })
  }

  openAddBankAccountDialog() {
    const dialogRef = this.dialog.open(AddBankAccountComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.openSnackBar("Account successfully added!");
        this.ngOnInit();
      }
    });
  }

  // openUpdateBankAccountDialog() {
  //   const dialogRef = this.dialog.open(UpdateBankaccountComponent);
  // }
  openRemoveBankAccountDialog() {
    const dialogRef = this.dialog.open(RemoveBankaccountComponent);
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

  openTransactionsDialog(transactions: Transaction[]) {
    const dialogRef = this.dialog.open(ShowTransactionsComponent, { data: transactions });
  }

  openExpenseTransactionDialog(bankAccountId: number) {
    const dialogRef = this.dialog.open(AddExpenseTransactionComponent, { data: bankAccountId });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== true || result !== false) {
        this.openSnackBar(result);
      }
      if (result === true) {
        this.openSnackBar("Transaction successfully added!");
        this.ngOnInit();
      }
    });
  }

  openIncomeTransactionDialog(bankAccountId: number) {
    const dialogRef = this.dialog.open(AddIncomeTransactionComponent, { data: bankAccountId });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.openSnackBar("Transaction successfully added!");
        this.ngOnInit();
      } else {

      }
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, { duration: 4000 });
  }

  @ViewChild(MatAccordion) accordion!: MatAccordion;
}
