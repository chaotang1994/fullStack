import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from '../app/home/home.component';
import {ShoppingCartComponent} from '../app/shopping-cart/shopping-cart.component';
import { AccountComponent } from './account/account.component';
import {RegisterComponent} from '../app/register/register.component';
import { LoginComponent } from './login/login.component';
import {ShoppingAppComponent} from '../app/shopping-app/shopping-app.component';
import { AuthGuard } from '../../src/app/auth.guard';
import { LoginOptionComponent} from '../app/login-option/login-option.component';
import {AdminAccountComponent } from '../app/admin-account/admin-account.component';
import {AdminModifyProductComponent} from './admin-modify-product/admin-modify-product.component'
import { AdminHomeComponent } from '../app/admin-home/admin-home.component';
import { CommonModule } from '@angular/common';
import {AdminModifyAddProductComponent} from '../app/admin-modify-add-product/admin-modify-add-product.component';
import {ConfirmComponent} from '../app/confirm/confirm.component';


const routes: Routes = [
  { path: '', redirectTo: 'login-Option', pathMatch: 'full' },
  { path: 'home/:id', component: HomeComponent , canActivate: [AuthGuard] },
  { path: 'shopping-cart/:id', component: ShoppingCartComponent},
  { path: 'account/:id', component: AccountComponent},
  { path: 'register/:type', component: RegisterComponent},
  { path: 'login/:type', component: LoginComponent},
  { path: 'shopping-app/:type', component: ShoppingAppComponent},
  { path: 'login-Option', component: LoginOptionComponent},
  { path: 'admin-account/:id', component: AdminAccountComponent, canActivate: [AuthGuard],
     children:[
      { path: 'admin-modify-product', component: AdminModifyProductComponent},
      { path: 'admin-home', component: AdminHomeComponent},
      ]
  },
  { path: 'admin-modify-add-product/:id', component: AdminModifyAddProductComponent},
  // { path: '**', component: LoginOptionComponent },
  {path: 'confirm/:id/:total', component: ConfirmComponent}
];

@NgModule({
  imports: [
  RouterModule.forRoot(routes),
  CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
