import { Component, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddBankAccountComponent } from '../dialogs/add-bankaccount/add-bankaccount.component';
import { UserLoginRequest } from 'src/app/types/user-login';
import { UpdateBankaccountComponent } from '../dialogs/update-bankaccount/update-bankaccount.component';
import { RemoveBankaccountComponent } from '../dialogs/remove-bankaccount/remove-bankaccount.component';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddTransactionComponent } from '../dialogs/add-transaction/add-transaction.component';
import { UpdateTransactionComponent } from '../dialogs/update-transaction/update-transaction.component';
import { RemoveTransactionComponent } from '../dialogs/remove-transaction/remove-transaction.component';

@Component({
  selector: 'app-test-split-screen',
  templateUrl: './test-split-screen.component.html',
  styleUrls: ['./test-split-screen.component.css']
})
export class TestSplitScreenComponent {

  constructor(public dialog: MatDialog,
    private _snackBar: MatSnackBar) { }

  openAddBankAccountDialog() {
    const dialogRef = this.dialog.open(AddBankAccountComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.openSnackBar("Account successfully added!");
      }
    });
  }

  openUpdateBankAccountDialog() {
    const dialogRef = this.dialog.open(UpdateBankaccountComponent);
  }
  openRemoveBankAccountDialog() {
    const dialogRef = this.dialog.open(RemoveBankaccountComponent);
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, undefined, { duration: 4000 });
  }

  openAddTransactionDialog() {
    const dialogRef = this.dialog.open(AddTransactionComponent);
  }

  openUpdateTransactionDialog() {
    const dialogRef = this.dialog.open(UpdateTransactionComponent);
  }
  openRemoveTransactionDialog() {
    const dialogRef = this.dialog.open(RemoveTransactionComponent);
  }
}
