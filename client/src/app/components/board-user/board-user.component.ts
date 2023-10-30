import { Component, OnInit, ViewChild } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';
import { UserService } from 'src/app/services/user.service';
import { BankAccount } from 'src/app/types/bank-account';
import { Transaction } from 'src/app/types/transaction';
import { User } from 'src/app/types/user';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  userDetails!: User;
  noOfBankAccounts!: number;
  transactions: Transaction[] = [];
  noOfTransactions!: number;
  userTotalBalance!: number;

  ngOnInit(): void {
    this.getUserDetails();
    this.getTotalBudget();
  }
  constructor(private userService: UserService) { }

  getUserDetails(): void {
    this.userService.getUserBoard().subscribe({
      next: res => {
        this.userDetails = res;
        this.noOfBankAccounts = this.userDetails.bankAccounts.length;
        this.pushTransaction(this.userDetails.bankAccounts, this.transactions);
        this.noOfTransactions = this.transactions.length;
        console.log(this.userDetails);
      },
      error: err => {
        console.log(err);
      }
    })
  }

  pushTransaction(bankAccounts: BankAccount[], transactions: Transaction[]): void {
    bankAccounts.forEach(bankAcccount => {
      bankAcccount.transactions.forEach(transaction => {
        transactions.push(transaction);
      });
    })
  }

  getTotalBudget(): void {
    this.userService.getTotalBalance().subscribe({
      next: res => {
        console.log(res);
        this.userTotalBalance = res;
      },
      error: err => {
        console.log(err);
      }
    });
  }

  @ViewChild(MatAccordion) accordion!: MatAccordion;
}
