import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import { UserService } from '../user.service';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;
  submitted = false;
  error:boolean =false;
  returnUrl:string;
  errorMessage:string;
  type:string=null;

  constructor(
    private formBuilder: FormBuilder,
    private userService:UserService,
    private router : Router,
    private route:ActivatedRoute,
    private authenticationService:AuthenticationService,
    private _location: Location,

  ) { 
 

  }

  ngOnInit(): void {
    this.createForm();
    this.route.paramMap.subscribe(
      params=>{
        this.type=params.get('type');
      }
    );

  }

  get f() { return this.loginForm.controls; }


  createForm(){
    this.loginForm=this.formBuilder.group({
      emailID:['',Validators.required],
      password:['',Validators.required]
    });
  }

  

  login(){
    this.submitted=true;
    if (this.loginForm.invalid) {
      return;
    }

    
    this.authenticationService.login(this.loginForm.value,this.type)
    .subscribe(
      data =>{
        console.log("login successful",data);
        localStorage.setItem('currentUser',JSON.stringify(data));
        if(this.type==='Customer'){
          this.router.navigate(['/home',data]);
        }else{
          this.router.navigate(['/admin-account',data]);
        }
      },
      // error => {
      //   console.log("login fail   ",error);
      //   this.error=true;
      // }
      (res: Response)=>{
        this.errorMessage=this.loginStatus(res.status);
        this.error=true;
      }, 
      
    );
  }

  loginStatus(error:number):string{
    // if(error==404){
    //   return "Account not found";
    // }else 
    if(error==401 || error==404){
      return "Email or password incorrect";
    }else{
      return "Is not connect to serve";
    }

  }

  backClicked() {
    this._location.back();
  }




}
