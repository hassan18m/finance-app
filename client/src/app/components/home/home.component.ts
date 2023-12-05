import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/services/storage.service';
import { UserService } from 'src/app/services/user.service';
import { Content } from './content';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  content: Content = {
    noOfUsers: 0,
    moneyInCirculation: 0,
    noOfBankAccounts: 0,
    noOfTransactions: 0,
    mostUsedBank: '',
  };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getPublicContent().subscribe({
      next: (data) => {
        this.content = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
