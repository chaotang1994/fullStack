import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {ProductService} from '../product.service';
import {Product} from '../product';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'app-admin-modify-product',
  templateUrl: './admin-modify-product.component.html',
  styleUrls: ['./admin-modify-product.component.css']
})
export class AdminModifyProductComponent implements OnInit {


  @Input() admin_id:string
  products:Product[]
  success:boolean
  searchProduct:any;
  emptyList:boolean=false
  serveDisconnected:boolean=false;
  successRemove:boolean=false;
  successMessage:string;
  successSave:boolean=false;
  @ViewChild('selfClosingAlert', {static: false}) selfClosingAlert: NgbAlert;


  constructor(
    private productService:ProductService
  ) { }

  ngOnInit(): void {
    console.log("modify: "+this.admin_id);
    
    this.productService.getAllAdminProduct(this.admin_id).subscribe(
      products=>{
        this.products=products;
        if(this.products===null||this.products.length==0){
          this.emptyList=true;
        }
        
      },
      (rep:Response)=>{
        if(rep.status==200){
          console.log("Successfully!!!");
        }else if(rep.status==404){
          this.emptyList=true;
        }else{
          this.serveDisconnected=true;
        }
      }


    )

  }



  remove(product_id:number):void{
    this.productService.removeProductFromAdmin(product_id,this.admin_id).subscribe(
      success=>{
        this.success=success;
        console.log("success: "+this.success);
        if(this.success){
          for(let p=0; p<this.products.length; p++){
            if(this.products[p].id===product_id){
              this.successMessage=this.products[p].name+" has been removed successfully!!!";
              this.successRemove=true;
              this.products.splice(p,1);
              this.timeout();
            }
          }
        }
      }
    )

 
  
  }

save(product:Product):void{
  console.log("product quantity: "+product.quantity);
  this.productService.editProductFromAdmin(product,this.admin_id).subscribe(
    success=>{
      this.success=success;
      if(this.success=true){
        this.successMessage="Save successfully!!!";
        this.successSave=true;
        this.timeout();
      }
    },
    error=>{
      console.log("error: "+ error);
    }
  )
  
}

setMessageStyles() {
  let styles = {
    'color':'#65EB49',
    'padding-left':'100px',
    'padding-top':'100px',
    'font-size':'300%',
  };
  return styles;
}

  // edit(product_id:number):void{
  //   new Product(product_id,this.name,this.category,this.quantity,this.condition,this.price,this.imgURL);
  // }

  // changeName(name:string){
  //   this.name=name;
  // }

  // changeCategory(category:string){
  //   this.category=category;
  // }

  // changeQuantity(quantity:number){
  //   this.quantity=quantity;

  // }

  // changeCondition(condition:string){
  //   this.condition=condition;

  // }

  // changePrice(price:number){
  //   this.price=price;

  // }


  timeout(){
    setTimeout(() => this.selfClosingAlert.close(), 6000);
  }

 


}
