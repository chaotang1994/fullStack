import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {AuthenticationService} from '../authentication.service';
@Component({
  selector: 'app-shopping-cart-menu',
  templateUrl: './shopping-cart-menu.component.html',
  styleUrls: ['./shopping-cart-menu.component.css']
})
export class ShoppingCartMenuComponent implements OnInit {

  id:string

  constructor(
    private activatedRoute:ActivatedRoute,
    private auth:AuthenticationService,
  ) { }

  ngOnInit(): void {
    this.getId();
  }

  
  getId(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.id=params.get('id');
      }
    );
  }
  logout(){
    this.auth.logout();
  }


}
