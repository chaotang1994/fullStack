import { Component, OnInit, ViewChild } from '@angular/core';
import { Input, Output, EventEmitter  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Location} from '@angular/common';
import {FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { ProductService } from '../product.service';
import {debounceTime} from 'rxjs/operators';
import {NgbAlert} from '@ng-bootstrap/ng-bootstrap';
import {Subject} from 'rxjs';
import {Product} from '../product';
import { first } from 'rxjs/operators';


@Component({
  selector: 'app-admin-modify-add-product',
  templateUrl: './admin-modify-add-product.component.html',
  styleUrls: ['./admin-modify-add-product.component.css']
})
export class AdminModifyAddProductComponent implements OnInit {

  // @Output() countChanged: EventEmitter<number> =   new EventEmitter();

  admin_id:string;
  url:string;
  addProductForm: FormGroup;
  submitted:boolean=false;
  file:File=null;
  private _success = new Subject<string>();  
  @ViewChild('selfClosingAlert', {static: false}) selfClosingAlert: NgbAlert;
  successMessage = '';
  product:Product;
  Condition:any=['new','used','unknow']
  Category:any=['electronic','apparel','food']
  

  constructor(
    private route: ActivatedRoute,
    private _location: Location,
    private formBuilder: FormBuilder,
    private productService:ProductService
  ) { 
    
  }

  ngOnInit(): void {
    this.getId();
    this.createForm();
    this._success.pipe(debounceTime(500)).subscribe(() => {
      if (this.selfClosingAlert) {
        this.selfClosingAlert.close();
      }
    });
  }

  getId(){
    this.admin_id=this.route.snapshot.paramMap.get("id");

  }

  backClicked() {
    this._location.back();
  }

  createForm(){
    this.addProductForm=this.formBuilder.group({
      product_name:['',[Validators.required,Validators.minLength(1)]],
      product_quantity:['',[Validators.required,Validators.min(1)]],
      product_price:['',[Validators.required,Validators.min(1)]],
      product_image:[null,[Validators.required]],
      product_category:['',[Validators.required,Validators.minLength(1)]],
      product_condition:['',[Validators.required,Validators.minLength(1)]],
    });
    // this.addProductForm=new FormGroup({
    //   product_name: new FormControl('',[Validators.required,Validators.minLength(1)]),
    //   product_quantity:new FormControl('',[Validators.required,Validators.min(0)]),
    //   product_price:new FormControl('',[Validators.required,Validators.min(0)]),
    //   product_image:new FormControl(['',Validators.required]),
    //   product_category:new FormControl(['',Validators.required]),
    //   product_condition:new FormControl(['',Validators.required])
    // });

  }

  get f(){
    return this.addProductForm.controls;
  }

  onFileChange(event:any) {
    let reader = new FileReader();

    if(event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
    
      reader.onload = () => {
        this.addProductForm.patchValue({
          product_image: reader.result
        });
        
      };
    }

  }

   
  changeSuccessMessage() {
    this.successMessage="Product Success Added!";
  }


  onSubmit(){
    console.log("onSumbit yes");
    this.submitted=true;

    console.log("invalide? "+this.addProductForm.invalid);



    console.log("name"+ this.addProductForm.value.product_name);
    console.log("qu"+ this.addProductForm.value.product_quantity);
    console.log("pr"+ this.addProductForm.value.product_price);
    console.log("ca"+ this.addProductForm.value.product_category);
    console.log("co"+ this.addProductForm.value.product_condition);
    console.log("co "+ this.f['product_condition'].value);
    console.log("img "+ this.f['product_image'].value);
    console.log("-----------------");
    console.log("id "+this.admin_id);

    if (this.addProductForm.invalid) {
      return;
    }
    console.log("productForm "+this.addProductForm.value);
    let product = new Product(-1,
    this.addProductForm.value.product_name,
    this.addProductForm.value.product_category,
    this.addProductForm.value.product_quantity,
    this.addProductForm.value.product_condition,
    this.addProductForm.value.product_price,
    this.addProductForm.value.product_image
    );

      this.productService.addProductFromAdmin(this.admin_id, product).subscribe(
        res=>{
          console.log("response: "+res);
          alert('Updated Successfully.')
          this.changeSuccessMessage();
          this.backClicked();
        },
        error=>{
          console.log("error: "+error);
        }

      );
    
       
  }



  onReset() {
    this.submitted = false;
    this.addProductForm.reset();
  }





  

}
