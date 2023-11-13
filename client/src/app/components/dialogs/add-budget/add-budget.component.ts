import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BudgetService } from 'src/app/services/budget.service';
import { ExpenseCategoryService } from 'src/app/services/expense-category.service';
import { BankAccount } from 'src/app/types/bank-account';

@Component({
  selector: 'app-add-budget',
  templateUrl: './add-budget.component.html',
  styleUrls: ['./add-budget.component.css']
})
export class AddBudgetComponent implements OnInit {

  userBankAccounts!: BankAccount[];

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  addBudgetReq: any = {
    name: "",
    bankId: "",
    amount: "",
    startDate: "",
    endDate: "",
    expenseCategories: []
  }

  expenseCategoriesForm = new FormControl('');
  expenseCategoriesList!: string[];

  form = new FormGroup({
    name: new FormControl('', [Validators.required]),
    bankId: new FormControl<number>(0, [Validators.required]),
    amount: new FormControl('', [Validators.required, Validators.min(0.01)]),
    range: this.range.controls.start,
    expenseCategoriesForm: this.expenseCategoriesForm
  });
  submitted: boolean = false;

  ngOnInit(): void {
    this.getUserBankAccounts();
    this.getExpenseCategories();
  }

  constructor(private budgetService: BudgetService,
    private expenseCategoryService: ExpenseCategoryService,
    private dialogRef: MatDialogRef<AddBudgetComponent>,
    private bankAccountService: BankAccountService) { }

  addBudget() {
    if (this.submitted && !this.form.invalid) {
      const startDate: string = this.formatStartDate();
      const endDate: string = this.formatEndDate();
      this.addBudgetReq.name = this.form.value.name;
      this.addBudgetReq.amount = this.form.value.amount;
      this.addBudgetReq.startDate = startDate;
      this.addBudgetReq.endDate = endDate;
      this.addBudgetReq.expenseCategories = this.form.value.expenseCategoriesForm;
      this.addBudgetReq.bankId = this.form.value.bankId;

      this.budgetService.addBudgetToUser(this.addBudgetReq).subscribe({
        next: res => {
          this.dialogRef.close(true);
        },
        error: err => {
          this.dialogRef.close('error');
        }
      });
    }
  }

  getExpenseCategories() {
    this.expenseCategoryService.getAllExpenseCategories().subscribe({
      next: res => {
        this.expenseCategoriesList = res
      }
    })
  }

  onSubmit() {
    const bankAccountBalance = this.userBankAccounts.find(bankAccount => bankAccount.id === this.form.value.bankId)?.balance;
    this.form.controls.amount.addValidators(Validators.max(bankAccountBalance!));
    if (this.range.valid) {
      this.submitted = true;
    }
  }

  getUserBankAccounts() {
    this.bankAccountService.getBankAccounts().subscribe({
      next: res => {
        this.userBankAccounts = res;
      }
    })
  }

  private formatStartDate(): string {
    let startDate = this.range.value.start?.getFullYear() +
      '-' + this.range.value.start?.getMonth() +
      '-' + this.range.value.start?.getDate();

    if (startDate.charAt(startDate.length - 2) === '-') {
      startDate = startDate.substring(0, startDate.length - 1) +
        '0' + startDate.charAt(startDate.length - 1);
    }
    return startDate;
  }

  private formatEndDate(): string {
    let endDate = this.range.value.end?.getFullYear() +
      '-' + this.range.value.end?.getMonth() +
      '-' + this.range.value.end?.getDate();

    if (endDate.charAt(endDate.length - 2) === '-') {
      endDate = endDate.substring(0, endDate.length - 1) +
        '0' + endDate.charAt(endDate.length - 1);
    }
    return endDate;
  }
}
