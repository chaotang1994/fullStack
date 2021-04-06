import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  count:number=0;
  id:string=null;
  pageError:boolean=false;
  user:User;
  total:number=0;

  constructor(
    private activeRouter:ActivatedRoute,
    private userService:UserService
  ) { }

  ngOnInit(): void {
    this.activeRouter.paramMap.subscribe(data=>{
      this.id=data.get('id');
      this.total=parseInt(data.get('total'));
      if(this.id === null && this.id === '') {
        this.pageError=false;

      }else{
        this.getUserDetails(this.id);
        this.count++;
      }
    })
  }

  getUserDetails(id:string){
    this.userService.UserDetails(id).subscribe(
      info=>{
        this.user=info;
        this.getTotal();
      }
    )
  }

  getTotal(){
    
  }

}
