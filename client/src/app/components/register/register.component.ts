import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRegisterRequest } from 'src/app/types/user-register-req';
import Validation from 'src/app/types/validation';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  form = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
    confirmPassword: new FormControl('', [Validators.required]),
  });
  submitted = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  addValidator() {
    this.form.controls.confirmPassword.addValidators(
      Validators.pattern(this.form.value.password!)
    );
  }

  onSubmit(): void {
    this.submitted = true;
  }

  regUser(): void {
    if (this.submitted && !this.form.invalid) {
      const userRegister: UserRegisterRequest = {
        firstName: this.form.value.firstName!,
        lastName: this.form.value.lastName!,
        email: this.form.value.email!,
        password: this.form.value.password!,
      };

      this.authService.register(userRegister).subscribe({
        next: (res) => {
          this.router.navigate(['/login']);
        },
      });
    }
  }
}
