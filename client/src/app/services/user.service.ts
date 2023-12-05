import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { Content } from '../components/home/content';

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  accountUrl: string = 'http://localhost:8080/api/v1/users/';
  budgetUrl: string = `http://localhost:8080/api/v1/users/${this.userId()}/totalBalance`;

  userId(): string {
    if (this.storageService.isLoggedIn()) {
      return this.storageService.getUser().id;
    }
    return '';
  }

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  getPublicContent(): Observable<Content> {
    return this.http.get<Content>(API_URL + 'all');
  }

  getUserBoard(): Observable<any> {
    return this.http.get(this.accountUrl + this.userId(), {
      withCredentials: true,
    });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', {
      responseType: 'text',
      withCredentials: true,
    });
  }

  getTotalBalance(): Observable<any> {
    return this.http.get(this.budgetUrl, { withCredentials: true });
  }
}
