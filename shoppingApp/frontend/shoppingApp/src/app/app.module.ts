import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShoppingAppComponent } from './shopping-app/shopping-app.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { AccountComponent } from './account/account.component';
import { AdminAccountComponent } from './admin-account/admin-account.component';
import { HomeComponent} from './home/home.component';
import { RegisterComponent } from './register/register.component';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LoginOptionComponent } from './login-option/login-option.component';
import { AdminModifyProductComponent } from './admin-modify-product/admin-modify-product.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { CommonModule } from '@angular/common';
import { AdminModifyAddProductComponent } from './admin-modify-add-product/admin-modify-add-product.component';
import { ItemFilterPipe } from './item-filter.pipe';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { FilterListPipe } from './filter-list.pipe';
import { ConfirmComponent } from './confirm/confirm.component';
import { AboutMeComponent } from './about-me/about-me.component'; 


@NgModule({
  declarations: [
    AppComponent,
    ShoppingAppComponent,
    HomeComponent,
    ShoppingCartComponent,
    AccountComponent,
    AdminAccountComponent,
    RegisterComponent,
    LoginComponent,
    LoginOptionComponent,
    AdminModifyProductComponent,
    AdminHomeComponent,
    AdminModifyAddProductComponent,
    ItemFilterPipe,
    FilterListPipe,
    ConfirmComponent,
    AboutMeComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    NgbModule,
    NgxPaginationModule,
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
