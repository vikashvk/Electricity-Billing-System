import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxJs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = 'http://localhost:9292/api/bill-module';

  constructor(private http: HttpClient) { 
  }

  getCustomerList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + '/Customers');
  }

  getBillList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + '/Bills');
  }
  getAllBillByCustId(custId:number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + '/Bills/all/'+custId);
  }

  createBill(bill: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + '/addBill', bill);
  }

  deleteBill(id: any): Observable<any>{
    return this.http.delete(`${this.baseUrl}` + '/deleteBill/' + `${id}`, { responseType : 'text'});
  }

  editBill(id:number, value:any):Observable<Object>
{
    return this.http.put(`${this.baseUrl}`+'/updateBill/' + `${id}`, value);
}
  
  viewBilldetail(id: any):Observable<any>
{
  return this.http.get(`${this.baseUrl}`+'/Bills/' + `${id}`);
}

  getBillByid(id: any): Observable<any> 
{
  return this.http.get(`${this.baseUrl}`+'/Bills/' + `${id}`);
}

  getBillBycustid(id: any): Observable<any> 
{
  return this.http.get(`${this.baseUrl}`+'/Billscust/' + `${id}`);
}

}
