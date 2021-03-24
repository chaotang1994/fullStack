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
  status:boolean=false;
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



  homeClick(){
    this.home=true;
    this.modify=false;

  }

  modifilyClick(){
    this.modify=true;
    this.home=false;
  }



  logout(){
    this.auth.logout();
  }
}
