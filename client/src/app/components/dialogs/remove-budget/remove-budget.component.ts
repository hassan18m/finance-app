import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BudgetService } from 'src/app/services/budget.service';

@Component({
  selector: 'app-remove-budget',
  templateUrl: './remove-budget.component.html',
  styleUrls: ['./remove-budget.component.css']
})
export class RemoveBudgetComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public budgetId: number,
    private budgetService: BudgetService,
    public dialogRef: MatDialogRef<RemoveBudgetComponent>) { }

  removeBudget() {
    this.budgetService.removeBudget(this.budgetId).subscribe({
      next: res => {
        this.dialogRef.close(true);
      },
      error: err => {
        this.dialogRef.close('error');
      }
    })
  }
}
