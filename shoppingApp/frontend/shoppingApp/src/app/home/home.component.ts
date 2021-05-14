import { identifierModuleUrl } from '@angular/compiler';
import { Component, OnInit , ViewChild} from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';
import {AuthenticationService} from '../authentication.service';
import { ProductService } from '../product.service';
import { Product } from '../product';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
// import {ItemFilterPipe} from '../item-filter.pipe';
import { fromEventPattern } from 'rxjs';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  id:string
  customerName:string;
  email:string
  adminID:string='Andy'
  products:Product[]
  searchProduct:string;
  p:number=1;
  currentID:number=-1;
  serveDisconnected:boolean=false;
  noProductListFound:boolean=false;
  successMessage:string="";
  SuccessSaveMessage:String="";
  successAdded:boolean=false;
  successSave:boolean=false;
  message:string="";
  @ViewChild('selfClosingAlert', {static: false}) selfClosingAlert: NgbAlert;
  



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
    this.getNameByEmail();
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
      if(this.products===null || this.products.length==0){
        this.noProductListFound=true;
      }
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
          this.message="Product ("+ product.name+") Successfully Added to Shopping Cart";

          if(product.id===data){
            console.log("successfully added!!!" + product.name);
            this.successAdded=true;
            this.successMessage=this.message;
            this.timeout();
          }else{
            console.log("adding to cart error!!!");
          }
  
        }
        
      )
    }


  }


  getNameByEmail(){
    this.userService.getNameByEmail(this.email).subscribe(
      data=>{
        this.customerName=data;
        console.log("User Name "+this.customerName);
      }
    );
  }

  account(){
    this.router.navigate(['/account',this.id]);
  }

  editQuantity(product_id:number){
    this.currentID=product_id;
  }

  saveQuantity(){
    this.successSave=true;
    this.SuccessSaveMessage="Product Quantity Save Successfully!";
    this.timeout();
    this.currentID=-1;
  }


  logout(){
    this.auth.logout();
  }

  timeout(){
    setTimeout(() => this.selfClosingAlert.close(), 5000);
  }

  



}
