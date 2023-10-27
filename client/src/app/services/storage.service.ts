import { Injectable } from '@angular/core';
import { UserDetails } from '../types/user-details';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() { }

  clear(): void {
    window.sessionStorage.clear();
    window.localStorage.clear();
  }

  saveUser(user: any): void {
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  getUser(): UserDetails {
    const user = window.localStorage.getItem(USER_KEY);
    return user ? JSON.parse(user) : null;
  }

  removeUser(): void {
    window.localStorage.removeItem(USER_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getUser();
  }
}
