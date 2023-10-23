import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BankAccount } from '../types/bank-account';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BankAccountService {

  private url: string = "http://localhost:8080/api/v1/bank-accounts";

  constructor(private http: HttpClient) { }

  getBankAccounts():Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(this.url);
  }
}
