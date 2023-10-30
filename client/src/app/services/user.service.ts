import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  accountUrl: string = 'http://localhost:8080/api/v1/users/';
  budgetUrl: string = `http://localhost:8080/api/v1/users/${this.userId()}/totalBalance`;

  userId(): string {
    if (this.storageService.isLoggedIn()) {
      return this.storageService.getUser().id;
    }
    return "";
  }


  constructor(private http: HttpClient, private storageService: StorageService) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(this.accountUrl + this.userId(), { withCredentials: true });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  getTotalBalance(): Observable<any> {
    return this.http.get(this.budgetUrl, { withCredentials: true });
  }
}
