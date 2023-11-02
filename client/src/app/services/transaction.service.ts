import { Injectable } from '@angular/core';
import { BankAccountService } from './bank-account.service';
import { Observable } from 'rxjs';
import { Transaction } from '../types/transaction';
import { HttpClient } from '@angular/common/http';

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
}
