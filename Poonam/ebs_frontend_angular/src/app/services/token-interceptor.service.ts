import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private injector: Injector) { }
  intercept(req, next) {
    let userAuthService = this.injector.get(UserAuthService);
    let tokenizedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${userAuthService.getToken()}`
      }
    });
    return next.handle(tokenizedReq);
  }
}
