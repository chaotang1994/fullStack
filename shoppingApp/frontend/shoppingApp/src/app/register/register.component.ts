import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { first } from 'rxjs/operators';
import { Name } from '../name';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService:UserService,
  
  ) { }

  ngOnInit(): void {
    this.createForm();


  

  }

  createForm(){
    this.registerForm = this.formBuilder.group({

      emailID:['',Validators.required],
      password:['',Validators.required],
      firstName:['',Validators.required],
      lastName:['', Validators.required],
      address:['',Validators.required],
      city:['',Validators.required],
      state:['',Validators.required],
      zipCode:['',Validators.required],
      phoneNumber:['',Validators.required],
    });
  }

  get f(){
    return this.registerForm.controls;
  }
///this.content = Object.assign(this.content, this.contentForm.value);

  onSubmit(){
    console.log(this.registerForm.value);
    this.userService.register(this.registerForm.value)
    .subscribe(
        data => {
            console.log("Registration successful",data);
            // this.router.navigate(['/login']);
        },
        error => {
          console.log(error);
          console.log("Registration error");
        });

  }

}

