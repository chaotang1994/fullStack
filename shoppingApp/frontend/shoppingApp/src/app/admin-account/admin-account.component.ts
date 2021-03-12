import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../product.service';
import {Product} from '../product';


@Component({
  selector: 'app-admin-account',
  templateUrl: './admin-account.component.html',
  styleUrls: ['./admin-account.component.css']
})
export class AdminAccountComponent implements OnInit {

  email:string
  id:string=null
  modify:boolean=false;
  home:boolean=true;
  // url:string;

  constructor(
    private auth:AuthenticationService,
    private route:ActivatedRoute,
    // private productService:ProductService,
    private router:Router,
  ) { 

  }

  ngOnInit(): void {

    this.route.paramMap.subscribe(
      param=>{
        this.id=param.get('id');
      }
    );
    this.email=this.id;

  }


  // isHomeRoute(){
  //   return this.router.url==='/admin-account/'+this.email;
  // }

  // ismodifilyProduct(){
  //   return this.router.url==='/admin-modifily-product/'+this.email;
  // }

  homeClick(){
    this.home=true;
    this.modify=false;
  }

  modifilyClick(){
    this.home=false;
    this.modify=true;
  }


  logout(){
    this.auth.logout();
  }
}
