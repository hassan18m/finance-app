import { TemplatePortal } from '@angular/cdk/portal';
import { TemplateBinding } from '@angular/compiler';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TransactionService } from 'src/app/services/transaction.service';
import { Transaction } from 'src/app/types/transaction';

@Component({
  selector: 'app-show-transactions',
  templateUrl: './show-transactions.component.html',
  styleUrls: ['./show-transactions.component.css']
})

export class ShowTransactionsComponent implements OnInit {

  ngOnInit(): void { }

  constructor(@Inject(MAT_DIALOG_DATA) public data: Transaction[],
    private transactionService: TransactionService) { }

  bankAccountTransactions: Transaction[] = this.data;

  deleteTransaction(transactionId: number): void {
    this.transactionService.deleteTransaction(transactionId).subscribe({
      next: res => {
        const transactionIndex = this.returnIndexOfTransaction(transactionId);
        this.bankAccountTransactions.splice(transactionIndex, 1);
        console.log(this.bankAccountTransactions);
        this.ngOnInit();
        console.log(res);
      },
      error: err => {
        console.log(err);
      }
    })
  }

  returnIndexOfTransaction(transactionId: number): number {
    let index = 0;
    this.bankAccountTransactions.forEach(transaction => {
      if (transaction.id === transactionId) {
        index = this.bankAccountTransactions.indexOf(transaction);
      }
    });

    return index;
  }
}

