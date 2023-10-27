import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginRequest } from '../types/user-login';
import { Observable, finalize } from 'rxjs';
import { UserRegisterRequest } from '../types/user-register-req';
import { StorageService } from './storage.service';

const AUTH_API = 'http://localhost:8080/api/v1/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  login(userLogin: UserLoginRequest): Observable<any> {
    const loginUrl = AUTH_API + 'signin';
    return this.http.post(loginUrl, userLogin, { withCredentials: true });
  }

  register(userRegister: UserRegisterRequest): Observable<any> {
    const registerUrl = AUTH_API + 'signup';
    return this.http.post(registerUrl, userRegister, httpOptions);
  }

  logout(): Observable<any> {
    const logoutUrl = AUTH_API + 'signout';
    return this.http.post(logoutUrl, null, { withCredentials: true });
  }
}
