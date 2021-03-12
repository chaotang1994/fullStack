import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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
  submitted = false;
  error:boolean =false;
  userType:string=null;
  userError:boolean=false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService:UserService,
    private route:ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.route.paramMap.subscribe(
      params=>{
        this.userType=params.get('type');
      }
    );
  }

  createForm(){
    this.registerForm = this.formBuilder.group({
      emailID:['',[Validators.required,Validators.email]],
      password:['',[Validators.required, Validators.minLength(6)]],
      firstName:['',[Validators.required, Validators.minLength(1)]],
      lastName:['',[Validators.required, Validators.minLength(1)]],
      address:['',[Validators.required, Validators.minLength(1)]],
      city:['',[Validators.required, Validators.minLength(1)]],
      state:['',[Validators.required, Validators.minLength(1)]],
      zipCode:['',[Validators.required]],
      phoneNumber:['',[Validators.required, Validators.minLength(1)]],
    });
  }
  // Validators.minLength(5),Validators.pattern(/^[0-9]\d*$/)]

  get f(){
    return this.registerForm.controls;
  }

  onSubmit(){

    this.submitted = true;


    if (this.registerForm.invalid) {
      return;
    }

    // console.log(this.registerForm.value);
    if(this.userError!==null){
      this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
          data => {
              console.log("Registration successful",data);
              this.router.navigate(['/login',this.userType]);
          },
          error => {
            this.error=true;
            console.log(error);
            console.log("Registration error");
          }
          );
        }else{
          this.userError=true;
        }
      
}

  

  ngOnDestroy() {
    // unsubscribe to avoid memory leaks
  }

}

