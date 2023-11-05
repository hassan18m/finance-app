import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TransactionService } from 'src/app/services/transaction.service';
import { Transaction } from 'src/app/types/transaction';
import { UpdateTransactionComponent } from '../update-transaction/update-transaction.component';

@Component({
  selector: 'app-show-transactions',
  templateUrl: './show-transactions.component.html',
  styleUrls: ['./show-transactions.component.css']
})

export class ShowTransactionsComponent implements OnInit {

  ngOnInit(): void { }

  constructor(@Inject(MAT_DIALOG_DATA) public data: Transaction[],
    private transactionService: TransactionService,
    private _snackBar: MatSnackBar,
    private dialog: MatDialog) { }

  bankAccountTransactions: Transaction[] = this.data;

  deleteTransaction(transactionId: number): void {
    this.transactionService.deleteTransaction(transactionId).subscribe({
      next: res => {
        const transactionIndex = this.returnIndexOfTransaction(transactionId);
        this.bankAccountTransactions.splice(transactionIndex, 1);
        this.openSnackBar('Transaction deleted successfully!');
      },
      error: err => {
        this.openSnackBar('Error deleting transaction!');
      }
    })
  }

  updateTransaction(transaction: any) {
    const dialogRef = this.dialog.open(UpdateTransactionComponent, { data: transaction });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Transaction updated successfully!') {
        window.location.reload()
        this.openSnackBar(result);
      }
      if (result === 'Error updating transaction, try again!') {
        this.openSnackBar(result);
      }
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, { duration: 4000 });
  }

  private returnIndexOfTransaction(transactionId: number): number {
    let index = 0;
    this.bankAccountTransactions.forEach(transaction => {
      if (transaction.id === transactionId) {
        index = this.bankAccountTransactions.indexOf(transaction);
      }
    });
    return index;
  }
}

