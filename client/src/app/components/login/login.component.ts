import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserAuth } from 'src/app/types/user-auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userLogin: UserAuth = {
    email: '',
    password: ''
  };

  constructor(private loginService: LoginService,
    private router: Router) { }

  onLogin() {
    debugger;
    this.loginService.loginUser(this.userLogin).subscribe({
      next: data => {
        this.router.navigate(['/']).then(() => window.location.reload());
        localStorage.setItem('loginToken', data.token);
      }
      ,
      error: err => console.log(err)
    });
  }
}
