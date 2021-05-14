import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../product';
import {Location} from '@angular/common';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  id:string
  products:Product[]
  emptyCart:boolean=false;
  message:string="Shooping Cart is Empty!!!";
  edit:boolean=false;
  p:number=1;
  currentID:number=-1;
  successMessage:string="";
  SuccessSaveMessage:String="";
  successSave=false;
  successRemove:boolean=false;
  total:number=0;
  @ViewChild('selfClosingAlert', {static: false}) selfClosingAlert: NgbAlert;


  constructor(
    private activatedRoute:ActivatedRoute,
    private productService:ProductService,
    private _location:Location,
    private router:Router
  ) { }

  ngOnInit(): void {
    setTimeout(() => this.selfClosingAlert.close(), 5000);

    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.id=params.get('id');
        console.log(this.id);
      }
    )
    this.loadCart()
  }

  loadCart(){
    this.productService.getShoppingCartList(this.id).subscribe(
      data=>{
        this.products=data;
        this.getTotal();
      },
      (res:Response)=>{
        if(res.status==404){
          this.emptyCart=true;
        }
      }
    )
    // this.checkEmptyCart();
  }

  // checkEmptyCart(){
  //   console.log("check empty cart: "+ this.emptyCart);
  //   if(this.products===undefined || this.products.length==0){
  //     this.message="Shooping Cart is Empty!!!";
  //     this.emptyCart=true;
  //   }
  // }

  setMessageStyles() {
    let styles = {
      'color':'#392613',
      'padding-bottom':'20px',
      'font-size':'150%',
    };
    return styles;
  }

  removeFromCart(product_id:number){
    this.productService.removeProductFromCustomer(product_id,this.id).subscribe(
      result=>{
        console.log("remove from cart: "+ result);
        console.log("return id: "+result);
        this.successRemove=true;
        if(result===product_id){
          this.successMessage="Product id ("+ product_id+") Successfully removed";
          for(let p=0; p<this.products.length; p++){
            if(this.products[p].id===product_id){
              this.products.splice(p,1);
              if(this.products.length==0){
                this.ngOnInit();
                }
              }
            }
          }else{
            console.log("remove fail!!!");
          }

          this.total=0;
          this.getTotal();

      }
    );
   
  }

  saveQuantity(product:Product){
    this.currentID=-1;
    this.productService.updateQuantityFromCustomer(this.id,product).subscribe(
      rep=>{
          if(rep==product.id){
            console.log("reponse: "+ rep);
            this.successSave=true;
            this.SuccessSaveMessage="Product Quantity Save Successfully!";
          }     
      }
    );
    this.total=0;
    this.getTotal();

  }

  editQuantity(product_id:number){
    if(this.currentID!==-1 && this.currentID!==product_id){
      console.log("Remember to Save!!!");
    }
    this.currentID=product_id;
  }

  comfirm(){
    this.router.navigate(['/confirm',this.id,this.total]);
  }
  
  backClicked() {
    this._location.back();
  }

  getTotal(){
    for(let i=0; i<this.products.length; i++){
      this.total+=this.products[i].price*this.products[i].quantity;
    }
  }

}
