import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ExpenseCategoryService } from 'src/app/services/expense-category.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { ExpenseTransactionRequest } from 'src/app/types/expense-transaction-request';

@Component({
  selector: 'app-add-expense-transaction',
  templateUrl: './add-expense-transaction.component.html',
  styleUrls: ['./add-expense-transaction.component.css']
})
export class AddExpenseTransactionComponent implements OnInit {
  addExpenseTransactionReq: ExpenseTransactionRequest = {
    amount: 0,
    description: '',
    categoryName: '',
    recipient: '',
    paymentMethod: '',
    location: '',
  };
  erorrMessage: string = '';
  expenseCategoriesList!: string[];

  form = new FormGroup({
    amount: new FormControl(0, [Validators.required, Validators.min(0.01)]),
    description: new FormControl('', [Validators.required]),
    categoryName: new FormControl('', [Validators.required]),
    recipient: new FormControl('', [Validators.required]),
    paymentMethod: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
  });
  submitted: boolean = false;

  constructor(private transactionService: TransactionService,
    @Inject(MAT_DIALOG_DATA) public bankAccountId: number,
    public dialogRef: MatDialogRef<ExpenseTransactionRequest>,
    private expenseCategoryService: ExpenseCategoryService
  ) { }

  ngOnInit(): void {
    this.getExpenseCategories();
  }

  addExpenseTransaction() {
    if (this.submitted && !this.form.invalid) {
      this.addExpenseTransactionReq.amount = this.form.value.amount!;
      this.addExpenseTransactionReq.description = this.form.value.description!;
      this.addExpenseTransactionReq.categoryName = this.form.value.categoryName!;
      this.addExpenseTransactionReq.recipient = this.form.value.recipient!;
      this.addExpenseTransactionReq.paymentMethod = this.form.value.paymentMethod!;
      this.addExpenseTransactionReq.location = this.form.value.location!;
    }

    this.transactionService.addExpenseTransaction(this.addExpenseTransactionReq, this.bankAccountId).subscribe({
      error: err => {
        this.erorrMessage = err.error
        this.dialogRef.close(this.erorrMessage);
      }
    });
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

  getExpenseCategories() {
    this.expenseCategoryService.getAllExpenseCategories().subscribe({
      next: res => {
        this.expenseCategoriesList = res
      }
    })
  }
}
