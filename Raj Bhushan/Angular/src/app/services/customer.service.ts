import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Customer } from '../entity/Customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient , private router:Router) { }

  url:string="http://localhost:3300/ebms"

  //Code for viewing the bill generated on customer
  viewBillHistory(customerId:number):Observable<any>
  {
     return this.http.get(this.url+'/viewBillHistory/'+customerId)
  }
  
  //Code for viewing the payment status of customer
  viewPaymentHistory(customerId:number):Observable<any>
  {
    return this.http.get(this.url+'/viewPaymentHistory/'+customerId)
  }

  //Code for viewing the profile
  viewCustomerProfile(customerId:number):Observable<any>
  {
    return this.http.get(this.url+'/viewCustomerProfile/'+customerId)
  }
  
  //Code for updating the profile 
  editProfile(customerId:number,customer:Customer):Observable<any>
{
    return this.http.put(this.url+'/editProfile/'+customerId+"/", customer);
}

 //Code for changing the password
 changePassword(customerId:number,oldPassword:string, newPassword:string):Observable<any>
 {
   return this.http.get(this.url+'/changePassword/'+customerId+"/"+oldPassword+"/"+newPassword)
 }
}
