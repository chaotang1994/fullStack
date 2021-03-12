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
  message:string;

  constructor(
    private activatedRoute:ActivatedRoute,
    private productService:ProductService,
    private _location:Location
  ) { }

  ngOnInit(): void {
    this.loadCart()
  }

  loadCart(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.id=params.get('id');
        console.log(this.id);
      }
    )
    this.productService.getShoppingCartList(this.id).subscribe(
      data=>{
        this.products=data;
      },
      (res:Response)=>{
        if(res.status==404){
          this.message="Shooping Cart is Empty!!!";
          this.emptyCart=true;
        }
      }
    )
  }

  setMessageStyles() {
    let styles = {
      'color':'#392613',
      'padding-bottom':'20px',
      'font-size':'150%',
    };
    return styles;
  }

  backClicked() {
    this._location.back();
  }

}
