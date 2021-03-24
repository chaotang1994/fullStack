import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { User } from '../user';
import {UserService} from '../user.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  id:string
  user:User
  disable:boolean =true;
  @ViewChild('alert', { static: true }) alert: ElementRef;
  alertDialog:boolean=false;

  constructor(
    private activatedRoute:ActivatedRoute, 
    private userService:UserService,
    private location:Location,
    ) { }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(){
    this.activatedRoute.paramMap.subscribe(
      params=>{
        this.id=params.get('id');
      }
    );
    if(this.id !== null && this.id !== '') {
      this.UserDetails(this.id);
    }
  }

  UserDetails(id){
    this.userService.UserDetails(id).subscribe(
      userInfo=>{
        this.user=userInfo;
        // console.log("user info : "+this.user);
      }
    );
  }



  backClicked(){
    this.location.back();
  }

  saveInfo(){
    this.userService.UpdateUser(this.user).subscribe(
      success=>{
        if(success){
          this.loadUser();
          this.alertDialog=success;
        }
      }
    );
    this.disable=true;
  }

  editInfo(){
    this.disable=false;
  }

  closeAlert() {
    this.alertDialog=false;
  }


}
