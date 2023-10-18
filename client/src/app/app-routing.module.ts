import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthContentComponent } from './auth-content/auth-content.component';

const routes: Routes = [
  { path: 'auth', component: AuthContentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
