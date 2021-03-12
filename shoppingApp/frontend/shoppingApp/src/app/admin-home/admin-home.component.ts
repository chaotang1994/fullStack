import { Component, OnInit, Input } from '@angular/core';
import {ProductService} from '../product.service';
import {Product} from '../product';
import {ActivatedRoute, Router} from '@angular/router';
import{environment} from '../../environments/environment';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {


  id:string;
  products:Product[]
  url:string;

  constructor(
    private productService:ProductService,
    private route:ActivatedRoute,
    private router:Router

  ) { }

  ngOnInit(): void {

    // this.productService.getAllAdminProduct(this.id).subscribe(
    //   products=>{
    //     console.log("response data: ", products);
    //     this.products=products;
    //   }
    // );

    this.loadAdminHome();

  }

  loadAdminHome(){

    this.route.paramMap.subscribe(
      param=>{
        this.id=param.get('id');
      }
    );
    if(this.id!=null){
      this.productService.getAllAdminProduct(this.id).subscribe(//backend product return check 
        products=>{
          console.log("response data: ", products);
          this.products=products;
        }
      );
    }

  }






}
