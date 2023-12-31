import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BankAccount } from '../types/bank-account';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { User } from '../types/user';

@Injectable({
  providedIn: 'root',
})
export class BankAccountService {
  nonUser!: User;

  private bankAccountURL: string =
    'http://localhost:8080/api/v1/bank-accounts/';
  private userId: string = this.storageService.getUser().id;
  private getBankAccountsURL: string = `http://localhost:8080/api/v1/bank-accounts/user/${this.userId}`;
  private addBankAccountURL: string = `http://localhost:8080/api/v1/users/${this.userId}/add-bank-account`;

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  isLoggedIn(): boolean {
    return this.storageService.isLoggedIn();
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
    return this.http.get<BankAccount[]>(this.getBankAccountsURL, {
      withCredentials: true,
    });
  }

  addBankAccount(bankAccountReq: any): Observable<BankAccount> {
    if (this.isLoggedIn()) {
      return this.http.post<BankAccount>(
        this.addBankAccountURL,
        bankAccountReq,
        { withCredentials: true }
      );
    }

    return new Observable();
  }

  deleteBankAccount(bankAccountId: string) {
    const deleteURL = this.bankAccountURL + bankAccountId;
    return this.http.delete<BankAccount>(deleteURL, { withCredentials: true });
  }

  updateBankAccount(
    bankAccountId: number,
    bankAccountUpdate: any
  ): Observable<BankAccount> {
    const updateURL: string = this.bankAccountURL + 'update/' + bankAccountId;
    return this.http.patch<BankAccount>(updateURL, bankAccountUpdate, {
      withCredentials: true,
    });
  }

  getBalanceOfBankAccount(bankAccountId: number): Observable<number> {
    const bankBalanceURL = this.bankAccountURL + bankAccountId + '/balance';
    return this.http.get<number>(bankBalanceURL, { withCredentials: true });
  }

  getBankAccountById(bankAccountId: number): Observable<BankAccount> {
    const bankAccountByIdURL = this.bankAccountURL + `get/${bankAccountId}`;
    return this.http.get<BankAccount>(bankAccountByIdURL, {
      withCredentials: true,
    });
  }
}
