import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TransactionService } from 'src/app/services/transaction.service';
import { IncomeTransactionRequest } from 'src/app/types/income-transaction-request';

@Component({
  selector: 'app-add-income-transaction',
  templateUrl: './add-income-transaction.component.html',
  styleUrls: ['./add-income-transaction.component.css']
})
export class AddIncomeTransactionComponent {
  addIncomeTransactionReq: IncomeTransactionRequest = {
    amount: 0,
    description: '',
    paymentMethod: '',
    location: '',
  };

  form: FormGroup = new FormGroup({
    amount: new FormControl(0, [Validators.required, Validators.min(0.01)]),
    description: new FormControl('', [Validators.required]),
    paymentMethod: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
  });
  submitted: boolean = false;

  constructor(private transactionService: TransactionService,
    @Inject(MAT_DIALOG_DATA) public bankAccountId: number,
  ) { }

  addExpenseTransaction() {
    if (this.submitted && !this.form.invalid) {
      this.addIncomeTransactionReq.amount = this.form.value.amount;
      this.addIncomeTransactionReq.description = this.form.value.description;
      this.addIncomeTransactionReq.paymentMethod = this.form.value.paymentMethod;
      this.addIncomeTransactionReq.location = this.form.value.location;
    }

    this.transactionService.addIncomeTransaction(this.addIncomeTransactionReq, this.bankAccountId).subscribe();
  }

  onSubmit() {
    this.submitted = true;
  }
  getErrorMessage(): string {
    if (this.form.get('amount')?.hasError('min')) {
      return 'Minimum amount: 0.01';
    }
    return 'You must enter a value';
  }
}
