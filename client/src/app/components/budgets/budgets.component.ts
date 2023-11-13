import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BudgetService } from 'src/app/services/budget.service';
import { Budget } from 'src/app/types/budget';
import { BudgetExpenseCategoriesComponent } from '../dialogs/budget-expense-categories/budget-expense-categories.component';
import { BudgetExpenseCategory } from 'src/app/types/budget-expense-category';
import { AddBudgetComponent } from '../dialogs/add-budget/add-budget.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RemoveBudgetComponent } from '../dialogs/remove-budget/remove-budget.component';
import { UpdateBudgetComponent } from '../dialogs/update-budget/update-budget.component';

@Component({
  selector: 'app-budgets',
  templateUrl: './budgets.component.html',
  styleUrls: ['./budgets.component.css']
})
export class BudgetsComponent implements OnInit {
  budgets!: Budget[];

  constructor(private budgetService: BudgetService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getUserBudgets();
  }

  openExpenseCategoriesDialog(budgetExpenseCategories: BudgetExpenseCategory[]) {
    const dialogRef = this.dialog.open(BudgetExpenseCategoriesComponent, { data: budgetExpenseCategories });
  }

  openUpdateBudgetDialog(budget: Budget) {
    const dialogRef = this.dialog.open(UpdateBudgetComponent, { data: budget });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.openSnackBar("Budget updated successfully!")
      } else if (result !== true && result !== false) {
        this.openSnackBar(result);
      }
    });
  }

  openRemoveBudgetDialog(budgetId: number) {
    const dialogRef = this.dialog.open(RemoveBudgetComponent, { data: budgetId });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.ngOnInit();
        this.openSnackBar("Budget successfully removed!");
      } else if (result === 'error') {
        this.openSnackBar("Error removing budget, try again!");
      }
    });
  }

  getUserBudgets() {
    this.budgetService.getUserBudgets().subscribe({
      next: res => {
        this.budgets = res;
      }
    })
  }

  addBudgetToUserDialog() {
    const dialogRef = this.dialog.open(AddBudgetComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.ngOnInit();
        this.openSnackBar("Budget successfully added!");
      } else if (result === 'error') {
        this.openSnackBar("Error adding budget, try again!");
      }
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, { duration: 4000 });
  }
}
