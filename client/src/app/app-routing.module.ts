import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { BankAccountListComponent } from './components/bank-account-list/bank-account-list.component';

const routes: Routes = [
  { path: 'users', component: UserlistComponent },
  { path: 'bank-accounts', component: BankAccountListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
