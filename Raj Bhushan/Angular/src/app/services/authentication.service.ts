import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Router } from '@angular/router';
import { Customer } from '../entity/Customer';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient, private router:Router) { }

  sendToken(token: string) {
    localStorage.setItem("LoggedInUser", token)
  }
  getToken() {
    return localStorage.getItem("LoggedInUser")
  }
  isLoggedIn() {
    return this.getToken() !== null;
  }
  logout() {
    localStorage.removeItem("LoggedInUser");
    this.router.navigate(['login']);
  }
  

  url:string="http://localhost:3300/ebms"
   //Code for login the user
   loginCustomer(customer:Customer): Observable<any> 
   {
     return this.http.get(this.url+'/login/' + customer.custId +"/"+customer.custPassword);
   }
 
  // Code for registering the user
   registerCustomer(customer:Customer): Observable<any> 
   {
     return this.http.post(this.url+'/register', customer);
   }
}
