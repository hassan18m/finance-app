import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  loginReq = {
    email: '',
    password: '',
  };

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.form.valid) {
      this.loginReq.email = this.form.value.email!;
      this.loginReq.password = this.form.value.password!;
    }
  }

  login() {
    this.loginReq.email = this.form.value.email!;
    this.loginReq.password = this.form.value.password!;
    this.authService.login(this.loginReq).subscribe({
      next: (data) => {
        this.storageService.saveUser(data);
        this.reloadPage();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  reloadPage(): void {
    this.router.navigate(['/']).then(() => window.location.reload());
  }
}
