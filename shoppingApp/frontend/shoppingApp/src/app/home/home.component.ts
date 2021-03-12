import { identifierModuleUrl } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import {AuthenticationService} from '../authentication.service';
import { ProductService } from '../product.service';
import { Product } from '../product';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
// import {ItemFilterPipe} from '../item-filter.pipe';
import { fromEventPattern } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  id:string
  email:string
  adminID:string='Andy'
  products:Product[]
  searchProduct:string;
  p:number=1;


  constructor(
    private userService: UserService,
    private productService : ProductService,
    private activatedRoute:ActivatedRoute,
    private auth:AuthenticationService,
    private router: Router
  ) { 
  }

  ngOnInit(): void {
    this.loadHomePage();
    this.email=this.id;
  }



  loadHomePage(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.id=params.get('id');
      }
    );

    // this.userService.loadHome(this.id);
    this.productService.getAllAdminProduct(this.adminID).subscribe((result : Product[])=>{
      this.products=result;
    }

      // data=>{
      //   console.log("response data: ", data);
      //   this.products=data;
      // }
    );
  }


  addToCart(productID:number){
    console.log("Product id "+ productID);
    console.log("Customer id "+ this.email);

    if(productID!=null && this.email!=null){
      this.productService.addProductToUser(productID,this.email).subscribe(
        data=>{
          console.log("add to cart id: ",data);
        }
      )
    }
  }


  logout(){
    this.auth.logout();
  }



}
