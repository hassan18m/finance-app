import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRegisterRequest } from 'src/app/types/user-register-req';
import Validation from 'src/app/types/validation';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    console.log(JSON.stringify(this.form.value, null, 2));
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  regUser(): void {
    if (this.submitted && !this.form.invalid) {
      const userRegister: UserRegisterRequest = {
        firstName: this.form.value.firstName,
        lastName: this.form.value.lastName,
        email: this.form.value.email,
        password: this.form.value.password
      }

      this.authService.register(userRegister).subscribe({
        next: res => {
          this.router.navigate(['/login']);
        },
        error: err => {
          console.log(userRegister);
          console.log(err);
        }
      });
    }
  }
}
