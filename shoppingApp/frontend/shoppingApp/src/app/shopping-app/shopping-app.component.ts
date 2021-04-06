import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-shopping-app',
  templateUrl: './shopping-app.component.html',
  styleUrls: ['./shopping-app.component.css']
})
export class ShoppingAppComponent implements OnInit {

  type:string;
  disable:boolean=false;


  constructor(
    private activatedRoute:ActivatedRoute,
    private _location: Location,

  ) { }

  ngOnInit(): void {
    this.getType();
  }


  getType(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.type=params.get('type');
        console.log("type: "+this.type);
        if(this.type==="admin"){
          this.disable=true;
        }
        this.type=this.type.charAt(0).toUpperCase()+this.type.slice(1);
      }
    );
  }

  goBack(){
    this._location.back();
  }

}
