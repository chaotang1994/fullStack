import { Injectable } from '@angular/core';
import{ HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router'



@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  url:string = 'http://localhost:8888';

  constructor(private http: HttpClient, private route: Router) {
    
   }


  login(data:string, type:string):Observable<any>{
    return this.http.post(this.url+'/Login/'+type, data,{responseType:'text'});
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.route.navigate(['/'])
  }

  currentUser(){
    return localStorage.getItem('currentUser');
  }

  // islogin(id:string){
  //   if(localStorage.getItem('currentUser')==id){
  //     return true;
  //   }
  //   return false;
  // }

}
