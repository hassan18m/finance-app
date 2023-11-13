import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BankAccountService } from 'src/app/services/bank-account.service';
import { BudgetService } from 'src/app/services/budget.service';
import { Budget } from 'src/app/types/budget';

@Component({
  selector: 'app-update-budget',
  templateUrl: './update-budget.component.html',
  styleUrls: ['./update-budget.component.css']
})
export class UpdateBudgetComponent {

  updateBudgetReq: any = {
    amount: 0,
    name: ''
  };
  bankAccountBalance: number = 0;

  form = new FormGroup({
    amount: new FormControl(null, [Validators.min(0.01)]),
    name: new FormControl('', Validators.minLength(3))
  });
  submitted: boolean = false;

  constructor(private budgetService: BudgetService,
    @Inject(MAT_DIALOG_DATA) public budget: Budget,
    private dialogRef: MatDialogRef<UpdateBudgetComponent>,
    private bankAccountService: BankAccountService) { }

  ngOnInit(): void {
    this.getBankAccountBalance();
  }

  onSubmit() {
    this.submitted = true;
  }

  updateBudget() {
    if (this.form.value.amount !== null) {
      this.updateBudgetReq.amount = this.form.value.amount;
    }
    if (this.form.value.name !== '') {
      this.updateBudgetReq.name = this.form.value.name;
    }

    this.budgetService.updateBudget(this.budget.id, this.updateBudgetReq).subscribe({
      next: res => {
        this.dialogRef.close(true);
      },
      error: err => {
        console.log(err);
        this.dialogRef.close(err.error);
      }
    })
  }

  getBankAccountBalance(): void {
    this.bankAccountService.getBalanceOfBankAccount(this.budget.bankAccountId).subscribe({
      next: res => {
        this.bankAccountBalance = res;
        this.form.controls.amount.addValidators(Validators.max(this.bankAccountBalance));
      }
    });
  }
}
