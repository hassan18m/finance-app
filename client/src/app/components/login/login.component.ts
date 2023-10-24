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

  userLogin!: UserAuth;

  constructor(private loginService: LoginService,
    private router: Router) { }

  onLogin() {
    this.loginService.loginUser(this.userLogin).subscribe({
      next: data => {
        console.log(data)
        this.router.navigate(['/']).then(() => window.location.reload());
      }
      ,
      error: err => console.log(err)
    });
  }
}
