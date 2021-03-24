import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import {FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import { ProductService } from '../product.service';
import {Subject} from 'rxjs';
import {Product} from '../product';


@Component({
  selector: 'app-admin-modify-add-product',
  templateUrl: './admin-modify-add-product.component.html',
  styleUrls: ['./admin-modify-add-product.component.css']
})
export class AdminModifyAddProductComponent implements OnInit {


  admin_id:string;
  url:string;
  addProductForm: FormGroup;
  submitted:boolean=false;
  file:File=null;
  private _success = new Subject<string>();  
  successMessage = '';
  product:Product;
  Condition:any=['New','Used','Unknow'];
  Category:any=['Electronic','Apparel','Food'];


  constructor(
    private route: ActivatedRoute,
    private _location: Location,
    private formBuilder: FormBuilder,
    private productService:ProductService,
    private router: Router
    ) { 
    
  }

  ngOnInit(): void {
    this.getId();
    this.createForm();
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
      product_quantity:[1],
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

    // console.log("invalide? "+this.addProductForm.invalid);
    // console.log("name"+ this.addProductForm.value.product_name);
    // console.log("qu"+ this.addProductForm.value.product_quantity);
    // console.log("pr"+ this.addProductForm.value.product_price);
    // console.log("ca"+ this.addProductForm.value.product_category);
    // console.log("co"+ this.addProductForm.value.product_condition);
    // console.log("co "+ this.f['product_condition'].value);
    // console.log("img "+ this.f['product_image'].value);
    // console.log("-----------------");
    // console.log("id "+this.admin_id);

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
        window.alert('New Product Added Successfully.')
        
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
