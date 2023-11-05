import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-update-transaction',
  templateUrl: './update-transaction.component.html',
  styleUrls: ['./update-transaction.component.css']
})
export class UpdateTransactionComponent {

  updateTransactionReq: any = {
    amount: this.transaction.amount,
    description: this.transaction.description,
    recipient: this.transaction.recipient,
    paymentMethod: this.transaction.paymentMethod,
    location: this.transaction.location,
  }

  form: FormGroup = new FormGroup({
    amount: new FormControl(0),
    description: new FormControl(''),
    recipient: new FormControl(''),
    paymentMethod: new FormControl(this.transaction.paymentMethod),
    location: new FormControl(''),
  });

  constructor(private transactionService: TransactionService,
    @Inject(MAT_DIALOG_DATA) public transaction: any,
    public dialogRef: MatDialogRef<UpdateTransactionComponent>) { }

  updateTransaction() {
    this.updateTransactionReq.amount = this.form.value.amount;
    this.updateTransactionReq.description = this.form.value.description;
    this.updateTransactionReq.recipient = this.form.value.recipient;
    this.updateTransactionReq.paymentMethod = this.form.value.paymentMethod;
    this.updateTransactionReq.location = this.form.value.location;


    this.transactionService.updateTransaction(this.transaction.id, this.updateTransactionReq)
      .subscribe({
        next: data => {
          this.dialogRef.close('Transaction updated successfully!');
        },
        error: err => {
          this.dialogRef.close('Error updating transaction, try again!');
        }
      });
  }
}
