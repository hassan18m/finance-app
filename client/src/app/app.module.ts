import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardModeratorComponent } from './components/board-moderator/board-moderator.component';
import { BoardUserComponent } from './components/board-user/board-user.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { BankAccountComponent } from './components/bank-account/bank-account.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { AddBankAccountComponent } from './components/dialogs/add-bankaccount/add-bankaccount.component';
import { MatSelectModule } from '@angular/material/select';
import { UpdateBankaccountComponent } from './components/dialogs/update-bankaccount/update-bankaccount.component';
import { RemoveBankaccountComponent } from './components/dialogs/remove-bankaccount/remove-bankaccount.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AddTransactionComponent } from './components/dialogs/add-transaction/add-transaction.component';
import { UpdateTransactionComponent } from './components/dialogs/update-transaction/update-transaction.component';
import { ShowTransactionsComponent } from './components/dialogs/show-transactions/show-transactions.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { AddExpenseTransactionComponent } from './components/dialogs/add-expense-transaction/add-expense-transaction.component';
import { AddIncomeTransactionComponent } from './components/dialogs/add-income-transaction/add-income-transaction.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    BankAccountComponent,
    AddBankAccountComponent,
    UpdateBankaccountComponent,
    RemoveBankaccountComponent,
    AddTransactionComponent,
    UpdateTransactionComponent,
    ShowTransactionsComponent,
    AddExpenseTransactionComponent,
    AddIncomeTransactionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatGridListModule,
    MatCardModule,
    MatDialogModule,
    MatSelectModule,
    MatSnackBarModule,
    MatDividerModule,
    MatProgressBarModule,
    MatStepperModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
