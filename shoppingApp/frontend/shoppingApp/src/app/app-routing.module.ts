import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from '../app/home/home.component';
import {ShoppingCartComponent} from '../app/shopping-cart/shopping-cart.component';
import { AccountComponent } from './account/account.component';
import {RegisterComponent} from '../app/register/register.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent},
  { path: 'account', component: AccountComponent},
  { path: 'register', component: RegisterComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
