import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient , private router:Router) { }

  url:string="http://localhost:3300/ebms"

  viewBillHistory(customerId:number)
  {
     return this.http.get(this.url+'/viewBillHistory/'+customerId)
  }
  viewPaymentHistory(customerId:number)
  {
    return this.http.get(this.url+'/viewPaymentHistory/'+customerId)
  }
}
