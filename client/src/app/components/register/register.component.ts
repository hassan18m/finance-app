import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserRegisterRequest } from 'src/app/types/user-register-req';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form!: UserRegisterRequest;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';


  constructor(private authService: AuthService) { }

  ngOnInit(): void {

  }

  onSubmit(): void {
    const userRegister = this.form;

    this.authService.register(userRegister).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
}
