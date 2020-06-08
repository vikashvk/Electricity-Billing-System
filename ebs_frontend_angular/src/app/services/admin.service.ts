import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = 'http://localhost:9191/ebs/v2';
  
  constructor(private http: HttpClient) {
   }
   login(adminName, password): Observable<any> {
    let params = new HttpParams();
    params = params.set('adminName', adminName);
    params = params.set('password', password);
    return this.http.get(`${this.baseUrl}` + `/main`, { params: params });
  }
}
