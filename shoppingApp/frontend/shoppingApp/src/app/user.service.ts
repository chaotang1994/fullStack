import { Injectable } from '@angular/core';
import{ HttpClient} from '@angular/common/http';
import { User } from '../interface/user';
import { Name } from '../app/name';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http : HttpClient) { }

  register(data):Observable<any>{
    return this.http.post<User>('http://localhost:8888/registerNewCustomer',data);
  }


    
  

}
