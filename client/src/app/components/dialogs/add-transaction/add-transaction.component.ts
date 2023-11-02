import { Component, OnInit } from '@angular/core';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.css']
})
export class AddTransactionComponent implements OnInit {
  bankAccounts!: BankAccount[]

  ngOnInit(): void {
    this.getBankAccounts();
  }

  constructor(private bankAccountService: BankAccountService) { }

  getBankAccounts() {
    this.bankAccountService.getBankAccounts().subscribe({
      next: res => {
        this.bankAccounts = res;
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
