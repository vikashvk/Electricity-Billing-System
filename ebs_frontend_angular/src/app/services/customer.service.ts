import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChangePasswordModel } from '../models/change-password-model';
import { CustomerDetail } from '../models/customer-detail';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(@Inject('API_URL') private apiUrl: string, private http: HttpClient) { }
  public getCustomerProfile() {
    let path: string = 'api/v1/users/me';
    return this.http.get<any>(this.apiUrl + path);
  }
  //returns all the bills
  getAllBills(): Observable<any> {
    let path: string = 'api/v1/bills';
    return this.http.get(this.apiUrl + path);
  }
  //returns details of bill of specified id
  getBillDetails(billId: number): Observable<any> {
    let path: string = 'api/v1/bills/';
    return this.http.get(this.apiUrl + path + billId);
  }
//downloads bill in pdf
downloadBillPdf(billId: number): Observable<any> {
  let path:string = 'api/v1/bills/pdf/';
  return this.http.get(this.apiUrl + path + billId,{responseType: 'blob'});
}
  //Code for viewing the payment status of customer
  getAllPayments(): Observable<any> {
    let path: string = 'api/v1/payments';
    return this.http.get(this.apiUrl + path);
  }

  //Code for viewing the profile
  getProfileDetails(): Observable<any> {
    let path: string = 'api/v1/users/me';
    return this.http.get(this.apiUrl + path);
  }

  //Code for updating the profile 
  updateProfileDetails(customer: CustomerDetail): Observable<any> {
    let path: string = 'api/v1/users/me';
    return this.http.put(this.apiUrl + path, customer);
  }

  //Code for changing the password
  changePassword(credentials: ChangePasswordModel): Observable<any> {
    let path: string = 'api/v1/users/change-password';
    return this.http.put(this.apiUrl + path, credentials);
  }
}
