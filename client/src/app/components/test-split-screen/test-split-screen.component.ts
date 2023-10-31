import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddBankAccountComponent } from '../dialogs/add-bankaccount/add-bankaccount.component';
import { UserLoginRequest } from 'src/app/types/user-login';
import { UpdateBankaccountComponent } from '../dialogs/update-bankaccount/update-bankaccount.component';
import { RemoveBankaccountComponent } from '../dialogs/remove-bankaccount/remove-bankaccount.component';

@Component({
  selector: 'app-test-split-screen',
  templateUrl: './test-split-screen.component.html',
  styleUrls: ['./test-split-screen.component.css']
})
export class TestSplitScreenComponent {
  constructor(public dialog: MatDialog) { }

  openAddDialog() {
    const dialogRef = this.dialog.open(AddBankAccountComponent);
  }
  openUpdateDialog() {
    const dialogRef = this.dialog.open(UpdateBankaccountComponent);
  }
  openRemoveDialog() {
    const dialogRef = this.dialog.open(RemoveBankaccountComponent);
  }
}
