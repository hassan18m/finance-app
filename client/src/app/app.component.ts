import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './services/storage.service';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  constructor(private storageService: StorageService, private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {

      const user = this.storageService.getUser();
      console.log(user);
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.email;
    }
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(this.storageService.getUser());
        this.storageService.clean();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
}