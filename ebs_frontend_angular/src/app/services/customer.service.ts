import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable,BehaviorSubject } from 'rxjs';
import { ChangePasswordModel } from '../models/change-password-model';
import { CustomerDetail } from '../models/customer-detail';
import { feedback } from '../models/feedback';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private amountSource = new BehaviorSubject<number>(0);
  currentAmount = this.amountSource.asObservable();
  constructor(@Inject('API_URL') private apiUrl: string, private http: HttpClient) { }
  changeAmount(amount: number) {
    this.amountSource.next(amount)
  }
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

  //Code for sending the feedback or any suggestion
  giveFeedback(feed :feedback): Observable<any>
  {
    console.log(feed);
    let path: string= 'api/v1/users/give-feedback';
    return this.http.post(this.apiUrl +path , feed);
  }

}
