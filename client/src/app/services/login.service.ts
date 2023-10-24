import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private AUTH_URL = 'http://localhost:8080/api/v1/auth/login';

  constructor(private http: HttpClient) { }

  loginUser(loginObj: Object): Observable<any> {
    return this.http.post(this.AUTH_URL, loginObj);
  }
}
