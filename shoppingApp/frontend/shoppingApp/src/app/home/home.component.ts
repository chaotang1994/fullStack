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
  currentID:number=-1;
  serveDisconnected:boolean=false;
  noProductListFound:boolean=false;
  // shoppingCartList:Product[] = [];
  // localList:Product[];

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
    },
    (rep:Response)=>{
      if(rep.status==200){
        console.log("Successfully!!!");
      }else if(rep.status==404){
        this.noProductListFound=true;
      }else{
        this.serveDisconnected=true;
      }
    }
    );
    this.email=this.id;
  }


  addToCart(product:Product){
    console.log("Product id "+ product.id);
    console.log("Customer id "+ this.email);
    console.log("Product Quantity "+ product.quantity);

    if(product.id!=null && this.email!=null){
      this.productService.addProductToUser(product,this.email).subscribe(
        data=>{
          console.log("add to cart id: ",data);

          if(product.id===data){
            console.log("successfully added!!!" + product.name);
            // if(JSON.parse(localStorage.getItem("shoppingCartList"))== null  || JSON.parse(localStorage.getItem("shoppingCartList")) ==='undefined'){
            //   this.shoppingCartList.push(product);
            //   localStorage.setItem("shoppingCartList",JSON.stringify(this.shoppingCartList));
            //   this.localList=JSON.parse(localStorage.getItem("shoppingCartList"));
            // }else{
            //   this.shoppingCartList=JSON.parse(localStorage.getItem("shoppingCartList"));
            //   this.shoppingCartList.push(product);
            //   localStorage.setItem("shoppingCartList",JSON.stringify(this.shoppingCartList));
            //   this.localList=JSON.parse(localStorage.getItem("shoppingCartList"));
            //   console.log(this.localList);
            // }
          }else{
            console.log("adding to cart error!!!");
          }
         
        }
      )
    }


  }



  account(){
    this.router.navigate(['/account',this.id]);
  }

  editQuantity(product_id:number){
    this.currentID=product_id;
  }

  saveQuantity(){
    //alert?
    this.currentID=-1;
  }


  logout(){
    this.auth.logout();
  }

  



}
