import { Injectable } from '@angular/core';
import { BankAccountService } from './bank-account.service';
import { Observable } from 'rxjs';
import { Transaction } from '../types/transaction';
import { HttpClient } from '@angular/common/http';
import { ExpenseTransactionRequest } from '../types/expense-transaction-request';
import { IncomeTransactionRequest } from '../types/income-transaction-request';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  transactionsURL: string = 'http://localhost:8080/api/v1/transactions/'

  constructor(private http: HttpClient) { }


  deleteTransaction(transactionId: number) {
    const transactionToDeleteURL = this.transactionsURL + transactionId;
    return this.http.delete(transactionToDeleteURL, { withCredentials: true });
  }

  addExpenseTransaction(transactionReq: ExpenseTransactionRequest, bankAccountId: number) {
    const expenseTransactionURL: string = `http://localhost:8080/api/v1/bank-accounts/${bankAccountId}/transactions/expense`;
    return this.http.post(expenseTransactionURL, transactionReq, { withCredentials: true });
  }

  addIncomeTransaction(transactionReq: IncomeTransactionRequest, bankAccountId: number) {
    const expenseTransactionURL: string = `http://localhost:8080/api/v1/bank-accounts/${bankAccountId}/transactions/income`;
    return this.http.post(expenseTransactionURL, transactionReq, { withCredentials: true });
  }
}
