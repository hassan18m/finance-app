import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BankAccount } from '../types/bank-account';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { User } from '../types/user';

@Injectable({
  providedIn: 'root'
})

export class BankAccountService {

  nonUser!: User;

  private url: string = "http://localhost:8080/api/v1/users/";
  private userId: string = this.storageSerivce.getUser().id;
  private getBankAccountsURL: string = `http://localhost:8080/api/v1/bank-accounts/user/${this.userId}`;
  private addBankAccountURL: string = `http://localhost:8080/api/v1/users/${this.userId}/add-bank-account`;


  constructor(private http: HttpClient, private storageSerivce: StorageService) { }

  isLoggedIn(): boolean {
    return this.storageSerivce.isLoggedIn();
  }


  // getBankAccounts(): Observable<User> {
  //   if (this.isLoggedIn()) {
  //     const userId: string = this.storageSerivce.getUser().id;
  //     const finalUrl: string = `${this.url}${userId}`;
  //     return this.http.get<User>(finalUrl, { withCredentials: true });
  //   }

  //   return new Observable;
  // }

  getBankAccounts(): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(this.getBankAccountsURL, { withCredentials: true });
  }

  addBankAccount(bankAccount: BankAccount): Observable<BankAccount> {
    if (this.isLoggedIn()) {
      return this.http.post<BankAccount>(this.addBankAccountURL, bankAccount, { withCredentials: true });
    }

    return new Observable;
  }
}
