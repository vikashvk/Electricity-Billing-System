import { Injectable, ErrorHandler, Inject, Injector } from '@angular/core';
import { UNAUTHORIZED, BAD_REQUEST, FORBIDDEN } from "http-status-codes";
import { Router } from "@angular/router";
import { NotificationService } from './notification.service';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService implements ErrorHandler {
  static readonly DEFAULT_ERROR_TITLE: string = "Please try again.";
  static readonly UNAUTHORIZED_ERROR: string = "Please login to access.";
  constructor(@Inject(Injector) private injector: Injector,private router: Router) { }


  public handleError(error: any) {
    console.log(error);
    let httpErrorCode = error.status;
    switch (httpErrorCode) {
      case UNAUTHORIZED:
        this.showError(ErrorHandlerService.UNAUTHORIZED_ERROR);
        this.router.navigateByUrl("/login");
        break;
      case BAD_REQUEST:
        let message: string = '';
        if (error.error) {
          message = error.error.message;
          this.showError(message);
        }
        break;
      case 0:
        break;
      default:
        this.showError(ErrorHandlerService.DEFAULT_ERROR_TITLE);
    }
  }
 // Need to get ToastrService from injector rather than constructor injection to avoid cyclic dependency error
 private get toastrService(): ToastrService {
  return this.injector.get(ToastrService);
}
  private showError(message: string) {
    this.toastrService.error(
      message,
      null,
      {
          closeButton: true,
          timeOut: 5000,
          onActivateTick: true
      }
  );
  }
}
