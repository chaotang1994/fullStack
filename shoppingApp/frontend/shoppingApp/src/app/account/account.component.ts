import { Component, OnInit } from '@angular/core';
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

  goBack(){
    this.location.back();
  }

}
