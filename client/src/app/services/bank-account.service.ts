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

  constructor(private http: HttpClient, private storageSerivce: StorageService) { }

  isLoggedIn(): boolean {
    return this.storageSerivce.isLoggedIn();
  }


  getBankAccounts(): Observable<User> {
    if (this.isLoggedIn()) {
      const userId: string = this.storageSerivce.getUser().id;
      const finalUrl: string = `${this.url}${userId}`;
      return this.http.get<User>(finalUrl,{withCredentials: true});
    }

    return new Observable;
  }
}
