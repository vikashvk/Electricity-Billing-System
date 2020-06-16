import { Injectable } from '@angular/core';
import { CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '../../../node_modules/@angular/router';
import { Observable } from 'rxjs';
import { UserAuthService } from './user-auth.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivateChild{

  constructor(private userAuthService: UserAuthService,private router:Router) { }
  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
   if(!this.userAuthService.isLoggedIn())
   {
     this.router.navigate(['/'])
     return false;
   }
   return true;

  }
}
