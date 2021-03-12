import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login-option',
  templateUrl: './login-option.component.html',
  styleUrls: ['./login-option.component.css']
})
export class LoginOptionComponent implements OnInit {

  constructor(
    private router:Router,
  ) { }

  ngOnInit(): void {
  }


  adminLogin(){
    this.router.navigate(['/shopping-app','admin']);
  }

  customerLogin(){
    this.router.navigate(['/shopping-app','customer']);
  }

}
