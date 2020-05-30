import { Injectable, Inject } from '@angular/core';
import { LoginModel } from '../login/login-model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

const TOKEN_NAME: string = 'ACCESS_TOKEN';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor(@Inject('API_URL') private apiUrl: string, private router: Router, private http: HttpClient) { }
  public login(loginDetails: LoginModel) {
    let path: string = 'login';
    this.http.post<any>(this.apiUrl + path, loginDetails).subscribe(data => {
      console.log(data);
      localStorage.setItem(TOKEN_NAME, data.token);
      this.router.navigateByUrl('/user');
    },
      error => { throw error; });
  }

  public isLoggedIn() {
    return !!localStorage.getItem(TOKEN_NAME);

  }

  public logout() {
    localStorage.removeItem(TOKEN_NAME);
  }

  public getToken() {
    return localStorage.getItem(TOKEN_NAME);
  }
}
