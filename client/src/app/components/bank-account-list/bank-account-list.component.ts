import { Component, OnInit } from '@angular/core';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BankAccount } from 'src/app/types/bank-account';
import { Transaction } from 'src/app/types/transaction';

@Component({
  selector: 'app-bank-account-list',
  templateUrl: './bank-account-list.component.html',
  styleUrls: ['./bank-account-list.component.css']
})
export class BankAccountListComponent implements OnInit {

  bankAccounts: BankAccount[] = [];
  transactions: Transaction[] = [];


  constructor(private bankAccountService: BankAccountService) { }

  ngOnInit(): void {
    this.listBankAccounts();
  }

  listBankAccounts() {
    this.bankAccountService.getBankAccounts().subscribe(data => {
      console.log(JSON.stringify(data));
      this.bankAccounts = data;
      this.bankAccounts.forEach(element => {
        this.transactions = element.transactions;
      });
    });
  }

}
