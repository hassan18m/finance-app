import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
//import { UserlistComponent } from './components/userlist/userlist.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardUserComponent } from './components/board-user/board-user.component';
import { BoardModeratorComponent } from './components/board-moderator/board-moderator.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BankAccountComponent } from './components/bank-account/bank-account.component';
import { RemoveBankaccountComponent } from './components/dialogs/remove-bankaccount/remove-bankaccount.component';
import { BudgetsComponent } from './components/budgets/budgets.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'bank-account', component: BankAccountComponent },
  { path: 'budgets', component: BudgetsComponent },
  { path: 'remove-bank-account', component: RemoveBankaccountComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
