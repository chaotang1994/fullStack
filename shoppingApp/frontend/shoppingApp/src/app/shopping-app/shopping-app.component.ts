import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-shopping-app',
  templateUrl: './shopping-app.component.html',
  styleUrls: ['./shopping-app.component.css']
})
export class ShoppingAppComponent implements OnInit {

  type:string;
  constructor(
    private activatedRoute:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.getType();
  }


  getType(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.type=params.get('type');
        this.type=this.type.charAt(0).toUpperCase()+this.type.slice(1);
      }
    );
  }

}
