import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Budget } from 'src/app/types/budget';
import { BudgetExpenseCategory } from 'src/app/types/budget-expense-category';

@Component({
  selector: 'app-budget-expense-categories',
  templateUrl: './budget-expense-categories.component.html',
  styleUrls: ['./budget-expense-categories.component.css']
})
export class BudgetExpenseCategoriesComponent {


  constructor(@Inject(MAT_DIALOG_DATA) public expenseCategories: BudgetExpenseCategory[]) { }
}
