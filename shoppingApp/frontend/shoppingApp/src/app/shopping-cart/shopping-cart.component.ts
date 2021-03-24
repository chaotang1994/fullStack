import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../product';
import {Location} from '@angular/common';

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


  constructor(
    private activatedRoute:ActivatedRoute,
    private productService:ProductService,
    private _location:Location
  ) { }

  ngOnInit(): void {
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
    this.productService.removeProductFromUser(product_id,this.id).subscribe(
      result=>{
        console.log("remove from cart: "+ result);
        if(result===product_id){
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
      }
    );
  }

  saveQuantity(product:Product){
    this.currentID=-1;
    this.productService.updateQuantityFromUser(this.id,product).subscribe(
      rep=>{
        console.log("reponse: "+ rep);
      }
    );
  }

  editQuantity(product_id:number){
    if(this.currentID!==-1 && this.currentID!==product_id){
      console.log("Remember to Save!!!");
    }
    this.currentID=product_id;
  }
  
  backClicked() {
    this._location.back();
  }

}
